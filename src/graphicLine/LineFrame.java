package graphicLine;

import javax.swing.JFrame;

public class LineFrame extends JFrame {

	public LineFrame() {
		add(new LinePanel());
		setSize(500, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new LineFrame();
	}

}





