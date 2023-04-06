package dongwan;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DongwanPanel extends JPanel implements Runnable, KeyListener {

	BufferedImage dongwan;
	BufferedImage taco;
	int x=0, y=0;
	int dx=0, dy=0;
	
	
	
	public DongwanPanel () {
		try {
			dongwan = ImageIO.read(new File("dongwan.png"));
			taco = ImageIO.read(new File("taco.png"));
			
		} catch (Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
		addKeyListener(this);	//키 리스너 생성자에 넣어야 작동함.
		requestFocus();			// 이거랑
		setFocusable(true);		// 이거는 세트로 keyListener add 할 때 넣어줘야함.
		
		new Thread(this).start();	// runnable Thread로 변환하여 객체 생성 후 start();
	
	}

	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(taco,0,0,null);
		g.drawImage(dongwan, x, y, null); 
		
	}



	@Override
	public void run() {
		while(true) {
			if(x < 0 || x > 700) {
				dx = -1  * dx;
			}
			if(y < 0 || y > 700) {
				dy = -1  * dy;
			}
			x += dx;
			y += dy;
			repaint();
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
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
