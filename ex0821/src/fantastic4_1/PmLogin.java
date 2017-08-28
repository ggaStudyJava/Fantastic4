 package fantastic4_1;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JPasswordField;

public class PmLogin {

	private static PmLoginDAO pmLogin;
	private JFrame frame;
	private JTextField txtfieldId;
	private JPasswordField passwordField;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		pmLogin = new PmLoginDAO("admin", "1234");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PmLogin window = new PmLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PmLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	// id 체크 메소드
	public void checkId(){	
		System.out.println(pmLogin.getId());
		System.out.println(txtfieldId.getText());
		System.out.println(new String(passwordField.getPassword()));
		System.out.println(pmLogin.getPw());
		if (txtfieldId.getText().equals(pmLogin.getId())
				&& new String(passwordField.getPassword()).equals(pmLogin.getPw())) {
			System.out.println("로그인성공");
			CreateTable ct = new CreateTable();

			frame.dispose();
			PmMain pmMain = new PmMain();
			pmMain.main(null); // 메인창 호출
		} else {
			W_ErrWindow err = new W_ErrWindow();
			System.out.println("로그인실패");
			err.main(null);			
			txtfieldId.setText("");
			passwordField.setText("");
		}
	}

	private void initialize() {

		frame = new JFrame();
		frame.setTitle("로그인");
		frame.setBounds(500, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 48, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 182, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 80, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -52, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel);

		JPanel panelId = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelId, 48, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelId, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelId, 80, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelId, 165, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panelId);

		JPanel panelPw = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelPw, 16, SpringLayout.SOUTH, panelId);
		springLayout.putConstraint(SpringLayout.WEST, panelPw, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelPw, 48, SpringLayout.SOUTH, panelId);

		JLabel labelId = new JLabel("ID");
		panelId.add(labelId);
		springLayout.putConstraint(SpringLayout.EAST, panelPw, 165, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panelPw);

		JPanel panel_3 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_3, 16, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_3, 17, SpringLayout.EAST, panelPw);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_3, 0, SpringLayout.SOUTH, panelPw);

		JLabel labelPw = new JLabel("PASSWORD");
		panelPw.add(labelPw);
		springLayout.putConstraint(SpringLayout.EAST, panel_3, 0, SpringLayout.EAST, panel);

		panel.setLayout(null);

		txtfieldId = new JTextField();
		txtfieldId.setBounds(0, 0, 200, 32);
		panel.add(txtfieldId);
		txtfieldId.setColumns(10);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setBounds(0, 0, 200, 32);
		panel_3.add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {
			// 텍스트필드에 발생한 이벤트(엔터 입력) 감지

			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == 10)	checkId();
			}
		});

		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -82, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, panel_2);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 59, SpringLayout.EAST, panel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, panel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, -56, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 69, SpringLayout.WEST, frame.getContentPane());

		// 관리자로그인
		JButton loginButton = new JButton("\uAD00\uB9AC\uC790\uB85C\uADF8\uC778");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkId();
			}
		});

		panel_1.add(loginButton);
		frame.getContentPane().add(panel_2);

		JButton buttonCreate = new JButton("TempButton");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_2.add(buttonCreate);
	}
	
	
}
