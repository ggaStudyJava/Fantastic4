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
	public final int OvWorkPay = 30000; //초과근무수당
<<<<<<< HEAD


	// 누적초과시간 만든이: 국경아
	//초과근무시간 메소드는 굳이 안만들어도 누적초과시간 메소드에서 해결됨
	public int accumulateOverWorkTime(int IdNum, int OverWorkTime) {
		int num = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 해당 드라이버를 메모리에 업로드
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
	// 누적초과시간 만든이: 국경아
	//초과근무시간 메소드는 굳이 안만들어도 누적초과시간 메소드에서 해결됨
	public int accumulateOverWorkTime(int IdNum, int OverWorkTime) {
		int num = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 해당 드라이버를 메모리에 업로드
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
			if(rs.next()){ //dayOverWorkTime을 DB에 있던 OverWorkTime와 합산
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
	//초과근무수당 메소드 만든이: 국경아
=======
	//초과근무수당 메소드
>>>>>>> branch 'master' of https://github.com/jangchankyung/Team4.git
	public int overWorkPay(int OverWorkTime){ 		
		return OvWorkPay*OverWorkTime;
		
	}
}
