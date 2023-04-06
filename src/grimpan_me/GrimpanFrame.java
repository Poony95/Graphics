package grimpan_me;

import java.awt.Color;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
	// 메뉴 만들기
public class GrimpanFrame extends JFrame implements ActionListener{
	
	GrimpanPanel pan;		
	JColorChooser jcc;		/**여러가지 색상 나오게 하는 자바클래스*/
	JFileChooser jfc;
	String fname = "c:/temp/my.pan";
	File file;
	
	public GrimpanFrame () {
		
		setTitle("그림판");
		pan = new GrimpanPanel();	//객체를 인스턴스화하여 생성합니다.
		add(pan);	// add하여 pan객체를 추가해줍니다.
		
		jcc = new JColorChooser(Color.black);
		jfc = new JFileChooser("c:/temp"); 
		
		// 메뉴바 만들기( 메뉴바는 1개만 있어도 됩니다.)
		JMenuBar jmb = new JMenuBar();
		
		// 파일 주메뉴 만들기
		JMenu mn_file = new JMenu("파일");
		// 부메뉴 만들기
		JMenuItem file_new = new JMenuItem("새파일");
		JMenuItem file_open = new JMenuItem("열기");
		JMenuItem file_save = new JMenuItem("저장");
		JMenuItem file_close = new JMenuItem("종료");
		//메뉴에 이벤트를 등록합니다.
		file_new.addActionListener(this);
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		file_close.addActionListener(this);
		// 부메뉴들을 주메뉴에 담기
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		mn_file.add(file_close);
		// 주메뉴를 메뉴바에 담기
		jmb.add(mn_file);
				
//--------------------------------------------------------				
		
		// 그리기타입 주메뉴 만들기
		JMenu mn_draw = new JMenu("그리기 도구");
		// 부메뉴 만들기
		JMenuItem draw_line = new JMenuItem("선");
		JMenuItem draw_rect = new JMenuItem("사각형");
		JMenuItem draw_oval = new JMenuItem("원");
		
		//메뉴에 이벤트를 등록합니다.
		draw_line.addActionListener(this);
		draw_rect.addActionListener(this);
		draw_oval.addActionListener(this);
		
		// 부메뉴들을 주메뉴에 담기
		mn_draw.add(draw_line);
		mn_draw.add(draw_rect);
		mn_draw.add(draw_oval);
		
		// 주메뉴를 메뉴바에 담기
		jmb.add(mn_draw);
//--------------------------------------------------------		
		
		// 색상 주메뉴 만들기
		JMenu mn_color=new JMenu("그리기 색상");
		
		// 부메뉴 만들기
		JMenuItem color_red = new JMenuItem("빨강");
		JMenuItem color_green = new JMenuItem("초록");
		JMenuItem color_blue = new JMenuItem("파랑");
		JMenuItem color_other = new JMenuItem("다른색상...");
		
		//메뉴에 이벤트를 등록합니다.
		color_red.addActionListener(this);
		color_green.addActionListener(this);
		color_blue.addActionListener(this);
		color_other.addActionListener(this);
		
		// 부메뉴들을 주메뉴에 담기
		mn_color.add(color_red);
		mn_color.add(color_green);
		mn_color.add(color_blue);
		mn_color.add(color_other);
		
		// 주메뉴를 메뉴바에 담기
		jmb.add(mn_color);
		
		// 메뉴바를 프레임에 붙이기
		setJMenuBar(jmb);	//setJMenuBar는 메뉴바를 프레임에 붙이는 메소드입니다.
		
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//생성자 end
//--------------------------------------------------------	
	
	public void openFile() {
		try {
			int re = jfc.showOpenDialog(this);	// JfileChooser.파일을 열기하려는 메소드 
			if(re == 0) { // 0 : 열기, 1: 취소
				file = jfc.getSelectedFile();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			pan.list = (ArrayList<GraphicInfo>)ois.readObject();	//파일에서 읽어올 때 어레이리스트(그래픽인포)로 형변환 하여 pan.list안에 다시 담아야함.
			pan.repaint();
			}				
		} catch (Exception e2) {System.out.println("예외발생:"+e2.getMessage());}
	
	}
	
	
	public void saveFile() {	//저장 파일 메소드
		try {
			int re = 0;			
			if(file == null) {	//저장된 파일이 없다면
				re = jfc.showSaveDialog(this);	//저장다이어로그를 불러와라		
			}			
			if(re != 1) {		// 그리기 후 열기를 할 때 저장할지 물어보는 조건문  0:저장예, 1:저장아니오, 2:취소
				if(re == 0) {		//0:네하면
					file = jfc.getSelectedFile();	//열기파일 바로 불러오는 메소드
				}
				//객체단위로 파일에 그림을 전송하는 클래스
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(pan.list);
				oos.close();
				JOptionPane.showMessageDialog(this, "파일에 저장하였습니다.");
				pan.isNew = false;  //그림이 사라짐.. true는 있음
			}
			
			
			
		}catch (Exception e2) {
			System.out.println("예외발생:"+e2.getMessage());
		}
	}
	
	
	public static void main(String[] args) {
		new GrimpanFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();	//눌러진 부분의 글자를 출력해오는 메소드
		System.out.println(cmd);
		
		switch (cmd) {
			case "선": pan.drawType=0;break;
			case "사각형": pan.drawType=1;break;
			case "원": pan.drawType=2;break;
		}
		switch (cmd) {
			case "빨강": pan.drawColor=Color.RED;break;
			case "초록": pan.drawColor=Color.GREEN;break;
			case "파랑": pan.drawColor=Color.BLUE;break;
			case "다른색상...": pan.drawColor = jcc.showDialog(this, "색상선택", Color.black);
		}
		switch (cmd) {
		case "새파일":
			if(pan.isNew == true) {
			int re = JOptionPane.showConfirmDialog(this, "저장하시겠습니까?");
			if(re == 2 ) { // 예:0, 아니오:1, 취소:2
				return;
			}
			if(re == 0) { 
				saveFile();
			}
			System.out.println(re);
			}
			pan.list.clear();	//list에 저장되어있는 현재 그림지우기
			pan.repaint();		//새파일만들기위해 페인트 도구 다시 초기화로 나오게함.
			break;
		case "열기":
			if(pan.isNew = true){
			int re = JOptionPane.showConfirmDialog(this, "변경된 내용을 저장하시겠습니까?");
				if(re == 2) {
					return;
				}
				if(re == 0) {
					saveFile();
				}
			} 
		openFile(); break;
		case "저장": saveFile();break;
		case "종료": System.exit(0);break;	//아무 숫자나 넣어주면 됩니다.
		
		}
	}
}
