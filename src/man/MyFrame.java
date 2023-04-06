package man;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
	public MyFrame() {
		setTitle("졸라맨");
		add(new ManPanel());
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new MyFrame();
		
		

	}

}
