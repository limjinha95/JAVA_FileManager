import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class CreateDir extends JFrame {

	private JPanel contentPane;
	private JTextField dirname;


	/**
	 * Create the frame.
	 */
	public CreateDir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 213, 90);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(6, 6, 201, 56);
		panel.setLayout(null);
		contentPane.add(panel);
		
		dirname = new JTextField();
		dirname.setBackground(new Color(255, 240, 245));
		dirname.setBounds(6, 12, 122, 32);
		panel.add(dirname);
		dirname.setColumns(10);
		
		JButton createbtn = new JButton("create");
		createbtn.setBounds(130, 12, 65, 32);
		panel.add(createbtn);
		
		
		createbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		CreateDir createdir = new CreateDir();
	
	}
}
