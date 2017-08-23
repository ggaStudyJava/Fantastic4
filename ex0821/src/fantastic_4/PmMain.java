package fantastic_4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.SpringLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JTable;

public class PmMain {

	private JFrame frame;
	private JTextField txt_findworker;
	private JTextField txt_idnum;
	private JTextField txt_name;
	private JTextField txt_birth;
	private JTextField txt_joindate;
	private JTextField txt_phoneNum;
	private JTextField txt_address;
	private JTextField cbb_accounts;
	private JTextField txt_license;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PmMain window = new PmMain();
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
	public PmMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280,1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_search = new JPanel();
		
		JPanel panel_list = new JPanel();
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_search, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_list, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(panel_search, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_list, GroupLayout.PREFERRED_SIZE, 903, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel_list.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 169, 34);
		
		txt_findworker = new JTextField();
		txt_findworker.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		txt_findworker.setColumns(10);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(txt_findworker, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(txt_findworker, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
		);
		panel_4.setLayout(gl_panel_4);
		panel_list.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(280, 0, -114, 34);
		panel_list.add(panel_5);
		
		JButton btn_find = new JButton("New button");
		btn_find.setBounds(171, 0, 113, 34);
		panel_list.add(btn_find);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 44, 262, 34);
		panel_list.add(panel_6);
		panel_6.setLayout(new CardLayout(0, 0));
		
		JComboBox cbb_sorts = new JComboBox();
		cbb_sorts.setBounds(10, 44, 262, 34);
		panel_6.add(cbb_sorts);
		panel_6.add(cbb_sorts, "name_10358438746288");
		
		JList list_name = new JList();
		list_name.setBounds(0, 89, 284, 814);
		panel_list.add(list_name);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
		);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel pnl_photo = new JPanel();
		pnl_photo.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_photo.setBounds(712, 81, 172, 218);
		panel_1.add(pnl_photo);
		
		JLabel txt_photo = new JLabel("\uC0AC\uC9C4");
		GroupLayout gl_pnl_photo = new GroupLayout(pnl_photo);
		gl_pnl_photo.setHorizontalGroup(
			gl_pnl_photo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_photo.createSequentialGroup()
					.addGap(69)
					.addComponent(txt_photo)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		gl_pnl_photo.setVerticalGroup(
			gl_pnl_photo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_photo.createSequentialGroup()
					.addGap(102)
					.addComponent(txt_photo)
					.addContainerGap(102, Short.MAX_VALUE))
		);
		pnl_photo.setLayout(gl_pnl_photo);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_8.setBounds(63, 81, 146, 28);
		panel_1.add(panel_8);
		
		JLabel label = new JLabel("\uC774              \uB984");
		panel_8.add(label);
		
		JPanel panel_9 = new JPanel();
		panel_9.setForeground(Color.BLACK);
		panel_9.setBounds(221, 80, 284, 29);
		panel_1.add(panel_9);
		
		
		panel_9.setLayout(new CardLayout(0, 0));
		
		txt_name = new JTextField();
		panel_9.add(txt_name, "name_9424152680863");
		txt_name.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_10.setBounds(63, 119, 146, 28);
		panel_1.add(panel_10);
		
		JLabel lblNewLabel_1 = new JLabel("\uC0AC              \uBC88");
		panel_10.add(lblNewLabel_1);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBounds(221, 119, 284, 28);
		panel_1.add(panel_11);
		panel_11.setLayout(new CardLayout(0, 0));
		
		txt_idnum = new JTextField();
		panel_11.add(txt_idnum, "name_9510506508163");
		txt_idnum.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_12.setBounds(63, 157, 146, 28);
		panel_1.add(panel_12);
		
		JLabel lblNewLabel_2 = new JLabel("\uC0DD  \uB144  \uC6D4  \uC77C");
		panel_12.add(lblNewLabel_2);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(221, 157, 284, 28);
		panel_1.add(panel_13);
		panel_13.setLayout(null);
		
		txt_birth = new JTextField();
		txt_birth.setBounds(0, 0, 284, 28);
		panel_13.add(txt_birth);
		txt_birth.setColumns(10);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_14.setBounds(63, 195, 146, 28);
		panel_1.add(panel_14);
		
		JLabel label_1 = new JLabel("\uC785     \uC0AC     \uC77C");
		panel_14.add(label_1);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(221, 195, 284, 28);
		panel_1.add(panel_15);
		panel_15.setLayout(null);
		
		txt_joindate = new JTextField();
		txt_joindate.setBounds(0, 0, 284, 28);
		panel_15.add(txt_joindate);
		txt_joindate.setColumns(10);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_16.setBounds(63, 233, 146, 28);
		panel_1.add(panel_16);
		
		JLabel label_2 = new JLabel("\uC5F0     \uB77D     \uCC98");
		panel_16.add(label_2);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBounds(221, 233, 284, 28);
		panel_1.add(panel_17);
		panel_17.setLayout(null);
		
		txt_phoneNum = new JTextField();
		txt_phoneNum.setBounds(0, 0, 284, 28);
		panel_17.add(txt_phoneNum);
		txt_phoneNum.setColumns(10);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_18.setBounds(63, 271, 146, 28);
		panel_1.add(panel_18);
		
		JLabel label_3 = new JLabel("\uC8FC              \uC18C");
		panel_18.add(label_3);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBounds(221, 271, 284, 61);
		panel_1.add(panel_19);
		panel_19.setLayout(null);
		
		txt_address = new JTextField();
		txt_address.setBounds(0, 0, 284, 61);
		panel_19.add(txt_address);
		txt_address.setColumns(10);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_21.setBounds(63, 342, 146, 28);
		panel_1.add(panel_21);
		
		JLabel label_4 = new JLabel("\uBD80              \uC11C");
		panel_21.add(label_4);
		
		JPanel panel_22 = new JPanel();
		panel_22.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_22.setBounds(63, 380, 146, 28);
		panel_1.add(panel_22);
		
		JLabel lblNewLabel_4 = new JLabel("\uC9C1              \uCC45");
		panel_22.add(lblNewLabel_4);
		
		JPanel panel_23 = new JPanel();
		panel_23.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.setBounds(63, 414, 146, 28);
		panel_1.add(panel_23);
		
		JLabel label_5 = new JLabel("\uACC4  \uC88C  \uBC88  \uD638");
		panel_23.add(label_5);
		
		JPanel panel_24 = new JPanel();
		panel_24.setBounds(437, 414, 439, 28);
		panel_1.add(panel_24);
		panel_24.setLayout(null);
		
		cbb_accounts = new JTextField();
		cbb_accounts.setBounds(0, 0, 439, 28);
		panel_24.add(cbb_accounts);
		cbb_accounts.setColumns(10);
		
		JPanel panel_25 = new JPanel();
		panel_25.setBounds(221, 414, 204, 29);
		panel_1.add(panel_25);
		panel_25.setLayout(null);
		
		JComboBox cbb_banklist = new JComboBox();
		cbb_banklist.setBounds(0, 0, 204, 29);
		panel_25.add(cbb_banklist);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_26.setBounds(63, 452, 146, 29);
		panel_1.add(panel_26);
		
		JLabel label_6 = new JLabel("\uC790  \uACA9  \uC0AC  \uD56D");
		panel_26.add(label_6);
		
		JPanel panel_27 = new JPanel();
		panel_27.setBounds(221, 452, 663, 252);
		panel_1.add(panel_27);
		panel_27.setLayout(null);
		
		txt_license = new JTextField();
		txt_license.setBounds(0, 0, 663, 252);
		panel_27.add(txt_license);
		txt_license.setColumns(10);
		
		JPanel panel_29 = new JPanel();
		panel_29.setBounds(221, 342, 284, 28);
		panel_1.add(panel_29);
		panel_29.setLayout(null);
		
		JComboBox cbb_division = new JComboBox();
		cbb_division.setBounds(0, 0, 284, 28);
		panel_29.add(cbb_division);
		
		JPanel panel_30 = new JPanel();
		panel_30.setBounds(221, 380, 284, 28);
		panel_1.add(panel_30);
		panel_30.setLayout(null);
		
		JComboBox cbb_position = new JComboBox();
		cbb_position.setBounds(0, 0, 284, 28);
		panel_30.add(cbb_position);
		
		JPanel panel_31 = new JPanel();
		panel_31.setBounds(445, 789, 136, 28);
		panel_1.add(panel_31);
		
		JButton btn_newworker = new JButton("\uC2E0 \uADDC \uB4F1 \uB85D");//½Å±Ôµî·Ï
		btn_newworker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout gl_panel_31 = new GroupLayout(panel_31);
		gl_panel_31.setHorizontalGroup(
			gl_panel_31.createParallelGroup(Alignment.LEADING)
				.addComponent(btn_newworker, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
		);
		gl_panel_31.setVerticalGroup(
			gl_panel_31.createParallelGroup(Alignment.LEADING)
				.addComponent(btn_newworker, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
		);
		panel_31.setLayout(gl_panel_31);
		
		JPanel panel_32 = new JPanel();
		panel_32.setBounds(596, 789, 136, 28);
		panel_1.add(panel_32);
		
		JButton btn_update = new JButton("\uC218        \uC815");//¼öÁ¤
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_panel_32 = new GroupLayout(panel_32);
		gl_panel_32.setHorizontalGroup(
			gl_panel_32.createParallelGroup(Alignment.LEADING)
				.addComponent(btn_update, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
		);
		gl_panel_32.setVerticalGroup(
			gl_panel_32.createParallelGroup(Alignment.LEADING)
				.addComponent(btn_update, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
		);
		panel_32.setLayout(gl_panel_32);
		
		JPanel panel_33 = new JPanel();
		panel_33.setBounds(744, 789, 136, 28);
		panel_1.add(panel_33);
		
		JButton btn_quit = new JButton("\uD1F4        \uC0AC");//Åð»ç
		btn_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_panel_33 = new GroupLayout(panel_33);
		gl_panel_33.setHorizontalGroup(
			gl_panel_33.createParallelGroup(Alignment.LEADING)
				.addComponent(btn_quit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
		);
		gl_panel_33.setVerticalGroup(
			gl_panel_33.createParallelGroup(Alignment.LEADING)
				.addComponent(btn_quit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
		);
		panel_33.setLayout(gl_panel_33);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBounds(63, 81, 146, 28);
		panel_2.add(panel_20);
		
		JLabel label_7 = new JLabel("\uC774            \uB984");
		panel_20.add(label_7);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(221, 81, 286, 28);
		panel_2.add(panel_7);
		
		JPanel panel_28 = new JPanel();
		panel_28.setBounds(63, 119, 146, 28);
		panel_2.add(panel_28);
		
		JPanel panel_34 = new JPanel();
		panel_34.setBounds(221, 119, 286, 28);
		panel_2.add(panel_34);
		
		JPanel panel_35 = new JPanel();
		panel_35.setBounds(519, 81, 146, 28);
		panel_2.add(panel_35);
		
		JPanel panel_36 = new JPanel();
		panel_36.setBounds(677, 81, 255, 28);
		panel_2.add(panel_36);
		
		JPanel panel_37 = new JPanel();
		panel_37.setBounds(63, 157, 869, 358);
		panel_2.add(panel_37);
		panel_37.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 869, 358);
		panel_37.add(table);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 944, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 921, Short.MAX_VALUE)
		);
		panel_3.setLayout(gl_panel_3);
		panel.setLayout(gl_panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		panel_search.add(lblNewLabel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
