package memoNote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Memo extends JFrame implements ActionListener{	
	JTextArea jta;	
	public Memo() {
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		add(jsp);
		
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
		
		setJMenuBar(jmb);
		
		
		setSize(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Memo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("새파일")) {
			
		}else if(cmd.equals("열기")) {
			try {
				String fname = "c:/temp/hello.txt";
				FileReader fr = new FileReader(fname);
				int ch;
				String data= "";
				while( (ch = fr.read()) != -1  ) {
					data= data+ (char)ch;
				}
				fr.close();
				jta.setText(data);
			}catch (Exception ex) {
				System.out.println("예외발생:"+ex.getMessage());
			}
		}else if(cmd.equals("저장")) {
			try {
				String data = jta.getText();
				String fname = "c:/temp/hello.txt";
				FileWriter fw = new FileWriter(fname);
				fw.write(data);
				fw.close();
				JOptionPane.showMessageDialog(this, "파일을 저장하였습니다.");				
			}catch (Exception ex) {
				System.out.println("예외발생:"+ex.getMessage());
			}
		}else if(cmd.equals("종료")) {
			
		}
	}
}














