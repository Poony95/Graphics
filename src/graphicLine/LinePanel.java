package graphicLine;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LinePanel extends JPanel implements MouseListener{

	int x1 = 100;
	int y1 = 100;
	int x2 = 400;
	int y2 = 100;
	
	ArrayList<GraphicInfo> list;
	
	public LinePanel() {
		list = new ArrayList<GraphicInfo>();
		addMouseListener(this);
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//g.drawLine(x1, y1, x2, y2);
		for(GraphicInfo info  : list ) {
			g.drawLine(info.getX1(), info.getY1(),
					info.getX2(), info.getY2());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("마우스 클릭");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("마우스 눌러짐");
		x1 = e.getX();
		y1 = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("마우스 떼어짐");
		x2 = e.getX();
		y2 = e.getY();
		list.add(new GraphicInfo(x1, y1, x2, y2));
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("마우스 들어옴");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("마우스 나감");
	}
	
}









