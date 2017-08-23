package fantastic_4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PmLogin {

	private JFrame frame;
	private JTextField txtfieldId;
	private JTextField txtfieldPw;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
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
		
		txtfieldId = new JTextField();
		panel.add(txtfieldId);
		txtfieldId.setColumns(10);
		frame.getContentPane().add(panel_3);
		
		txtfieldPw = new JTextField();
		panel_3.add(txtfieldPw);
		txtfieldPw.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -82, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, panel_2);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 59, SpringLayout.EAST, panel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, panel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, -56, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 69, SpringLayout.WEST, frame.getContentPane());
		
		JButton loginButton = new JButton("\uAD00\uB9AC\uC790\uB85C\uADF8\uC778");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 6, SpringLayout.SOUTH, panelPw);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, -139, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, 32, SpringLayout.SOUTH, panelPw);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -133, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(passwordField);
	}
}
