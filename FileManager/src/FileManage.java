import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileManage extends JFrame {
	
	Socket s;
	DataOutputStream dou;
	
	private JFrame rmdir_dialog = new JFrame();
	JButton rmdirbtn;
	JButton okBtn;
	JButton cancelBtn;
	private JTextField filepath;
	
	public FileManage() {
		this.Manage();
	}

	public void Manage() {
		getContentPane().setFont(new Font("Apple Chancery", Font.BOLD, 17));
		setTitle("파일 매니져");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.PINK);
		contentPane.setLayout(null);

		JPanel backpanel = new JPanel();
		backpanel.setBackground(Color.white);
		backpanel.setBounds(6, 6, 688, 466);
		backpanel.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), null));
		panel1.setBackground(new Color(255, 250, 250));
		panel1.setBounds(0, 0, 212, 85);
		panel1.setLayout(null);
		backpanel.add(panel1);

		JPanel logout_panel = new JPanel();
		logout_panel.setBackground(new Color(255, 192, 203));
		logout_panel.setBounds(6, 6, 198, 73);
		logout_panel.setLayout(null);
		panel1.add(logout_panel);

		JLabel label = new JLabel("로그인이 필요합니다.");
		label.setFont(new Font("Heiti SC", Font.PLAIN, 13));
		label.setBounds(63, 26, 115, 16);
		logout_panel.add(label);

		ImageIcon account = new ImageIcon("/Users/limjinha/Desktop/스크린샷 2017-05-03 오후 3.07.30.png");

		JButton accountbtn = new JButton(account);

		LoginPG openlogin = new LoginPG();

		accountbtn.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseReleased(MouseEvent e) {
				openlogin.setVisible(true);
			}
		});

		accountbtn.setLocation(21, 21);
		accountbtn.setSize(30, 30);
		logout_panel.add(accountbtn);

		JPanel panel2 = new JPanel();
		panel2.setBorder(null);
		panel2.setBackground(new Color(255, 250, 250));
		panel2.setBounds(211, 0, 477, 56);
		panel2.setLayout(null);
		backpanel.add(panel2);
		
		JPanel titlepanel = new JPanel();
		titlepanel.setBackground(new Color(255, 182, 193));
		titlepanel.setBounds(6, 6, 465, 44);
		titlepanel.setLayout(null);
		panel2.add(titlepanel);
		
		filepath = new JTextField();
		filepath.setBounds(18, 5, 305, 33);
		titlepanel.add(filepath);
		filepath.setColumns(10);
		
		JFileChooser fileChooser = new JFileChooser();    
		
		JButton filesearchbtn = new JButton("search");
		
		filesearchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == filesearchbtn) {
		            int returnVal = fileChooser.showOpenDialog(contentPane);
		            
		            if( returnVal == JFileChooser.APPROVE_OPTION) {
		                //열기 버튼을 누르면
		                File file = fileChooser.getSelectedFile();
		                filepath.setText(file.toString());
		            }
		            else {
		                //취소 버튼을 누르면
		                return;
		            }
		        }
			}
		});
		
		filesearchbtn.setBounds(330, 8, 68, 29);
		titlepanel.add(filesearchbtn);
		
		JButton btnUpload = new JButton("upload");
		btnUpload.setBounds(391, 8, 68, 29);
		titlepanel.add(btnUpload);
		
		// 클라이언트 파일 업로드 부분 
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cmd = e.getActionCommand();
				
				try {
					if(cmd.equals("upload")) {
						System.out.println("connecting...");
						//s = new Socket("127.0.0.1", 7777);
						System.out.println("file uploading...");
						dou = new DataOutputStream(s.getOutputStream());
					}
				} catch(Exception e1) {}
		       
			}
		});
		//btnUpload.setEnabled(false);
		
		

		DefaultMutableTreeNode Root = new DefaultMutableTreeNode("Root");

		DefaultMutableTreeNode Photo = new DefaultMutableTreeNode("Photo");
		DefaultMutableTreeNode Music = new DefaultMutableTreeNode("Music");
		DefaultMutableTreeNode Video = new DefaultMutableTreeNode("Video");

		Root.add(Photo);
		Root.add(Video);
		Root.add(Music);

		JTree filetree = new JTree(Root);
		filetree.setBorder(null);
		filetree.setBackground(new Color(255, 255, 255));
		filetree.setShowsRootHandles(true);
		
		JScrollPane scrollPane1 = new JScrollPane(filetree);
		scrollPane1.setBounds(0, 86, 212, 350);
		backpanel.add(scrollPane1);
		scrollPane1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(212, 86, 477, 380);
		scrollPane2.setLayout(null);
		backpanel.add(scrollPane2);
		getContentPane().add(backpanel);
		
		JPanel panel3 = new JPanel();
		panel3.setBorder(null);
		panel3.setBackground(Color.WHITE);
		panel3.setBounds(0, 435, 212, 31);
		panel3.setLayout(null);
		backpanel.add(panel3);
		
		JButton mkdirbtn = new JButton("+");
		mkdirbtn.setBounds(153, 6, 19, 19);
		panel3.add(mkdirbtn);
		
		CreateDir createdir = new CreateDir();
		
		mkdirbtn.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseReleased(MouseEvent e) {
				createdir.setVisible(true);
			}
		});
		
		rmdirbtn = new JButton("-");
		rmdirbtn.setBounds(180, 6, 19, 19);
		panel3.add(rmdirbtn);
		
		JPanel panel4 = new JPanel();
		panel4.setBackground(new Color(255, 250, 250));
		panel4.setBounds(211, 55, 477, 31);
		backpanel.add(panel4);
		rmdirbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "폴더를 삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION) {
					// 폴더 삭제 
				}
				else if(result == JOptionPane.NO_OPTION) {
					// 취소 
				}
			}
		});
		
	
		
		
		
		setSize(700, 500);
		setVisible(true);
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FileManage MP = new FileManage();
	}

	
}


