package fantastic_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
=======
import java.sql.Statement;
import java.util.ArrayList;
>>>>>>> branch 'master' of https://github.com/jangchankyung/Team4.git

public class PaymentDAO {
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private Connection conn;
	private ResultSet rs;

	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";// DB URL
	private String user = "ck";
	private String password = "123456";
	public final int OvWorkPay = 30000; //�ʰ��ٹ�����
<<<<<<< HEAD


	// �����ʰ��ð� ������: �����
	//�ʰ��ٹ��ð� �޼ҵ�� ���� �ȸ��� �����ʰ��ð� �޼ҵ忡�� �ذ��
	public int accumulateOverWorkTime(int IdNum, int OverWorkTime) {
		int num = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// �ش� ����̹��� �޸𸮿� ���ε�
			conn = DriverManager.getConnection(url, user, password);
			
			String sql1 = "select overworktime from payment where IdNum = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, IdNum);
=======
>>>>>>> branch 'master' of https://github.com/jangchankyung/Team4.git


<<<<<<< HEAD
			num = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
=======
	// �����ʰ��ð� ������: �����
	//�ʰ��ٹ��ð� �޼ҵ�� ���� �ȸ��� �����ʰ��ð� �޼ҵ忡�� �ذ��
	public int accumulateOverWorkTime(int IdNum, int OverWorkTime) {
		int num = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// �ش� ����̹��� �޸𸮿� ���ε�
			conn = DriverManager.getConnection(url, user, password);
>>>>>>> branch 'master' of https://github.com/jangchankyung/Team4.git
			
<<<<<<< HEAD
=======
			String sql1 = "select overworktime from payment where IdNum = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, IdNum);


			num = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
>>>>>>> branch 'master' of https://github.com/jangchankyung/Team4.git
			
<<<<<<< HEAD
=======
			
>>>>>>> branch 'master' of https://github.com/jangchankyung/Team4.git
			if(rs.next()){ //dayOverWorkTime�� DB�� �ִ� OverWorkTime�� �ջ�
				OverWorkTime += rs.getInt(1); 				
			}			
			
			String sql2 = "update payment set OverWorkTime = ? where IdNum = ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, OverWorkTime);
			pstmt2.setInt(2, IdNum);
			
			num = pstmt2.executeUpdate();
			rs = pstmt2.executeQuery();			
			

		} catch (ClassNotFoundException e) {// try���� ������ �߻��� ��� catch����
			e.printStackTrace(); // ������ �ܼ�â�� �ڼ��� �������
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {if (pstmt2 != null)
				pstmt2.close();				
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return OverWorkTime;
	}

<<<<<<< HEAD
	//�ʰ��ٹ����� �޼ҵ� ������: �����
=======
	//�ʰ��ٹ����� �޼ҵ�
>>>>>>> branch 'master' of https://github.com/jangchankyung/Team4.git
	public int overWorkPay(int OverWorkTime){ 		
		return OvWorkPay*OverWorkTime;
		
	}
}
