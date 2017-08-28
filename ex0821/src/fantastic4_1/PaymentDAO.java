package fantastic4_1;

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
	private int sumOverWorkTime = 0;

	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";// DB URL
	private String user = "fantastic4";
	private String password = "123456";

	public final int OvWorkPay = 30000; // 초과근무수당

	final int sawonpay = 30000000;
	final int juimpay = 50000000;
	final int daeripay = 70000000;
	final int gwajangpay = 90000000;
	final int chajangpay = 110000000;
	final int bujangpay = 130000000;
	final int esapay = 150000000;
	final int sajangpay = 250000000;

	public int accumulateOverWorkTime(int IdNum, int OverWorkTime) {
		int num = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 해당 드라이버를 메모리에 업로드
			conn = DriverManager.getConnection(url, user, password);
			String sql1 = "select overworktime from payment where IdNum = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, IdNum);

			num = pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			if (rs.next()) { // dayOverWorkTime을 DB에 있던 OverWorkTime와 합산
				OverWorkTime += rs.getInt(1);
			}

			String sql2 = "update payment set OverWorkTime = ? where IdNum = ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, OverWorkTime);
			pstmt2.setInt(2, IdNum);

			num = pstmt2.executeUpdate();
			rs = pstmt2.executeQuery();

		} catch (ClassNotFoundException e) {// try에서 에러가 발생할 경우 catch실행
			e.printStackTrace(); // 오류를 콘솔창에 자세히 출력해줌
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		sumOverWorkTime = OverWorkTime;
		return OverWorkTime;
	}

	public int overWorkPay(int OverWorkTime) {
		return OvWorkPay * OverWorkTime;
	}

	public int sumoverWorkPay() {
		return OvWorkPay * sumOverWorkTime;
	}

}
