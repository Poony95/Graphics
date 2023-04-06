package grimpan;

import java.awt.Color;
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

public class GrimpanFrame extends JFrame implements ActionListener{

	GrimpanPanel pan;
	JColorChooser jcc;
	JFileChooser jfc;
	String fname = "c:/temp/my.pan";
	File file;
	public GrimpanFrame() {
				
		setTitle("그림판");
		pan = new GrimpanPanel();
		add(pan);
		
		jcc = new JColorChooser(Color.black);
		jfc = new JFileChooser("c:/temp");
		
		//메뉴바 만들기
		JMenuBar jmb = new JMenuBar();
		
		JMenu mn_file = new JMenu("파일");
		
		JMenuItem file_new = new JMenuItem("새파일");
		JMenuItem file_open = new JMenuItem("열기");
		JMenuItem file_save = new JMenuItem("저장");
		JMenuItem file_exit = new JMenuItem("종료");
		
		file_new.addActionListener(this);
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		file_exit.addActionListener(this);
		
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		mn_file.add(file_exit);
		
		jmb.add(mn_file);
		
		
		//주메뉴만들기
		JMenu mn_draw = new JMenu("그리기도구");
		
		//부메뉴만들기
		JMenuItem draw_line = new JMenuItem("선");
		JMenuItem draw_rect = new JMenuItem("사각형");
		JMenuItem draw_oval = new JMenuItem("원");		
		
		//메뉴에 이벤트를 등록합니다.
		draw_line.addActionListener(this);
		draw_rect.addActionListener(this);
		draw_oval.addActionListener(this);
		
		
		//부메뉴들을 주메뉴에 담기
		mn_draw.add(draw_line);
		mn_draw.add(draw_rect);
		mn_draw.add(draw_oval);
		
		//주메뉴를 메뉴바에 담기
		jmb.add(mn_draw);
		
		//색상에 대한 메뉴만들기
		JMenu mn_color= new JMenu("그리기색상");
		//눌러진 색상으로 그래픽이 표현되도록 코드를 완성해 봅니다.
		JMenuItem color_red = new JMenuItem("빨강");
		JMenuItem color_green = new JMenuItem("초록");
		JMenuItem color_blue = new JMenuItem("파랑");
		JMenuItem color_other = new JMenuItem("다른색상...");
		
		
		color_red.addActionListener(this);
		color_green.addActionListener(this);
		color_blue.addActionListener(this);
		color_other.addActionListener(this);
		
		mn_color.add(color_red);
		mn_color.add(color_green);
		mn_color.add(color_blue);
		mn_color.add(color_other);
		
		jmb.add(mn_color);
		
		//메뉴바를 프레임에 붙이기
		setJMenuBar(jmb);
				
		setSize(600, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void openFile() {
		try {
			int re = jfc.showOpenDialog(this);
			if(re == 0) {
				file = jfc.getSelectedFile();
				ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(file));
				pan.list = (ArrayList<GraphicInfo>)ois.readObject();
				pan.repaint();
			}
		}catch (Exception e2) {
			System.out.println("예외발생:"+e2.getMessage());
		}
	}
	
	public void saveFile() {
		try {						
			int re = 9;			
			if(file == null) {
				re = jfc.showSaveDialog(this);				
			}			
			if(re != 1) {
				if(re == 0) {
					file = jfc.getSelectedFile();
				}
				ObjectOutputStream oos 
				= new ObjectOutputStream( new FileOutputStream(file));
				oos.writeObject( pan.list );
				oos.close();
				JOptionPane.showMessageDialog(this, 
						"파일에 저장하였습니다.");
				pan.isNew = false;
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
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		
		switch(cmd) {
			case "선":pan.drawType=0;break;
			case "사각형":pan.drawType=1;break;
			case "원":pan.drawType=2;break;
		}
		
		switch(cmd) {
			case "빨강":pan.drawColor=Color.red;break;
			case "초록":pan.drawColor=Color.green;break;
			case "파랑":pan.drawColor=Color.blue;break;
			case "다른색상...":
				pan.drawColor = jcc.showDialog(this,
								"색상선택", Color.black);
				break;
		}
		
		
		switch(cmd) {
			case "새파일":
				if(pan.isNew == true) {
					int re =JOptionPane.showConfirmDialog(this, "저장하시겠습니까?");
					if(re == 2) {
						return;
					}
					if(re == 0) {
						saveFile();
					}
					//예:0,아니오:1,취소:2
				}
				pan.list.clear();
				pan.repaint();
				break;
			case "열기":
				if(pan.isNew == true) {
					int re =  JOptionPane.showConfirmDialog(this, "변경된 내용을 저장하시겠습니까?");
					//예:0,아니오:1,취소:2
					if(re == 2) {
						return;
					}
					if(re == 0) {
						saveFile();
					}
				}
				openFile();
				break;
			case "저장":saveFile();break;
				
			case "종료":
				System.exit(0);
				break;
		}
	}

}








