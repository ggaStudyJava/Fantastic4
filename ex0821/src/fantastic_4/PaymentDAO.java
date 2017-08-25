package fantastic_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaymentDAO {
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private Connection conn;
	private ResultSet rs;

	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";// DB URL
	private String user = "ck";
	private String password = "123456";
	public final int OvWorkPay = 30000; //�ʰ��ٹ�����


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

			num = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			
			
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

	//�ʰ��ٹ����� �޼ҵ�
	public int overWorkPay(int OverWorkTime){ 		
		return OvWorkPay*OverWorkTime;
		
	}
}
