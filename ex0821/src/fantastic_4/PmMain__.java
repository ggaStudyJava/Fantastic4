package fantastic_4;//사용되지 않습니다

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PmMain__{

	private JFrame frame;
	private JTextField textField;
	private final JButton button = new JButton("\uAC80\uC0C9");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PmMain__ window = new PmMain__();
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
	public PmMain__() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, button, 72, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, button, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, panel, -6, SpringLayout.WEST, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 6, SpringLayout.EAST, button);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -10, SpringLayout.EAST, frame.getContentPane());
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		JPanel Picturepanel = new JPanel();
		SpringLayout sl_panel_5 = new SpringLayout();
		Picturepanel.setLayout(sl_panel_5);
		JLabel picLabel = new JLabel();
		sl_panel_5.putConstraint(SpringLayout.NORTH, picLabel, 65, SpringLayout.NORTH, Picturepanel);
		sl_panel_5.putConstraint(SpringLayout.EAST, picLabel, -49, SpringLayout.EAST, Picturepanel);
		picLabel.setText("\uC0AC\uC9C4");
		Picturepanel.add(picLabel);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(310, Short.MAX_VALUE)
					.addComponent(Picturepanel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(23)
					.addComponent(Picturepanel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(238, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);
		
		JLabel nameLabel = new JLabel();
		
		nameLabel.setText("이름 : ");
		panel_2.add(nameLabel);
		
		
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 1, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -3, SpringLayout.WEST, button);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 26, SpringLayout.NORTH, frame.getContentPane());
		
		JList list = new JList();
		panel.add(list);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.getContentPane().add(button);
		
		JLabel label = new JLabel("\uC0AC\uC6D0 \uAC80\uC0C9");
		springLayout.putConstraint(SpringLayout.WEST, label, 17, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label, -4, SpringLayout.NORTH, button);
		springLayout.putConstraint(SpringLayout.EAST, label, 103, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(label);
		
	}
}

