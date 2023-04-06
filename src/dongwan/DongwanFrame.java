package dongwan;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DongwanFrame extends JFrame{
	public DongwanFrame()	{
		setTitle("동완이의 타코가게");
		add(new DongwanPanel());
		setSize(1000,1000);
		setVisible(true);
		setLocationRelativeTo(null); //프레임창이 null은 가운데에 뜸.
		setResizable(false);		//창 사이즈 안바꿈.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new DongwanFrame();
		
	}

}
