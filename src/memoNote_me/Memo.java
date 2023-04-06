package memoNote_me;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Memo extends JFrame implements ActionListener {
	
	JTextArea jta;		// 텍스트 에이리어, 메모장 사용하는 클래스
	
	public Memo() {
		jta = new JTextArea();	//객체 생성 
		JScrollPane jsp = new JScrollPane(jta); //JScrollPane은 껍데기 일 뿐 , 스크롤 바 안에 내용을 담는거야..
		add(jsp);	//스크롤펜 출력
		
		JMenuBar jmb = new JMenuBar();
//-------------------------------------------------------		
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
		
		setJMenuBar(jmb);
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		new Memo();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();	// 눌러진 메뉴의 글자를 가져오는 메소드
		if(cmd.equals("새파일")) {
			
		}else if(cmd.equals("열기")){
			try {
				String fname = "c:/temp/hello.txt";
				FileReader fr = new FileReader(fname);
				int ch;
				String data = "";
				while((ch = fr.read()) != -1) {
					
					data = data + (char)ch;
				}
				fr.close();
				jta.setText(data);
			} catch (Exception e2) {
				System.out.println("예외발생:"+ e2.getMessage());
			}
		}else if(cmd.equals("저장")){		//모든 입출력은 예외처리를 가지고 있습니다.
			try {
				String data = jta.getText();	//getText 글자 가져오는 메소드 
				String fname = "c:/temp/hello.txt";
				FileWriter fw = new FileWriter(fname);
				fw.write(data);
				fw.close();
				JOptionPane.showConfirmDialog(this, "파일을 저장하였습니다");
			} catch (Exception e2) {
				System.out.println("예외처리 : "+e2.getMessage());
			}
		}else if(cmd.equals("종료")){
			System.exit(0);
		}
	}
}
