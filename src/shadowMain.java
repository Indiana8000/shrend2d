import javax.swing.JFrame;

public class shadowMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Shadow Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new shadowPaint());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable( false );
		frame.setVisible(true);
	}
	
}
