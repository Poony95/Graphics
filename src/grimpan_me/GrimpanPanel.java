package grimpan_me;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
	
public class GrimpanPanel extends JPanel implements MouseListener {
	
	ArrayList<GraphicInfo> list; //사용자가 선을 그리는만큼 자료를 담으려함.(리스트로담음)
	int drawType=0;
	Color drawColor = Color.blue;	//컬러는 자료형으로 바로 사용이 가능함.
	boolean isNew;	
	private int x1=100;
	private int y1=100;
	private int x2=400;
	private int y2=100;
	
	
	public GrimpanPanel() {		//생성자 안에 작성
		addMouseListener(this); //마우스이벤트처리 담당자가 나 자신이다 this
		list = new ArrayList<GraphicInfo>(); //선이그려질때마다 선의 정보를 담으려고만듬
	}

	@Override
	public void paint(Graphics g) {
		//g.setColor(drawColorType);
		for(GraphicInfo info :list) {	//list에 저장된 것을 그래픽인포참조자료형으로 나타냄
			//g.drawLine(info.getX1(), info.getY1(), info.getX2(), info.getY2());
			int w = info.getX2() - info.getX1();
			int h = info.getY2() - info.getY1();
			int x = info.getX1();
			int y = info.getY1();
			if(info.getX1()> info.getX2()) {	//끝점이 x가 먼저 시작되면
				w = info.getX1() - info.getX2();	//반대로 작성하고
				x = info.getX2();				// 시작점을 x2로 설정해줌.
			}
			
			if(info.getY1() > info.getY2()) {
				h = info.getY1() - info.getY2();
				y = info.getY2();
			}
			//g.drawLine(x1, y1, x2, y2);
			//시작점 x1y1, 끝점x2,y2
			//g.drawRect(x, y, w, h);	//사각형 그리기
			//g.drawOval(x, y, w, h);	// 원그리기는 공식 똑같음. 
			
			g.setColor(info.getDrawColor());					//컬러가 그리기보다 앞에 나와야합니다.
			
			switch(info.getDrawType()) {
				case 0:g.drawLine(info.getX1(), info.getY1(), info.getX2(), info.getY2());break;
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
		x1 = e.getX();
		y1 = e.getY();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		list.add(new GraphicInfo(x1, y1, x2, y2,drawType,drawColor)); // 뗄 때 선의 정보를 리스트에 담겠다
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
