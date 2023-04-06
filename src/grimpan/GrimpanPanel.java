
package grimpan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GrimpanPanel extends JPanel implements MouseListener {
		
	boolean isNew;
	ArrayList<GraphicInfo> list;
	int drawType= 0;
	Color drawColor = Color.blue;
	private int x1=100;
	private int y1=100;
	private int x2=400;
	private int y2=100;
	
	public GrimpanPanel() {
		list = new ArrayList<GraphicInfo>();
		addMouseListener(this);
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		for( GraphicInfo info   :list) {
			g.setColor(info.getDrawColor());
			
			//g.drawLine(info.getX1(), info.getY1(),info.getX2(), info.getY2());
			int w = info.getX2() - info.getX1();
			int h = info.getY2() - info.getY1();
			int x = info.getX1();
			int y = info.getY1();
			if(info.getX1() > info.getX2()) {
				w = info.getX1() - info.getX2();
				x = info.getX2();
			}
			
			if(info.getY1() > info.getY2()) {
				h = info.getY1() - info.getY2();
				y = info.getY2();
			}
			
			switch(info.getDrawType()) {
				case 0:g.drawLine(info.getX1(), info.getY1(), 
					info.getX2(), info.getY2());break;
				case 1:g.drawRect(x, y, w, h);break;
				case 2:g.drawOval(x, y, w, h);break;
			}
			
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x1 = e.getX();
		y1 = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		x2 = e.getX();
		y2 = e.getY();
		list.add(new GraphicInfo(x1, y1, x2, y2,drawType,drawColor));
		isNew = true;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
















