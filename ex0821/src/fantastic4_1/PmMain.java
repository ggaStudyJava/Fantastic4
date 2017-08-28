package fantastic4_1;

//수정되지 않은 문제는 ****으로 찾으면 됨
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
//import com.toedter.calendar.JDayChooser;
//import com.toedter.calendar.JCalendar;
//import com.toedter.calendar.JMonthChooser;
//import com.toedter.calendar.JYearChooser;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

public class PmMain {

	private JFrame frame;
	private JTextField txt_findworker;
	private JTextField txt_idnum;
	private JTextField txt_name;
	private JTextField txt_birth;
	private JTextField txt_joindate;
	private JTextField txt_phoneNum;
	private JTextField txt_address;
	private JTextField txt_accounts;
	private JTextField txt_license;
	private JTable table;
	private JTextField txt_OverWorkTime;
	private JList list_name;
	boolean insert = false;
	private boolean setEditable;
	String selected;
	int yy;
	int mm;
	int dd;

	PersonDAO person = new PersonDAO();
	//PaymentDAO pay = new PaymentDAO();

	DefaultTableModel model;
	Calendar cal = new GregorianCalendar();
	JLabel label;
	JPanel panel_37;
	private DefaultListModel sortbyname;
	protected boolean dlperson = false;
	private JComboBox cbb_division;
	private JComboBox cbb_position;
	private JComboBox cbb_banklist;

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
		frame.getContentPane().setBackground(Color.WHITE);

		// frame.setBounds(100, 100, 1280, 1024);

		frame.setBounds(300, 0, 1280, 1024);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel_search = new JPanel();
		panel_search.setBackground(Color.WHITE);

		JPanel panel_list = new JPanel();
		panel_list.setBackground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_search, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_list, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(21)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(panel_search, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_list, GroupLayout.PREFERRED_SIZE, 903, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(15, Short.MAX_VALUE)));
		panel_list.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 169, 34);

		txt_findworker = new JTextField();
		txt_findworker.setFont(new Font("굴림", Font.PLAIN, 18));
		txt_findworker.setColumns(10);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addComponent(txt_findworker,
				GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addComponent(txt_findworker,
				GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE));
		panel_4.setLayout(gl_panel_4);
		panel_list.add(panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(280, 0, -114, 34);
		panel_list.add(panel_5);

		JButton btn_find = new JButton("\uAC80  \uC0C9");

		btn_find.setBounds(171, 0, 113, 34);
		panel_list.add(btn_find);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 44, 262, 34);
		panel_list.add(panel_6);
		panel_6.setLayout(new CardLayout(0, 0));

		JComboBox cbb_sorts = new JComboBox();
		cbb_sorts.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				// System.out.println(e.getStateChange());

				ArrayList<PersonVO> sortbyp;
				if (e.getItem() == "이름별 정렬") {
					sortbyname.clear();
					sortbyp = person.sortName();

					for (PersonVO personvo : sortbyp) {
						sortbyname.addElement(personvo.getName());
					}
				}
				if (e.getItem() == "부서별정렬") {
					sortbyname.clear();
					sortbyp = person.sortDivision();

					for (PersonVO personvo : sortbyp) {
						sortbyname.addElement(personvo.getName());
					}
				}
				if (e.getItem() == "직책별 정렬") {
					sortbyname.clear();
					sortbyp = person.sortPosition();
					for (PersonVO personvo : sortbyp) {
						sortbyname.addElement(personvo.getName());
					}
				}
				if (e.getItem() == "입사일순 정렬") {
					sortbyname.clear();
					sortbyp = person.sortJoinDate();

					for (PersonVO personvo : sortbyp) {
						sortbyname.addElement(personvo.getName());
					}
				}

			}
		});
		cbb_sorts.setModel(new DefaultComboBoxModel(
				new String[] { "\uC774\uB984\uBCC4 \uC815\uB82C", "\uBD80\uC11C\uBCC4 \uC815\uB82C",
						"\uC9C1\uCC45\uC21C \uC815\uB82C", "\uC785\uC0AC\uC77C\uC21C \uC815\uB82C" }));
		cbb_sorts.setBounds(10, 44, 262, 34);
		panel_6.add(cbb_sorts);
		panel_6.add(cbb_sorts, "name_10358438746288");

		sortbyname = new DefaultListModel<>();
		PersonDAO person = new PersonDAO();
		ArrayList<PersonVO> sortbynamep = person.sortName();

		for (PersonVO personvo : sortbynamep) {
			sortbyname.addElement(personvo.getName());
		}
		list_name = new JList(sortbyname);
		list_name.setBackground(SystemColor.inactiveCaptionBorder);

		list_name.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_name.setToolTipText("");
		list_name.setBounds(0, 89, 284, 593);
		panel_list.add(list_name);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(tabbedPane,
				Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("\uC0AC\uC6D0 \uC815\uBCF4", null, panel_1, null);
		// panel_1.
		panel_1.setLayout(null);

		JPanel pnl_photo = new JPanel();
		pnl_photo.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_photo.setBounds(712, 81, 172, 218);
		panel_1.add(pnl_photo);

		JLabel txt_photo = new JLabel("\uC0AC\uC9C4");
		GroupLayout gl_pnl_photo = new GroupLayout(pnl_photo);
		gl_pnl_photo.setHorizontalGroup(gl_pnl_photo.createParallelGroup(Alignment.LEADING).addGroup(gl_pnl_photo
				.createSequentialGroup().addGap(69).addComponent(txt_photo).addContainerGap(79, Short.MAX_VALUE)));
		gl_pnl_photo.setVerticalGroup(gl_pnl_photo.createParallelGroup(Alignment.LEADING).addGroup(gl_pnl_photo
				.createSequentialGroup().addGap(102).addComponent(txt_photo).addContainerGap(102, Short.MAX_VALUE)));
		pnl_photo.setLayout(gl_pnl_photo);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
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
		panel_10.setBackground(Color.WHITE);
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
		panel_12.setBackground(Color.WHITE);
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
		panel_14.setBackground(Color.WHITE);
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
		panel_16.setBackground(Color.WHITE);
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
		panel_18.setBackground(Color.WHITE);
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
		panel_21.setBackground(Color.WHITE);
		panel_21.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_21.setBounds(63, 342, 146, 28);
		panel_1.add(panel_21);

		JLabel label_4 = new JLabel("\uBD80              \uC11C");
		panel_21.add(label_4);

		JPanel panel_22 = new JPanel();
		panel_22.setBackground(Color.WHITE);
		panel_22.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_22.setBounds(63, 380, 146, 28);
		panel_1.add(panel_22);

		JLabel lblNewLabel_4 = new JLabel("\uC9C1              \uCC45");
		panel_22.add(lblNewLabel_4);

		JPanel panel_23 = new JPanel();
		panel_23.setBackground(Color.WHITE);
		panel_23.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.setBounds(63, 414, 146, 28);
		panel_1.add(panel_23);

		JLabel label_5 = new JLabel("\uACC4  \uC88C  \uBC88  \uD638");
		panel_23.add(label_5);

		JPanel panel_24 = new JPanel();
		panel_24.setBounds(437, 414, 439, 28);
		panel_1.add(panel_24);
		panel_24.setLayout(null);

		txt_accounts = new JTextField();
		txt_accounts.setBounds(0, 0, 439, 28);
		panel_24.add(txt_accounts);
		txt_accounts.setColumns(10);

		JPanel panel_25 = new JPanel();
		panel_25.setBounds(221, 414, 204, 29);
		panel_1.add(panel_25);
		panel_25.setLayout(null);

		cbb_banklist = new JComboBox();
		cbb_banklist.setModel(new DefaultComboBoxModel(new String[] { "\uC2E0\uD55C\uC740\uD589",
				"\uAD6D\uBBFC\uC740\uD589", "\uAD11\uC8FC\uC740\uD589", "\uC6B0\uCCB4\uAD6D", "\uB18D\uD611" }));
		cbb_banklist.setBounds(0, 0, 204, 29);
		panel_25.add(cbb_banklist);

		JPanel panel_26 = new JPanel();
		panel_26.setBackground(Color.WHITE);
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

		cbb_division = new JComboBox();
		cbb_division.setModel(new DefaultComboBoxModel(new String[] { "\uD68C\uACC4\uD300",
				"\uC0AC\uC5C5\uC81C\uC548\uD300", "\uC601\uC5C51\uD300", "\uBD80\uC124\uC5F0\uAD6C\uC18C" }));
		cbb_division.setBounds(0, 0, 284, 28);
		panel_29.add(cbb_division);

		JPanel panel_30 = new JPanel();
		panel_30.setBounds(221, 380, 284, 28);
		panel_1.add(panel_30);
		panel_30.setLayout(null);

		cbb_position = new JComboBox();
		cbb_position.setModel(new DefaultComboBoxModel(new String[] { "\uC0AC\uC6D0", "\uC8FC\uC784", "\uB300\uB9AC",
				"\uACFC\uC7A5", "\uCC28\uC7A5", "\uBD80\uC7A5", "\uC0C1\uBB34\uC774\uC0AC", "\uC0AC\uC7A5" }));
		cbb_position.setBounds(0, 0, 284, 28);
		panel_30.add(cbb_position);

		JPanel panel_31 = new JPanel();
		panel_31.setBounds(600, 342, 136, 28);
		panel_1.add(panel_31);

		JButton btn_newworker = new JButton("\uC2E0 \uADDC \uB4F1 \uB85D");// 신규등록

		GroupLayout gl_panel_31 = new GroupLayout(panel_31);
		gl_panel_31.setHorizontalGroup(gl_panel_31.createParallelGroup(Alignment.LEADING).addComponent(btn_newworker,
				GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE));
		gl_panel_31.setVerticalGroup(gl_panel_31.createParallelGroup(Alignment.LEADING).addComponent(btn_newworker,
				GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE));
		panel_31.setLayout(gl_panel_31);

		JPanel panel_32 = new JPanel();
		panel_32.setBounds(600, 380, 136, 28);
		panel_1.add(panel_32);

		JButton btn_update = new JButton("\uC218        \uC815");// 수정

		GroupLayout gl_panel_32 = new GroupLayout(panel_32);
		gl_panel_32.setHorizontalGroup(gl_panel_32.createParallelGroup(Alignment.LEADING).addComponent(btn_update,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE));
		gl_panel_32.setVerticalGroup(gl_panel_32.createParallelGroup(Alignment.LEADING).addComponent(btn_update,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE));
		panel_32.setLayout(gl_panel_32);

		JPanel panel_33 = new JPanel();
		panel_33.setBounds(748, 380, 136, 28);
		panel_1.add(panel_33);

		JButton btn_quit = new JButton("\uD1F4        \uC0AC");// 퇴사

		GroupLayout gl_panel_33 = new GroupLayout(panel_33);
		gl_panel_33.setHorizontalGroup(gl_panel_33.createParallelGroup(Alignment.LEADING).addComponent(btn_quit,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE));
		gl_panel_33.setVerticalGroup(gl_panel_33.createParallelGroup(Alignment.LEADING).addComponent(btn_quit,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE));
		panel_33.setLayout(gl_panel_33);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("\uADFC\uBB34 \uAD00\uB9AC", null, panel_2, null);
		panel_2.setLayout(null);

		JPanel panel_20 = new JPanel();
		panel_20.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_20.setBounds(63, 81, 146, 28);
		panel_2.add(panel_20);

		JLabel label_7 = new JLabel("\uC774            \uB984");
		panel_20.add(label_7);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBounds(221, 81, 255, 28);
		panel_2.add(panel_7);

		JLabel lbl_name = new JLabel("name");
		panel_7.add(lbl_name);

		JPanel panel_28 = new JPanel();
		panel_28.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_28.setBounds(63, 119, 146, 28);
		panel_2.add(panel_28);

		JLabel label_8 = new JLabel("\uC0AC  \uC6D0  \uBC88  \uD638");
		panel_28.add(label_8);

		JPanel panel_34 = new JPanel();
		panel_34.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_34.setBounds(221, 119, 255, 28);
		panel_2.add(panel_34);

		JLabel lbl_num = new JLabel("num");
		panel_34.add(lbl_num);

		JPanel panel_35 = new JPanel();
		panel_35.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_35.setBounds(488, 81, 146, 28);
		panel_2.add(panel_35);

		JLabel label_9 = new JLabel("\uBD80            \uC11C");
		panel_35.add(label_9);

		JPanel panel_36 = new JPanel();
		panel_36.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_36.setBounds(646, 81, 255, 28);
		panel_2.add(panel_36);

		JLabel lbl_div = new JLabel("div");
		panel_36.add(lbl_div);

		panel_37 = new JPanel();
		panel_37.setBounds(63, 157, 838, 358);

		panel_2.add(panel_37);
		panel_37.setLayout(null);

		JCalendar calendar = new JCalendar();
		calendar.setBounds(0, 0, 838, 358);
		panel_37.add(calendar);

		JPanel panel_38 = new JPanel();
		panel_38.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_38.setBounds(488, 119, 146, 28);
		panel_2.add(panel_38);

		JLabel label_10 = new JLabel("\uC9C1            \uCC45");
		panel_38.add(label_10);

		JPanel panel_39 = new JPanel();
		panel_39.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_39.setBounds(646, 119, 255, 28);
		panel_2.add(panel_39);

		JLabel lbl_Posi = new JLabel("position");
		panel_39.add(lbl_Posi);

		JPanel panel_40 = new JPanel();
		panel_40.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_40.setBounds(108, 542, 126, 28);
		panel_2.add(panel_40);

		JLabel label_11 = new JLabel("\uCD08\uACFC\uADFC\uBB34\uC2DC\uAC04");
		panel_40.add(label_11);

		JPanel panel_41 = new JPanel();
		panel_41.setBounds(252, 542, 146, 28);
		panel_2.add(panel_41);
		panel_41.setLayout(null);

		txt_OverWorkTime = new JTextField();
		txt_OverWorkTime.setBounds(0, 0, 146, 28);
		panel_41.add(txt_OverWorkTime);
		txt_OverWorkTime.setColumns(10);

		JPanel panel_42 = new JPanel();
		panel_42.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_42.setBounds(495, 542, 126, 28);
		panel_2.add(panel_42);

		JLabel label_12 = new JLabel("\uB204\uC801\uCD08\uACFC\uADFC\uBB34\uC2DC\uAC04");
		panel_42.add(label_12);

		JPanel panel_43 = new JPanel();
		panel_43.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_43.setBounds(638, 542, 146, 28);
		panel_2.add(panel_43);

		JLabel lbl_sumoverworktime = new JLabel("n");
		panel_43.add(lbl_sumoverworktime);

		JButton btnNewButton = new JButton("\uB4F1\uB85D");
		btnNewButton.setBounds(409, 542, 67, 28);

		panel_2.add(btnNewButton);

		JPanel panel_44 = new JPanel();
		panel_44.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_44.setBounds(108, 580, 126, 28);
		panel_2.add(panel_44);

		JLabel label_13 = new JLabel("\uCD08\uACFC\uADFC\uBB34\uC218\uB2F9");
		panel_44.add(label_13);

		JPanel panel_45 = new JPanel();
		panel_45.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_45.setBounds(495, 580, 126, 28);
		panel_2.add(panel_45);

		JLabel label_14 = new JLabel("\uB204\uC801\uCD08\uACFC\uADFC\uBB34\uC218\uB2F9");
		panel_45.add(label_14);

		JPanel panel_46 = new JPanel();
		panel_46.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_46.setBounds(252, 580, 146, 28);
		panel_2.add(panel_46);

		JLabel lbl_overworkpay = new JLabel("n");
		panel_46.add(lbl_overworkpay);

		JPanel panel_47 = new JPanel();
		panel_47.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_47.setBounds(638, 580, 146, 28);
		panel_2.add(panel_47);

		JLabel lbl_sumoverworkpay = new JLabel("n");
		panel_47.add(lbl_sumoverworkpay);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("\uC5F0\uBD09 \uAD00\uB9AC", null, panel_3, null);
		
		JPanel panel_49 = new JPanel();
		panel_49.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_15 = new JLabel("\uC774            \uB984");
		panel_49.add(label_15);
		
		JPanel panel_50 = new JPanel();
		panel_50.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_16 = new JLabel("\uC0AC  \uC6D0  \uBC88  \uD638");
		panel_50.add(label_16);
		
		JPanel panel_51 = new JPanel();
		panel_51.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_17 = new JLabel("\uBD80            \uC11C");
		panel_51.add(label_17);
		
		JPanel panel_52 = new JPanel();
		panel_52.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_18 = new JLabel("\uC9C1            \uCC45");
		panel_52.add(label_18);
		
		JPanel panel_53 = new JPanel();
		panel_53.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		// 연봉관리 초기화면 
												
		JLabel lbl_Name3 = new JLabel("lbl_Name3");
		panel_53.add(lbl_Name3);
		lbl_Name3.setText(" ");
		
		JPanel panel_54 = new JPanel();
		panel_54.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_Idnum3 = new JLabel("lbl_Idnum3");
		panel_54.add(lbl_Idnum3);
		lbl_Idnum3.setText(" ");
		
		JPanel panel_55 = new JPanel();
		panel_55.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_Division3 = new JLabel("lbl_Division3");
		panel_55.add(lbl_Division3);
		lbl_Division3.setText(" ");
		
		JPanel panel_56 = new JPanel();
		panel_56.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_Position3 = new JLabel("lbl_Position3");
		panel_56.add(lbl_Position3);
		lbl_Position3.setText(" ");
		
		JPanel panel_57 = new JPanel();
		panel_57.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_23 = new JLabel("\uAE30     \uBCF8     \uAE09");
		panel_57.add(label_23);
		
		JPanel panel_58 = new JPanel();
		panel_58.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_BasePay3 = new JLabel("lbl_Pay3");
		panel_58.add(lbl_BasePay3);
		lbl_BasePay3.setText(" ");
		
		JPanel panel_59 = new JPanel();
		panel_59.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_24 = new JLabel("\uC138           \uC728");
		panel_59.add(label_24);
		
		JPanel panel_60 = new JPanel();
		panel_60.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_Tax3 = new JLabel("lbl_Tax3");
		panel_60.add(lbl_Tax3);
		lbl_Tax3.setText(" ");
		
		JPanel panel_61 = new JPanel();
		panel_61.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_overPay3 = new JLabel("lbl_overPay3");
		panel_61.add(lbl_overPay3);
		lbl_overPay3.setText(" ");
		
		JPanel panel_62 = new JPanel();
		panel_62.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_27 = new JLabel("\uCD08\uACFC\uADFC\uBB34\uC218\uB2F9");
		panel_62.add(label_27);
		
		JPanel panel_63 = new JPanel();
		panel_63.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_realPay3 = new JLabel("lbl_realPay3");
		panel_63.add(lbl_realPay3);
		lbl_realPay3.setText(" ");
		
		JPanel panel_64 = new JPanel();
		panel_64.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_29 = new JLabel("\uC2E4 \uC218 \uB839 \uAE08");
		panel_64.add(label_29);
		
		JPanel panel_65 = new JPanel();
		panel_65.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_30 = new JLabel("\uD1F4    \uC9C1    \uAE08");
		panel_65.add(label_30);
		
		JPanel panel_66 = new JPanel();
		panel_66.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_outPay3 = new JLabel("lbl_outPay3");
		panel_66.add(lbl_outPay3);
		lbl_outPay3.setText(" ");
		
		JPanel panel_67 = new JPanel();
		panel_67.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_yearPay3 = new JLabel("lbl_yearPay3");
		panel_67.add(lbl_yearPay3);
		lbl_yearPay3.setText(" ");
		
		JPanel panel_68 = new JPanel();
		panel_68.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_33 = new JLabel("\uC5F0            \uBD09");
		panel_68.add(label_33);
		
		JPanel panel_69 = new JPanel();
		panel_69.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_19 = new JLabel("\uCD08\uACFC\uADFC\uBB34\uC2DC\uAC04");
		panel_69.add(label_19);
		
		JPanel panel_70 = new JPanel();
		panel_70.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lbl_overTime = new JLabel(" ");
		panel_70.add(lbl_overTime);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		
		gl_panel_3.setHorizontalGroup(
				gl_panel_3.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_3.createSequentialGroup()
						.addGap(45)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(panel_62, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(panel_61, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(panel_69, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(panel_70, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(panel_64, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(panel_63, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(panel_68, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(panel_67, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(panel_65, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(panel_66, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_3.createSequentialGroup()
										.addComponent(panel_59, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(panel_60, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_3.createSequentialGroup()
										.addComponent(panel_57, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(panel_58, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_3.createSequentialGroup()
										.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
											.addComponent(panel_52, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addComponent(panel_49, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addComponent(panel_50, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addComponent(panel_51, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
											.addComponent(panel_53, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
											.addComponent(panel_54, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
											.addComponent(panel_56, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
											.addComponent(panel_55, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))))))
						.addGap(486))
			);
			gl_panel_3.setVerticalGroup(
				gl_panel_3.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_3.createSequentialGroup()
						.addGap(81)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(panel_53, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(panel_54, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(panel_49, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addComponent(panel_50, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_51, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_55, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_52, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_56, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(34)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_57, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_58, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_59, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_60, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(90)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_69, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_70, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_62, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_61, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(82)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_64, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_63, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(258)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_65, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_66, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_68, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_67, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(362, Short.MAX_VALUE))
			);
			panel_3.setLayout(gl_panel_3);

		JPanel panel_48 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_48, null);
		panel.setLayout(gl_panel);

		JLabel lblNewLabel = new JLabel("Fantastic4 \uC778\uC0AC\uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_search.add(lblNewLabel);
		frame.getContentPane().setLayout(groupLayout);
		txtsIsEnabled(false);

		list_name.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!dlperson) { //
					setEditable = false;
					selected = list_name.getSelectedValue().toString();
					ArrayList<PersonVO> showperson = person.getpersoninfo(selected);

					txt_idnum.setText(showperson.get(0).getIdNum() + "");
					// txt_idnum.setEditable(false);
					lbl_num.setText(showperson.get(0).getIdNum() + "");
					txt_name.setText(showperson.get(0).getName());
					// txt_name.setEditable(false);
					lbl_name.setText(showperson.get(0).getName() + "");

					txt_birth.setText(showperson.get(0).getBirthDate() + "");
					// txt_birth.setEditable(false);

					txt_joindate.setText(showperson.get(0).getJoinDate() + "");
					// txt_joindate.setEditable(false);
					txt_phoneNum.setText(showperson.get(0).getPhoneNum());
					// txt_phoneNum.setEditable(false);
					txt_address.setText(showperson.get(0).getAddress());
					// txt_address.setEditable(false);
					cbb_position.setSelectedItem(showperson.get(0).getPosition());
					// cbb_position.setEnabled(false);
					lbl_Posi.setText(showperson.get(0).getPosition());
					cbb_division.setSelectedItem(showperson.get(0).getDivision());
					// cbb_division.setEnabled(false);
					lbl_div.setText(showperson.get(0).getDivision());
					int index = showperson.get(0).getAccountNum().indexOf(" ");
					String tmpbank = showperson.get(0).getAccountNum().substring(0, index);
					cbb_banklist.setSelectedItem(tmpbank);
					tmpbank = showperson.get(0).getAccountNum().substring(index + 1);
					txt_accounts.setText(tmpbank);
					// cbb_banklist.setEnabled(false);
					// txt_accounts.setEnabled(false);
					txt_license.setText(showperson.get(0).getLicense());
					// txt_license.setEditable(false);
					lbl_Name3.setText(showperson.get(0).getName());	// 이름 
					lbl_Idnum3.setText(showperson.get(0).getIdNum()+"");	// 사번
					lbl_Division3.setText(showperson.get(0).getDivision());	// 부서
					lbl_Position3.setText(showperson.get(0).getPosition());	// 직책
					
					// 3페이지 급여관리
					PaymentDAO payment = new PaymentDAO();
					lbl_Tax3.setText(payment.getTax()+"");
					lbl_overPay3.setText(payment.getOverPay()+"");
					lbl_outPay3.setText(payment.getOutPay()+"");
					// 기본급
					int basePayment = 0;
					switch(showperson.get(0).getPosition()){
					case "사원":
						basePayment = payment.getSawonPay();
						break;
					case "주임":
						basePayment = payment.getJuimPay();
						break;
					case "대리":
						basePayment = payment.getDaeriPay();
						break;
					case "과장":
						basePayment = payment.getGwajangPay();
						break;
					case "차장":
						basePayment = payment.getChajangPay();
						break;
					case "부장":
						basePayment = payment.getBujangPay();
						break;
					case "상무이사":
						basePayment = payment.getEsaPay();
						break;
					case "사장":
						basePayment = payment.getSajangPay();
						break;												
					}
					lbl_BasePay3.setText(basePayment+"");
					
					lbl_realPay3.setText(payment.getRealPay(basePayment)+"");
					lbl_outPay3.setText(payment.getOutPay()+"");
					lbl_yearPay3.setText(payment.getYearPay()+"");
					
					//PaymentDAO payment = new PaymentDAO(showperson.get(0).getDivision());
				
				
				
				
				} else {

				}

			}
		});

		btn_newworker.addActionListener(new ActionListener() {// 신규등록
			public void actionPerformed(ActionEvent arg0) {
				//버튼의 기능이 2개(입력 준비, 등록)이므로 불린으로 기능을 전환한다.
				
				txtsIsEnabled(true);
				if (insert){insert=false;}
				else{insert=true;}

				
				

				if (insert) {
					txtsclear();
					btn_newworker.setText("신규등록 확인");
				} else {
					if (txt_name != null && txt_birth != null && txt_joindate != null) {
						String tmpaddress = txt_address.getText().toString();
						String tmpbirth = txt_birth.getText().toString();
//						int tmpidnum = Integer.parseInt(txt_idnum.getText().toString());
						String tmpjoindate = txt_birth.getText().toString();
						String tmplicense = txt_license.getText().toString();
						String tmpname = txt_name.getText().toString();
						String tmpphone = txt_phoneNum.getText().toString();
						String tmpdiv = cbb_division.getSelectedItem().toString();
						String tmppos = cbb_position.getSelectedItem().toString();
						String tmpacc = cbb_banklist.getSelectedItem().toString();
						tmpacc = tmpacc + " " + txt_accounts.getText().toString();
						System.out.println(tmpaddress + tmpbirth + tmpjoindate + tmplicense + tmpname
								+ tmpphone + tmpdiv + tmppos + tmpacc);
						person.insertPerson(tmpname, tmpbirth, tmpaddress, tmpphone, tmpdiv, tmppos,
								tmplicense, tmpacc, tmpjoindate);
						
						
						W_popupWindow pop = new W_popupWindow();
						pop.main(null);
						btn_newworker.setText("신 규 등 록");

						sortbyname.clear();
						ArrayList<PersonVO> sortbyp;
						sortbyp = person.sortName();

						for (PersonVO personvo : sortbyp) {
							sortbyname.addElement(personvo.getName());
						}
					}
				}
			}
		});

		btn_update.addActionListener(new ActionListener() { //수정 버튼 부분
			public void actionPerformed(ActionEvent e) {

				String tmpAddress = txt_address.getText().toString();
				String tmpBirth = txt_birth.getText().toString();
				int tmpIdNum = Integer.parseInt(txt_idnum.getText().toString());
				String tmpJoinDate = txt_birth.getText().toString();
				String tmpLicense = txt_license.getText().toString();
				String tmpName = txt_name.getText().toString();
				String tmpPhone = txt_phoneNum.getText().toString();
				String tmpDiv = cbb_division.getSelectedItem().toString();
				String tmpPos = cbb_position.getSelectedItem().toString();
				String tmpAcc = cbb_banklist.getSelectedItem().toString();
				tmpAcc = tmpAcc + txt_accounts.getText().toString();
				if (tmpName == null || tmpJoinDate == null || tmpBirth == null) {
					W_nullWindow nl = new W_nullWindow();
					nl.main(null);
					// ****처리안됨
				} else {
					person.updatePerson(tmpIdNum, tmpAddress, tmpDiv, tmpPos, tmpLicense, tmpAcc, tmpPhone);
				}
			}
		});

		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exception = false;
				String tmpfind = txt_findworker.getText();
				int parsedtmpfind = 0;
				try {
					parsedtmpfind = Integer.parseInt(tmpfind);
				} catch (NumberFormatException e) {
					exception = true;
				}

				sortbyname.clear();

				if (exception) {
					// 우선 한가지값으로 검색하도록 함
					// exception의 초기값은 false인데 true가 된 경우 예외가 발생한 것으로 처리(문자열로만
					// 구성 된 경우)
					// 여전히 false인 경우 숫자열로만 구성됨>사원번호로 검색

					ArrayList<PersonVO> vo = person.searchPerson(tmpfind);// String
																			// name으로
																			// 검색

					for (PersonVO personvo : vo) {
						// sortbyname.addElement(personvo.getName());
						sortbyname.addElement(personvo.getName());
					}
				} else {
					// idnum으로 검색
					ArrayList<PersonVO> vo = person.searchPerson(parsedtmpfind);// intNum
																				// 로
																				// 검색

					for (PersonVO personvo : vo) {
						// sortbyname.addElement(personvo.getName());
						sortbyname.addElement(personvo.getName());
					}

				}

				// if(parsedtmpfind!=null){
				// System.out.println("String");
				//// person.searchPerson(IdNum, birthDate, name);
				// }
				// else{System.out.println("Int");}
			}
		});

		btn_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 선택된 사람을 삭제
				dlperson = true;//
				int tmpidnum = Integer.parseInt(txt_idnum.getText().toString());
				System.out.println("2");
				person.deletePerson(tmpidnum);
				System.out.println("3");
				sortbyname.clear();
				ArrayList<PersonVO> sortbyp;
				sortbyp = person.sortName();
				txtsIsEnabled(false);

				for (PersonVO personvo : sortbyp) {
					sortbyname.addElement(personvo.getName());
				}
				txtsclear();

				W_DelWindow del = new W_DelWindow();
				del.main(null);
				dlperson = false;
			}
		});

		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {// 달력에
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				final Calendar c = (Calendar) e.getNewValue();
				// ****YYYY,MM(MM-1),DD를 입력받아 PaymentDAO에 넘김
				// PaymentDAO에서는 해당년월로 컬럼을 생성하고, 컬럼이 존재하는경우 해당컬럼에 초과근무시간 누적
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				System.out.println(c.get(Calendar.MONTH));
				System.out.println(c.get(Calendar.YEAR));

			}
		});

		btnNewButton.addActionListener(new ActionListener() {// 추가근무시간 등록
			public void actionPerformed(ActionEvent arg0) {
				// 관리자 이름과 비밀번호를 입력해야 추가근무시간을 더해줄지 확인하는 대화상자를 열지 말지 생각해보자
				MonthRecordDAO monthdao = new MonthRecordDAO(yy, mm);
				
				int overworktime = Integer.parseInt(txt_OverWorkTime.getText().toString());
				lbl_sumoverworktime
						.setText(monthdao.accumulateOverWorkTime(person.getPersonIdNum(selected), overworktime) + "");
				lbl_overworkpay.setText(monthdao.overWorkPay(overworktime) + "");
				lbl_sumoverworkpay.setText(monthdao.sumoverWorkPay() + "");
			}
		});

	}

	public void txtsIsEnabled(boolean enables) {
		if (enables) {
			txt_accounts.setEnabled(true);
			txt_address.setEnabled(true);
			txt_birth.setEnabled(true);
			txt_joindate.setEnabled(true);
			txt_license.setEnabled(true);
			txt_name.setEnabled(true);
			txt_phoneNum.setEnabled(true);
			cbb_banklist.setEnabled(true);
			cbb_division.setEnabled(true);
			cbb_banklist.setEnabled(true);
		} else {
			txt_accounts.setEnabled(false);
			txt_address.setEnabled(false);
			txt_birth.setEnabled(false);
			txt_idnum.setEditable(false);
			txt_joindate.setEnabled(false);
			txt_license.setEnabled(false);
			txt_name.setEnabled(false);
			txt_phoneNum.setEnabled(false);
			cbb_banklist.setEnabled(false);
			cbb_division.setEnabled(false);
			cbb_banklist.setEnabled(false);
			cbb_position.setEnabled(false);
		}
	}

	public void txtsclear() {
		txt_accounts.setText("");
		txt_address.setText("");
		txt_birth.setText("");
		txt_idnum.setText("");
		txt_joindate.setText("");
		txt_license.setText("");
		txt_name.setText("");
		txt_phoneNum.setText("");
	}

	public void SwingCalendar() {

		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JButton b1 = new JButton("<-");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, -1);
				updateMonth();
			}
		});

		JButton b2 = new JButton("->");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, +1);
				updateMonth();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(b1, BorderLayout.WEST);
		panel.add(label, BorderLayout.CENTER);
		panel.add(b2, BorderLayout.EAST);

		String[] columns = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		model = new DefaultTableModel(null, columns);
		JTable table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);

		panel_37.add(panel, BorderLayout.NORTH);
		panel_37.add(pane, BorderLayout.CENTER);

		this.updateMonth();

	}

	void updateMonth() {

		label = new JLabel();
		cal.set(Calendar.DAY_OF_MONTH, 1);

		String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		int year = cal.get(Calendar.YEAR);
		label.setText(month + " " + year);

		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

		model.setRowCount(0);
		model.setRowCount(weeks);

		int i = startDay - 1;
		for (int day = 1; day <= numberOfDays; day++) {
			model.setValueAt(day, i / 7, i % 7);
			i = i + 1;
		}
	}
}

// package fantastic_4;
//
//// 수정되지 않은 문제는 ****으로 찾으면 됨
// import java.awt.BorderLayout;
// import java.awt.CardLayout;
// import java.awt.Color;
// import java.awt.EventQueue;
// import java.awt.Font;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.ItemEvent;
// import java.awt.event.ItemListener;
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.GregorianCalendar;
// import java.util.Locale;
//
// import javax.swing.DefaultComboBoxModel;
// import javax.swing.DefaultListModel;
// import javax.swing.GroupLayout;
// import javax.swing.GroupLayout.Alignment;
// import javax.swing.JButton;
// import javax.swing.JComboBox;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JList;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTabbedPane;
// import javax.swing.JTable;
// import javax.swing.JTextField;
// import javax.swing.LayoutStyle.ComponentPlacement;
// import javax.swing.SwingConstants;
// import javax.swing.border.EtchedBorder;
// import javax.swing.border.LineBorder;
// import javax.swing.border.TitledBorder;
// import javax.swing.table.DefaultTableModel;
//
// import javax.swing.ListSelectionModel;
// import javax.swing.AbstractListModel;
// import javax.swing.event.ListSelectionListener;
// import javax.swing.event.ListSelectionEvent;
//// import com.toedter.calendar.JDayChooser;
//// import com.toedter.calendar.JCalendar;
//// import com.toedter.calendar.JMonthChooser;
//// import com.toedter.calendar.JYearChooser;
//
// public class PmMain {
//
// private JFrame frame;
// private JTextField txt_findworker;
// private JTextField txt_idnum;
// private JTextField txt_name;
// private JTextField txt_birth;
// private JTextField txt_joindate;
// private JTextField txt_phoneNum;
// private JTextField txt_address;
// private JTextField cbb_accounts;
// private JTextField txt_license;
// private JTable table;
// private JTextField txt_OverWorkTime;
// private JList list_name;
// String selected;
//
// PersonDAO person = new PersonDAO();
//
// DefaultTableModel model;
// Calendar cal = new GregorianCalendar();
// JLabel label;
// JPanel panel_37;
//
// /**
// * Launch the application.
// */
// public static void main(String[] args) {
// EventQueue.invokeLater(new Runnable() {
// public void run() {
// try {
// PmMain window = new PmMain();
// window.frame.setVisible(true);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// });
// }
//
// /**
// * Create the application.
// */
// public PmMain() {
// initialize();
// }
//
// /**
// * Initialize the contents of the frame.
// */
// private void initialize() {
// frame = new JFrame();
//
// // frame.setBounds(100, 100, 1280, 1024);
//
// frame.setBounds(300, 0, 1280, 1024);
//
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
// JPanel panel_search = new JPanel();
//
// JPanel panel_list = new JPanel();
//
// JPanel panel = new JPanel();
// GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
// groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
// .createSequentialGroup().addContainerGap()
// .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
// .addComponent(panel_search, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
// GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
// .addComponent(panel_list, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 284,
// Short.MAX_VALUE))
// .addPreferredGap(ComponentPlacement.RELATED)
// .addComponent(panel, GroupLayout.DEFAULT_SIZE, 949,
// Short.MAX_VALUE).addContainerGap()));
// groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
// .createSequentialGroup().addGap(21)
// .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
// .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
// GroupLayout.DEFAULT_SIZE,
// Short.MAX_VALUE)
// .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
// .addComponent(panel_search, GroupLayout.PREFERRED_SIZE, 41,
// GroupLayout.PREFERRED_SIZE)
// .addPreferredGap(ComponentPlacement.RELATED)
// .addComponent(panel_list, GroupLayout.PREFERRED_SIZE, 903,
// GroupLayout.PREFERRED_SIZE)))
// .addContainerGap(15, Short.MAX_VALUE)));
// panel_list.setLayout(null);
//
// JPanel panel_4 = new JPanel();
// panel_4.setBounds(0, 0, 169, 34);
//
// txt_findworker = new JTextField();
// txt_findworker.setFont(new Font("굴림", Font.PLAIN, 18));
// txt_findworker.setColumns(10);
// GroupLayout gl_panel_4 = new GroupLayout(panel_4);
// gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addComponent(txt_findworker,
// GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE));
// gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addComponent(txt_findworker,
// GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE));
// panel_4.setLayout(gl_panel_4);
// panel_list.add(panel_4);
//
// JPanel panel_5 = new JPanel();
// panel_5.setBounds(280, 0, -114, 34);
// panel_list.add(panel_5);
//
// JButton btn_find = new JButton("\uAC80 \uC0C9");
// btn_find.setBounds(171, 0, 113, 34);
// panel_list.add(btn_find);
//
// JPanel panel_6 = new JPanel();
// panel_6.setBounds(10, 44, 262, 34);
// panel_list.add(panel_6);
// panel_6.setLayout(new CardLayout(0, 0));
//
// JComboBox cbb_sorts = new JComboBox();
// cbb_sorts.addItemListener(new ItemListener() {
// public void itemStateChanged(ItemEvent e) {
//
// // System.out.println(e.getStateChange());
// //****리스트가 갱신되지 않음
// System.out.println(e.getItem());
// DefaultListModel<String> sortby = new DefaultListModel<>();
//
// ArrayList<PersonVO> sortbyp;
// if (e.getItem() == "이름별 정렬") {
// sortbyp = person.sortName();
//
// for (PersonVO personvo : sortbyp) {
// sortby.addElement(personvo.getName());
// }
//
// list_name = new JList(sortby);
// list_name.updateUI();
//
// }
// if (e.getItem() == "부서별정렬") {
// sortbyp = person.sortDivision();
//
// for (PersonVO personvo : sortbyp) {
// sortby.addElement(personvo.getName());
// }
//
// list_name = new JList(sortby);
// list_name.updateUI();
//
//
// }
// if (e.getItem() == "직책별 정렬") {
// sortbyp = person.sortPosition();
//
// for (PersonVO personvo : sortbyp) {
// sortby.addElement(personvo.getName());
// }
//
// list_name = new JList(sortby);
// list_name.updateUI();
//
// }
// if(e.getItem()=="입사일순 정렬"){
// sortbyp = person.sortJoinDate();
//
// for (PersonVO personvo : sortbyp) {
// sortby.addElement(personvo.getName());
// }
// list_name = new JList(sortby);
// list_name.updateUI();
//
//
// }
//
// }
// });
// cbb_sorts.setModel(new DefaultComboBoxModel(new String[] {"\uC774\uB984\uBCC4
// \uC815\uB82C", "\uBD80\uC11C\uBCC4 \uC815\uB82C", "\uC9C1\uCC45\uC21C
// \uC815\uB82C", "\uC785\uC0AC\uC77C\uC21C \uC815\uB82C"}));
// cbb_sorts.setBounds(10, 44, 262, 34);
// panel_6.add(cbb_sorts);
// panel_6.add(cbb_sorts, "name_10358438746288");
//
// DefaultListModel<String> sortbyname = new DefaultListModel<>();
// PersonDAO person = new PersonDAO();
// ArrayList<PersonVO> sortbynamep = person.sortName();
//
// for (PersonVO personvo : sortbynamep) {
// sortbyname.addElement(personvo.getName());
// }
// list_name = new JList(sortbyname);
// list_name.addListSelectionListener(new ListSelectionListener() {
// public void valueChanged(ListSelectionEvent arg0) {
// selected = list_name.getSelectedValue().toString();
// ArrayList<PersonVO> showperson = person.getpersoninfo(selected);
//
// txt_idnum.setText(showperson.get(0).getIdNum()+"");
//
// }
// });
//
// list_name.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
// list_name.setToolTipText("");
// list_name.setBounds(0, 89, 284, 593);
// panel_list.add(list_name);
//
//
//
// JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
// GroupLayout gl_panel = new GroupLayout(panel);
// gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(tabbedPane,
// Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE));
// gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(tabbedPane,
// GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE));
//
// JPanel panel_1 = new JPanel();
// panel_1.setBackground(Color.WHITE);
// tabbedPane.addTab("\uC0AC\uC6D0 \uC815\uBCF4", null, panel_1, null);
// // panel_1.
// panel_1.setLayout(null);
//
// JPanel pnl_photo = new JPanel();
// pnl_photo.setBorder(new LineBorder(new Color(0, 0, 0)));
// pnl_photo.setBounds(712, 81, 172, 218);
// panel_1.add(pnl_photo);
//
// JLabel txt_photo = new JLabel("\uC0AC\uC9C4");
// GroupLayout gl_pnl_photo = new GroupLayout(pnl_photo);
// gl_pnl_photo.setHorizontalGroup(gl_pnl_photo.createParallelGroup(Alignment.LEADING).addGroup(gl_pnl_photo
// .createSequentialGroup().addGap(69).addComponent(txt_photo).addContainerGap(79,
// Short.MAX_VALUE)));
// gl_pnl_photo.setVerticalGroup(gl_pnl_photo.createParallelGroup(Alignment.LEADING).addGroup(gl_pnl_photo
// .createSequentialGroup().addGap(102).addComponent(txt_photo).addContainerGap(102,
// Short.MAX_VALUE)));
// pnl_photo.setLayout(gl_pnl_photo);
//
// JPanel panel_8 = new JPanel();
// panel_8.setBackground(Color.WHITE);
// panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_8.setBounds(63, 81, 146, 28);
// panel_1.add(panel_8);
//
// JLabel label = new JLabel("\uC774 \uB984");
// panel_8.add(label);
//
// JPanel panel_9 = new JPanel();
// panel_9.setForeground(Color.BLACK);
// panel_9.setBounds(221, 80, 284, 29);
// panel_1.add(panel_9);
//
// panel_9.setLayout(new CardLayout(0, 0));
//
// txt_name = new JTextField();
// panel_9.add(txt_name, "name_9424152680863");
// txt_name.setColumns(10);
//
// JPanel panel_10 = new JPanel();
// panel_10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_10.setBounds(63, 119, 146, 28);
// panel_1.add(panel_10);
//
// JLabel lblNewLabel_1 = new JLabel("\uC0AC \uBC88");
// panel_10.add(lblNewLabel_1);
//
// JPanel panel_11 = new JPanel();
// panel_11.setBounds(221, 119, 284, 28);
// panel_1.add(panel_11);
// panel_11.setLayout(new CardLayout(0, 0));
//
// txt_idnum = new JTextField();
// panel_11.add(txt_idnum, "name_9510506508163");
// txt_idnum.setColumns(10);
//
// JPanel panel_12 = new JPanel();
// panel_12.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_12.setBounds(63, 157, 146, 28);
// panel_1.add(panel_12);
//
// JLabel lblNewLabel_2 = new JLabel("\uC0DD \uB144 \uC6D4 \uC77C");
// panel_12.add(lblNewLabel_2);
//
// JPanel panel_13 = new JPanel();
// panel_13.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
// TitledBorder.TOP, null, null));
// panel_13.setBounds(221, 157, 284, 28);
// panel_1.add(panel_13);
// panel_13.setLayout(null);
//
// txt_birth = new JTextField();
// txt_birth.setBounds(0, 0, 284, 28);
// panel_13.add(txt_birth);
// txt_birth.setColumns(10);
//
// JPanel panel_14 = new JPanel();
// panel_14.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_14.setBounds(63, 195, 146, 28);
// panel_1.add(panel_14);
//
// JLabel label_1 = new JLabel("\uC785 \uC0AC \uC77C");
// panel_14.add(label_1);
//
// JPanel panel_15 = new JPanel();
// panel_15.setBounds(221, 195, 284, 28);
// panel_1.add(panel_15);
// panel_15.setLayout(null);
//
// txt_joindate = new JTextField();
// txt_joindate.setBounds(0, 0, 284, 28);
// panel_15.add(txt_joindate);
// txt_joindate.setColumns(10);
//
// JPanel panel_16 = new JPanel();
// panel_16.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_16.setBounds(63, 233, 146, 28);
// panel_1.add(panel_16);
//
// JLabel label_2 = new JLabel("\uC5F0 \uB77D \uCC98");
// panel_16.add(label_2);
//
// JPanel panel_17 = new JPanel();
// panel_17.setBounds(221, 233, 284, 28);
// panel_1.add(panel_17);
// panel_17.setLayout(null);
//
// txt_phoneNum = new JTextField();
// txt_phoneNum.setBounds(0, 0, 284, 28);
// panel_17.add(txt_phoneNum);
// txt_phoneNum.setColumns(10);
//
// JPanel panel_18 = new JPanel();
// panel_18.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_18.setBounds(63, 271, 146, 28);
// panel_1.add(panel_18);
//
// JLabel label_3 = new JLabel("\uC8FC \uC18C");
// panel_18.add(label_3);
//
// JPanel panel_19 = new JPanel();
// panel_19.setBounds(221, 271, 284, 61);
// panel_1.add(panel_19);
// panel_19.setLayout(null);
//
// txt_address = new JTextField();
// txt_address.setBounds(0, 0, 284, 61);
// panel_19.add(txt_address);
// txt_address.setColumns(10);
//
// JPanel panel_21 = new JPanel();
// panel_21.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_21.setBounds(63, 342, 146, 28);
// panel_1.add(panel_21);
//
// JLabel label_4 = new JLabel("\uBD80 \uC11C");
// panel_21.add(label_4);
//
// JPanel panel_22 = new JPanel();
// panel_22.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_22.setBounds(63, 380, 146, 28);
// panel_1.add(panel_22);
//
// JLabel lblNewLabel_4 = new JLabel("\uC9C1 \uCC45");
// panel_22.add(lblNewLabel_4);
//
// JPanel panel_23 = new JPanel();
// panel_23.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_23.setBounds(63, 414, 146, 28);
// panel_1.add(panel_23);
//
// JLabel label_5 = new JLabel("\uACC4 \uC88C \uBC88 \uD638");
// panel_23.add(label_5);
//
// JPanel panel_24 = new JPanel();
// panel_24.setBounds(437, 414, 439, 28);
// panel_1.add(panel_24);
// panel_24.setLayout(null);
//
// cbb_accounts = new JTextField();
// cbb_accounts.setBounds(0, 0, 439, 28);
// panel_24.add(cbb_accounts);
// cbb_accounts.setColumns(10);
//
// JPanel panel_25 = new JPanel();
// panel_25.setBounds(221, 414, 204, 29);
// panel_1.add(panel_25);
// panel_25.setLayout(null);
//
// JComboBox cbb_banklist = new JComboBox();
// cbb_banklist.setModel(new DefaultComboBoxModel(new String[] {
// "\uC2E0\uD55C\uC740\uD589",
// "\uAD6D\uBBFC\uC740\uD589", "\uAD11\uC8FC\uC740\uD589", "\uC6B0\uCCB4\uAD6D",
// "\uB18D\uD611" }));
// cbb_banklist.setBounds(0, 0, 204, 29);
// panel_25.add(cbb_banklist);
//
// JPanel panel_26 = new JPanel();
// panel_26.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_26.setBounds(63, 452, 146, 29);
// panel_1.add(panel_26);
//
// JLabel label_6 = new JLabel("\uC790 \uACA9 \uC0AC \uD56D");
// panel_26.add(label_6);
//
// JPanel panel_27 = new JPanel();
// panel_27.setBounds(221, 452, 663, 252);
// panel_1.add(panel_27);
// panel_27.setLayout(null);
//
// txt_license = new JTextField();
// txt_license.setBounds(0, 0, 663, 252);
// panel_27.add(txt_license);
// txt_license.setColumns(10);
//
// JPanel panel_29 = new JPanel();
// panel_29.setBounds(221, 342, 284, 28);
// panel_1.add(panel_29);
// panel_29.setLayout(null);
//
// JComboBox cbb_division = new JComboBox();
// cbb_division.setModel(new DefaultComboBoxModel(new String[] {
// "\uD68C\uACC4\uD300",
// "\uC0AC\uC5C5\uC81C\uC548\uD300", "\uC601\uC5C51\uD300",
// "\uBD80\uC124\uC5F0\uAD6C\uC18C" }));
// cbb_division.setBounds(0, 0, 284, 28);
// panel_29.add(cbb_division);
//
// JPanel panel_30 = new JPanel();
// panel_30.setBounds(221, 380, 284, 28);
// panel_1.add(panel_30);
// panel_30.setLayout(null);
//
// JComboBox cbb_position = new JComboBox();
// cbb_position.setModel(new DefaultComboBoxModel(new String[] { "\uC0AC\uC6D0",
// "\uC8FC\uC784", "\uB300\uB9AC",
// "\uACFC\uC7A5", "\uCC28\uC7A5", "\uBD80\uC7A5", "\uC0C1\uBB34\uC774\uC0AC",
// "\uC0AC\uC7A5" }));
// cbb_position.setBounds(0, 0, 284, 28);
// panel_30.add(cbb_position);
//
// JPanel panel_31 = new JPanel();
// panel_31.setBounds(445, 789, 136, 28);
// panel_1.add(panel_31);
//
// JButton btn_newworker = new JButton("\uC2E0 \uADDC \uB4F1 \uB85D");// 신규등록
// btn_newworker.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent arg0) {
// }
// });
// GroupLayout gl_panel_31 = new GroupLayout(panel_31);
// gl_panel_31.setHorizontalGroup(gl_panel_31.createParallelGroup(Alignment.LEADING).addComponent(btn_newworker,
// GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE));
// gl_panel_31.setVerticalGroup(gl_panel_31.createParallelGroup(Alignment.LEADING).addComponent(btn_newworker,
// GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE));
// panel_31.setLayout(gl_panel_31);
//
// JPanel panel_32 = new JPanel();
// panel_32.setBounds(596, 789, 136, 28);
// panel_1.add(panel_32);
//
// JButton btn_update = new JButton("\uC218 \uC815");// 수정
// btn_update.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// }
// });
// GroupLayout gl_panel_32 = new GroupLayout(panel_32);
// gl_panel_32.setHorizontalGroup(gl_panel_32.createParallelGroup(Alignment.LEADING).addComponent(btn_update,
// Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE));
// gl_panel_32.setVerticalGroup(gl_panel_32.createParallelGroup(Alignment.LEADING).addComponent(btn_update,
// Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE));
// panel_32.setLayout(gl_panel_32);
//
// JPanel panel_33 = new JPanel();
// panel_33.setBounds(744, 789, 136, 28);
// panel_1.add(panel_33);
//
// JButton btn_quit = new JButton("\uD1F4 \uC0AC");// 퇴사
// btn_quit.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// }
// });
// GroupLayout gl_panel_33 = new GroupLayout(panel_33);
// gl_panel_33.setHorizontalGroup(gl_panel_33.createParallelGroup(Alignment.LEADING).addComponent(btn_quit,
// Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE));
// gl_panel_33.setVerticalGroup(gl_panel_33.createParallelGroup(Alignment.LEADING).addComponent(btn_quit,
// Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE));
// panel_33.setLayout(gl_panel_33);
//
// JPanel panel_2 = new JPanel();
// tabbedPane.addTab("\uADFC\uBB34 \uAD00\uB9AC", null, panel_2, null);
// panel_2.setLayout(null);
//
// JPanel panel_20 = new JPanel();
// panel_20.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_20.setBounds(63, 81, 146, 28);
// panel_2.add(panel_20);
//
// JLabel label_7 = new JLabel("\uC774 \uB984");
// panel_20.add(label_7);
//
// JPanel panel_7 = new JPanel();
// panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_7.setBounds(221, 81, 255, 28);
// panel_2.add(panel_7);
//
// JLabel lblNewLabel_3 = new JLabel("name");
// panel_7.add(lblNewLabel_3);
//
// JPanel panel_28 = new JPanel();
// panel_28.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_28.setBounds(63, 119, 146, 28);
// panel_2.add(panel_28);
//
// JLabel label_8 = new JLabel("\uC0AC \uC6D0 \uBC88 \uD638");
// panel_28.add(label_8);
//
// JPanel panel_34 = new JPanel();
// panel_34.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_34.setBounds(221, 119, 255, 28);
// panel_2.add(panel_34);
//
// JLabel lblNum = new JLabel("num");
// panel_34.add(lblNum);
//
// JPanel panel_35 = new JPanel();
// panel_35.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_35.setBounds(488, 81, 146, 28);
// panel_2.add(panel_35);
//
// JLabel label_9 = new JLabel("\uBD80 \uC11C");
// panel_35.add(label_9);
//
// JPanel panel_36 = new JPanel();
// panel_36.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_36.setBounds(646, 81, 255, 28);
// panel_2.add(panel_36);
//
// JLabel lblDiv = new JLabel("div");
// panel_36.add(lblDiv);
//
// panel_37 = new JPanel();
// panel_37.setBounds(63, 157, 838, 358);
//
// panel_2.add(panel_37);
// panel_37.setLayout(null);
//
// // JCalendar calendar = new JCalendar();
// // calendar.setBounds(0, 0, 838, 358);
// // panel_37.add(calendar);
//
// /*
// * table = new JTable(); table.setBounds(0, 0, 838, 358);
// */
//
// /*
// * SwingCalendar calendar = new SwingCalendar(); panel_37.add(calendar);
// */
//
// JPanel panel_38 = new JPanel();
// panel_38.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_38.setBounds(488, 119, 146, 28);
// panel_2.add(panel_38);
//
// JLabel label_10 = new JLabel("\uC9C1 \uCC45");
// panel_38.add(label_10);
//
// JPanel panel_39 = new JPanel();
// panel_39.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_39.setBounds(646, 119, 255, 28);
// panel_2.add(panel_39);
//
// JLabel lblPosition = new JLabel("position");
// panel_39.add(lblPosition);
//
// JPanel panel_40 = new JPanel();
// panel_40.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_40.setBounds(108, 542, 126, 28);
// panel_2.add(panel_40);
//
// JLabel label_11 = new JLabel("\uCD08\uACFC\uADFC\uBB34\uC2DC\uAC04");
// panel_40.add(label_11);
//
// JPanel panel_41 = new JPanel();
// panel_41.setBounds(252, 542, 146, 28);
// panel_2.add(panel_41);
// panel_41.setLayout(null);
//
// txt_OverWorkTime = new JTextField();
// txt_OverWorkTime.setBounds(0, 0, 146, 28);
// panel_41.add(txt_OverWorkTime);
// txt_OverWorkTime.setColumns(10);
//
// JPanel panel_42 = new JPanel();
// panel_42.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_42.setBounds(495, 542, 126, 28);
// panel_2.add(panel_42);
//
// JLabel label_12 = new
// JLabel("\uB204\uC801\uCD08\uACFC\uADFC\uBB34\uC2DC\uAC04");
// panel_42.add(label_12);
//
// JPanel panel_43 = new JPanel();
// panel_43.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_43.setBounds(638, 542, 146, 28);
// panel_2.add(panel_43);
//
// JLabel lbl_sumoverworktime = new JLabel("n");
// panel_43.add(lbl_sumoverworktime);
//
// JButton btnNewButton = new JButton("\uB4F1\uB85D");
// btnNewButton.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent arg0) {
// int overworktime = Integer.parseInt(txt_OverWorkTime.getText().toString());
// PaymentDAO dao = new PaymentDAO();
// dao.manageoverworktime(selected, overworktime);
//
// }
// });
//
// btnNewButton.setBounds(409, 542, 67, 28);
//
// panel_2.add(btnNewButton);
//
// JPanel panel_44 = new JPanel();
// panel_44.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_44.setBounds(108, 580, 126, 28);
// panel_2.add(panel_44);
//
// JLabel label_13 = new JLabel("\uCD08\uACFC\uADFC\uBB34\uC218\uB2F9");
// panel_44.add(label_13);
//
// JPanel panel_45 = new JPanel();
// panel_45.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_45.setBounds(495, 580, 126, 28);
// panel_2.add(panel_45);
//
// JLabel label_14 = new
// JLabel("\uB204\uC801\uCD08\uACFC\uADFC\uBB34\uC218\uB2F9");
// panel_45.add(label_14);
//
// JPanel panel_46 = new JPanel();
// panel_46.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_46.setBounds(252, 580, 146, 28);
// panel_2.add(panel_46);
//
// JLabel lbl_overworkpay = new JLabel("n");
// panel_46.add(lbl_overworkpay);
//
// JPanel panel_47 = new JPanel();
// panel_47.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
// panel_47.setBounds(638, 580, 146, 28);
// panel_2.add(panel_47);
//
// JLabel lbl_sumoverworkpay = new JLabel("n");
// panel_47.add(lbl_sumoverworkpay);
//
// JPanel panel_3 = new JPanel();
// tabbedPane.addTab("\uC5F0\uBD09 \uAD00\uB9AC", null, panel_3, null);
// GroupLayout gl_panel_3 = new GroupLayout(panel_3);
// gl_panel_3
// .setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGap(0,
// 944, Short.MAX_VALUE));
// gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGap(0,
// 921, Short.MAX_VALUE));
// panel_3.setLayout(gl_panel_3);
//
// JPanel panel_48 = new JPanel();
// tabbedPane.addTab("New tab", null, panel_48, null);
// panel.setLayout(gl_panel);
//
// JLabel lblNewLabel = new JLabel("Fantastic4 \uC778\uC0AC\uAD00\uB9AC
// \uD504\uB85C\uADF8\uB7A8");
// lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
// panel_search.add(lblNewLabel);
// frame.getContentPane().setLayout(groupLayout);
// }
//
// public void SwingCalendar() {
//
// JLabel label = new JLabel();
// label.setHorizontalAlignment(SwingConstants.CENTER);
//
// JButton b1 = new JButton("<-");
// b1.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent ae) {
// cal.add(Calendar.MONTH, -1);
// updateMonth();
// }
// });
//
// JButton b2 = new JButton("->");
// b2.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent ae) {
// cal.add(Calendar.MONTH, +1);
// updateMonth();
// }
// });
//
// JPanel panel = new JPanel();
// panel.setLayout(new BorderLayout());
// panel.add(b1, BorderLayout.WEST);
// panel.add(label, BorderLayout.CENTER);
// panel.add(b2, BorderLayout.EAST);
//
// String[] columns = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
// model = new DefaultTableModel(null, columns);
// JTable table = new JTable(model);
// JScrollPane pane = new JScrollPane(table);
//
// panel_37.add(panel, BorderLayout.NORTH);
// panel_37.add(pane, BorderLayout.CENTER);
//
// this.updateMonth();
//
// }
//
// void updateMonth() {
//
// label = new JLabel();
// cal.set(Calendar.DAY_OF_MONTH, 1);
//
// String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
// int year = cal.get(Calendar.YEAR);
// label.setText(month + " " + year);
//
// int startDay = cal.get(Calendar.DAY_OF_WEEK);
// int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
// int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
//
// model.setRowCount(0);
// model.setRowCount(weeks);
//
// int i = startDay - 1;
// for (int day = 1; day <= numberOfDays; day++) {
// model.setValueAt(day, i / 7, i % 7);
// i = i + 1;
// }
//
// }
//
// private void setTxt() {
//
// }
// }
