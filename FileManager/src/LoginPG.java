import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

class LoginPG extends JFrame {

	private JPanel contentPane;
	private JTextField IdtextField;
	private JTextField PwtextField;
	clientConnect connectt;
	/**
	 * Create the frame.
	 */
	public LoginPG(clientConnect connect) {
		this.connectt = connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel mainpanel = new JPanel();
		mainpanel.setBackground(new Color(255, 250, 250));
		mainpanel.setBounds(6, 6, 438, 266);
		mainpanel.setLayout(null);


		JLabel LoginLabel = new JLabel("LOGIN");
		LoginLabel.setFont(new Font("Apple Chancery", Font.BOLD, 20));
		LoginLabel.setBounds(183, 33, 72, 16);
		mainpanel.add(LoginLabel);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("LingWai SC", Font.BOLD, 16));
		lblId.setBounds(88, 87, 61, 16);
		mainpanel.add(lblId);

		JLabel lblPw = new JLabel("PASSWORD");
		lblPw.setFont(new Font("LingWai SC", Font.BOLD, 16));
		lblPw.setBounds(88, 137, 78, 16);
		mainpanel.add(lblPw);

		IdtextField = new JTextField();
		IdtextField.setBounds(192, 82, 166, 26);
		mainpanel.add(IdtextField);
		IdtextField.setColumns(10);

		PwtextField = new JTextField();
		PwtextField.setBounds(192, 132, 166, 26);
		mainpanel.add(PwtextField);
		PwtextField.setColumns(10);

		JButton loginbtn = new JButton("Log-in");
		loginbtn.setFont(new Font("Hannotate SC", Font.PLAIN, 13));
		loginbtn.setBounds(115, 192, 93, 29);
		mainpanel.add(loginbtn);

		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ch = e.getActionCommand();

				try {
					if(ch.equals("Log-in")) {
						//connect = new clientConnect("127.0.0.1", 8888);
						System.out.println("로그인 성공! ");
					}
				} catch(Exception e1) {
					e1.getStackTrace();
				}
			}
		});

		JButton signupbtn = new JButton("Sign-up");
		signupbtn.setFont(new Font("Hannotate SC", Font.PLAIN, 13));
		signupbtn.setBounds(228, 192, 93, 29);
		mainpanel.add(signupbtn);


		signupbtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				SignupPG opensignup = new SignupPG(connectt);
				opensignup.setVisible(true);
			}
		});

		contentPane.add(mainpanel);
		//setVisible(true);
	}


	/**
	 * Launch the application.
	 */
	
}