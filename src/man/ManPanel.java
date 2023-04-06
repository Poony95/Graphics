package man;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ManPanel extends JPanel implements Runnable, KeyListener {

	BufferedImage man;		//그림 불러오는 참조변수는 BufferdImage
	BufferedImage man01;
	BufferedImage man02;
	BufferedImage man03;
	
	int x=400, y=300;		// x,y 좌표가 처음 나타날 초기값 세팅
	int dx=0, dy=0;			// 움직일 값 
	int n;
	
	
	public ManPanel (){
		try {
			man = ImageIO.read(new File("man.png"));		//그림을 생성자에 불러와여야 함.
			man01 = ImageIO.read(new File("man01.png"));
			man02 = ImageIO.read(new File("man02.png"));
			man03 = ImageIO.read(new File("man03.png"));
			
		} catch (Exception e) {System.out.println("예외발생: "+ e.getMessage());}
		
		addKeyListener(this);
		requestFocus();
		setFocusable(true);
		
		new Thread(this).start();
	}
	
	
		
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		switch(n) {
		case 0:g.drawImage(man, x, y, null); break;		//여러 그림을 움직이게 넣을 때 switch사용	
		case 1:g.drawImage(man01, x, y, null);break;
		case 2:g.drawImage(man02, x, y, null);break;
		case 3:g.drawImage(man03, x, y, null);break;
		}
		
	}


	@Override
	public void run() {
		while(true) {
			if(x < 0 || x > 450) {
				dx = -1 * dx;		// -1 부호 바꿈 ,, 벽에 닿으면 다시 부호 바꿔 움직이도록함
			}
			if(y <0 || y > (450)) {
				dy = -1 * dy;
			}
			x = x + dx;		// x 값은 dx를 움직이는 값으로 계속 바뀌어 움직이도록 함.
			y = y + dy;
			
			n++;	// 그림을 case 1씩 증가하여 다른 그림을 출력하게 함.
			if(n == 4) { //case 4가 없으니까 초기 0 값에서 다시 처음 그림을 반복함.
				n = 0;
			}
			repaint();		//paint 메소드를 불러옴.
			try {Thread.sleep(100);}catch (Exception e) {}
		}
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {		//키코드 메소드!!
		case KeyEvent.VK_LEFT:dx=-10;dy=0;break;
		case KeyEvent.VK_RIGHT:dx=10;dy=0;break;
		case KeyEvent.VK_UP:dx=0;dy=-10;break;
		case KeyEvent.VK_DOWN:dx=0;dy=10;break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


}
