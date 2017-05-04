import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;

public class SignupPG extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_2;



	/**
	 * Create the frame.
	 */
	public SignupPG() {
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
		
		textField = new JTextField();
		textField.setBounds(205, 61, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(205, 89, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 117, 130, 26);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(205, 145, 130, 26);
		panel.add(passwordField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(205, 173, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("check");
		btnNewButton.setFont(new Font("LingWai SC", Font.BOLD, 15));
		btnNewButton.setBounds(338, 61, 50, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("check");
		btnNewButton_1.setFont(new Font("LingWai SC", Font.BOLD, 15));
		btnNewButton_1.setBounds(338, 173, 50, 29);
		panel.add(btnNewButton_1);
		
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
		
		
		//setVisible(true);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SignupPG signuppg = new SignupPG();
	}
}
