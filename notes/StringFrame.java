import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

// ** NOTES ** from March 16 in class you missed. review code.


public class StringFrame extends JFrame {
	public StringFrame() {
		super("Yay class!");
		add(new StringPanel());
		setSize(200, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main (String[] args) {
		JFrame f = new StringFrame();
		f.setVisible(true);
	}
	private static class StringPanel extends JPanel {
		private static final String MESSAGE = "You Rock!";
		public StringPanel() {
			setFont( "Arial" );
		}
		public void setFont(String name) {
			setFont(new Font(name, Font.BOLD, 50));
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			double width = getWidth();
			double height = getHeight();
			
			setBackground(Color.DARK_GRAY);
			setForeground(Color.CYAN);
			
			FontMetrics fm   = g2.getFontMetrics();
			double strWidth  = fm.stringWidth( MESSAGE );
			double strHeight = fm.getHeight();
			double x         = (width-strWidth)/2f;
			double y         = height-(height - strHeight)/2.f;
			g2.drawString( MESSAGE , (int)x, (int) (y));
		}
	}
}
