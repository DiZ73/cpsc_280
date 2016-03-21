import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings("serial")
public class FlyingFrame extends JFrame {
	private class FlyingPanel extends JPanel {
		private int x = 50;
		private int y = 50;
		private Image image;
		private AudioClip sound;
		private boolean up, down, left, right;
		public FlyingPanel() {
			x = y = 50;
			up = down = left = right = false;
			image = getImage("airplane.gif");
			sound = getSound("bomb.wav");
			setFocusable(true);
			addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_UP    : up    = true; break;
					case KeyEvent.VK_DOWN  : down  = true; break;
					case KeyEvent.VK_LEFT  : left  = true; break;
					case KeyEvent.VK_RIGHT : right = true; break;
					case KeyEvent.VK_J     : x = y = 50;   break;
					case KeyEvent.VK_SPACE : sound.play();
					}
				}
				@Override
				public void keyReleased(KeyEvent e) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_UP    : up    = false; break;
					case KeyEvent.VK_DOWN  : down  = false; break;
					case KeyEvent.VK_LEFT  : left  = false; break;
					case KeyEvent.VK_RIGHT : right = false; break;
					}
				}
				
			});
			Timer timer = new Timer(2, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(up)    y -= 15;
					if(down)  y += 15;
					if(left)  x -= 15;
					if(right) x += 15;
					repaint();	
				}
			});
			timer.start();
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, x, y, null);
		}
		private Image getImage(String filename) {
			URL url = getClass().getResource(filename);
			ImageIcon image = new ImageIcon(url);
			return image.getImage();
		}
		private AudioClip getSound(String filename) {
			URL url = getClass().getResource(filename);
			return Applet.newAudioClip(url);
		}
	}
	public FlyingFrame() {
		super("Flying");
		JPanel panel = new FlyingPanel();
		
		add(panel);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		JFrame f = new FlyingFrame();
		f.setVisible(true);
	}
}
