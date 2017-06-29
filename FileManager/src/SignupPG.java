import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

class SignupPG extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JPasswordField pwd;
	private JPasswordField pwd1;
	private JTextField repoField;
	JButton checkBtn;
	private JButton repoCheck;
	clientConnect connectt;
	int idChecknum, repoChecknum;

	/**
	 * Create the frame.
	 */

	public SignupPG(clientConnect connect) {
		/* UI */
		this.connectt = connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		panel.setBounds(6, 6, 438, 266);
		panel.setLayout(null);
		contentPane.add(panel);

		JLabel lblSignup = new JLabel("SIGN-UP");
		lblSignup.setFont(new Font("Apple Chancery", Font.BOLD, 20));
		lblSignup.setBounds(168, 22, 102, 16);
		panel.add(lblSignup);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("LingWai SC", Font.BOLD, 16));
		lblId.setBounds(69, 66, 61, 16);
		panel.add(lblId);

		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("LingWai SC", Font.BOLD, 16));
		lblName.setBounds(69, 94, 61, 16);
		panel.add(lblName);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("LingWai SC", Font.BOLD, 16));
		lblPassword.setBounds(69, 122, 85, 16);
		panel.add(lblPassword);

		JLabel lblPasswordCheck = new JLabel("PASSWORD CHECK");
		lblPasswordCheck.setFont(new Font("LingWai SC", Font.BOLD, 16));
		lblPasswordCheck.setBounds(69, 150, 116, 16);
		panel.add(lblPasswordCheck);

		JLabel lblNewLabel = new JLabel("REPOSITORY");
		lblNewLabel.setFont(new Font("LingWai SC", Font.BOLD, 16));
		lblNewLabel.setBounds(69, 178, 85, 16);
		panel.add(lblNewLabel);

		idField = new JTextField();
		idField.setBounds(205, 61, 130, 26);
		panel.add(idField);
		idField.setColumns(10);

		nameField = new JTextField();
		nameField.setBounds(205, 89, 130, 26);
		panel.add(nameField);
		nameField.setColumns(10);

		pwd = new JPasswordField();
		pwd.setBounds(205, 117, 130, 26);
		panel.add(pwd);

		pwd1 = new JPasswordField();
		pwd1.setBounds(205, 145, 130, 26);
		panel.add(pwd1);

		repoField = new JTextField();
		repoField.setBounds(205, 173, 130, 26);
		panel.add(repoField);
		repoField.setColumns(10);

		checkBtn = new JButton("check");
		checkBtn.setFont(new Font("LingWai SC", Font.BOLD, 15));
		checkBtn.setBounds(338, 61, 50, 29);
		panel.add(checkBtn);

		repoCheck = new JButton("check");
		repoCheck.setFont(new Font("LingWai SC", Font.BOLD, 15));
		repoCheck.setBounds(338, 173, 50, 29);
		panel.add(repoCheck);

		JButton btnSignup = new JButton("sign-up");
		btnSignup.setFont(new Font("Hannotate TC", Font.PLAIN, 13));
		btnSignup.setBounds(220, 220, 85, 29);
		panel.add(btnSignup);

		JButton btnCancel = new JButton("cancel");
		btnCancel.setFont(new Font("Hannotate TC", Font.PLAIN, 13));
		btnCancel.setBounds(125, 220, 85, 29);
		panel.add(btnCancel);

		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setVisible(false);
			}
		});
		btnSignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String getId = idField.getText();
				String getName = nameField.getText();
				String getPwd = new String(pwd.getPassword());
				String getPwd2 = new String(pwd1.getPassword());
				String getRepo = repoField.getText();

				if (!pwdCheck(getPwd, getPwd2)) { //패스워드 체크 
					JOptionPane.showMessageDialog(null, "패스워드를 체크해 주세요", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					String msg = join_in(idChecknum, repoChecknum, getId, getName, getPwd, getRepo);
					System.out.println(msg);
					if (msg.equals("True"))
						JOptionPane.showMessageDialog(null, "가입을 축하합니다.", "OK", JOptionPane.PLAIN_MESSAGE);
					else if(msg.equals("False")){
						JOptionPane.showMessageDialog(null, "가입 실패", "ERROR", JOptionPane.ERROR_MESSAGE);

					}
				}
			}
		});
		/* 사용 가능한 아이디 이면 idChecknum = 1 */
		idCheck();
		/* 사용 가능한 아이디 이면 repoChecknum = 1 */
		repositoryCheck();

	}

	public boolean pwdCheck(String str, String str2) {
		// pwd.ad
		if (str.equals(str2)) {
			return true;
		}
		return false;
	}

	public void repositoryCheck() {
		repoCheck.addMouseListener(new MouseAdapter() {
			String repo = "", msg = "";

			public void mouseReleased(MouseEvent e) {
				repo = repoField.getText();
				System.out.println("Repo," + repo);
				connectt.sendData("Repo," + repo);
				msg = connectt.getData();
				System.out.println(msg);
				
				if (msg.equals("True")) {
					System.out.println("repsitory 사용 가능");
					repoChecknum = 1;
					JOptionPane.showMessageDialog(null, "Repository 를 사용하실수 있습니다", "OK", JOptionPane.PLAIN_MESSAGE);
				} else if (msg.equals("False")) {
					JOptionPane.showMessageDialog(null, "Repository 를 사용하실수 없습니다", "Warning",
							JOptionPane.ERROR_MESSAGE);
					repoChecknum = 0;
					System.out.println("repsitory 사용 불가");
				} else {
					System.out.println("검사 안함");
				}
			}
		});
	}

	public void idCheck() {
		checkBtn.addMouseListener(new MouseAdapter() {
			String id = "", msg = "";
			int num;

			@Override
			public void mouseReleased(MouseEvent e) {
				id = idField.getText();
				System.out.println("ID," + id);
				connectt.sendData("ID," + id);
				msg = connectt.getData();
				System.out.println(msg);

				if (msg.equals("True")) {
					System.out.println("id 사용 가능");
					idChecknum = 1;
					JOptionPane.showMessageDialog(null, "ID를 사용하실수 있습니다", "OK", JOptionPane.PLAIN_MESSAGE);
				} else if (msg.equals("False")) {
					JOptionPane.showMessageDialog(null, "ID를 사용하실수 없습니다", "Warning", JOptionPane.ERROR_MESSAGE);
					idChecknum = 0;
					System.out.println("id 사용 불가");

				} else {
					System.out.println("검사 안함");
				}
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public String join_in(int idNum, int repoNum, String id, String name, String pwd, String repository) {
		String msg = null;
		if (idNum == 1 && repoNum == 1) {
			String join = "Join," + id + ',' + name + ',' + pwd + ',' + repository;
			System.out.println("join :" + join);
			connectt.sendData(join);
			msg = connectt.getData();
		}
		return msg;
	}

}
