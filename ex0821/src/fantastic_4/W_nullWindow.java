package fantastic_4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class W_nullWindow {

	private JFrame frmError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					W_nullWindow window = new W_nullWindow();
					window.frmError.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public W_nullWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmError = new JFrame();
		frmError.setTitle("Error!");
		frmError.setBounds(500, 400, 266, 132);
		frmError.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmError.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 20, SpringLayout.NORTH, frmError.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 26, SpringLayout.WEST, frmError.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -23, SpringLayout.EAST, frmError.getContentPane());
		frmError.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -16, SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 55, SpringLayout.NORTH, frmError.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -10, SpringLayout.SOUTH, frmError.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 95, SpringLayout.WEST, frmError.getContentPane());
		
		JLabel label = new JLabel("이름,생일,입사일은 공백값이 허용되지 않습니다.");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label, "name_6260729542308");
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -89, SpringLayout.EAST, frmError.getContentPane());
		frmError.getContentPane().add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmError.dispose();
			}
		});
		panel_1.add(btnNewButton, "name_6227361782631");
	}
}
