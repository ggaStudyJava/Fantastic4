package fantastic4_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MonthRecordDAO {
	// 년,월을 체크해서 월별로 초과근무시간을 기록하는 테이블에 컬럼을 만드는 클래스

	private int sumOverWorkTime = 0;

	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";// DB URL
	private String id = "fantastic4";
	private String pw = "123456";

	public final int OvWorkPay = 30000;

	private int yy;
	private int mm;
	// private int dd;
	private PreparedStatement psmt;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private Statement stmt;
	private PreparedStatement pstmt2;

	public MonthRecordDAO(int yy, int mm) {
		this.yy = yy;
		this.mm = mm;
		createTable();
		// this.dd = dd;
	}

	private void createTable() {
		int num = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			// TODO: handle exception
		}
		boolean exist = false;
		String chktbl = "select " + yy + "y" + mm + "m" + " from monthrecord";
		try {
			pstmt = conn.prepareStatement(chktbl);
			num = pstmt.executeUpdate();
		} catch (Exception e) {
			exist = true;
		}
		if (!exist)
			try {
				String createmonthcolumm = "alter table monthrecord add(" + yy + "y" + mm + "m" + " number(10))";

				pstmt = conn.prepareStatement(createmonthcolumm);
				num = pstmt.executeUpdate();
				rs = pstmt.executeQuery();
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
		else {
		}
	}

	public int accumulateOverWorkTime(int IdNum, int OverWorkTime) {
		int num = -1;
		boolean exist = false;
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 해당 드라이버를 메모리에 업로드
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "select "+yy+"y"+mm+"m"+" from monthrecord where IdNum = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, IdNum);

			num = pstmt.executeUpdate();
			rs = pstmt.executeQuery();//해당 년,월로 생성된 컬럼이 있는지 확인 없으면 오류가 출력됨
										//오류가 생기면 오류체크=true, 컬럼이 없다는뜻이므로 컬럼을 생성하도록 함 			
			}
		catch (Exception e) {
			exist = true;				
			}
		if(exist){		
		try{//오류가 생기면 컬럼 생성.
			String sql2 = "ALTER TABLE monthrecord ADD("+yy+"y"+mm+"m"+" VARCAHR2(13))" ; 
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, IdNum);
			} catch (SQLException e) {
			}
		//컬럼이 없는 경우 컬럼 생성 완료, 생성된(혹은 존재하는) 필드에 값을 더해줌
		try {
			if(rs.next()){ //dayOverWorkTime을 DB에 있던 OverWorkTime와 합산
				OverWorkTime += rs.getInt(1); 				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
		try{
		String sql2 = "update payment set OverWorkTime = ? where IdNum = ?";
		pstmt2 = conn.prepareStatement(sql2);
		pstmt2.setInt(1, OverWorkTime);
		pstmt2.setInt(2, IdNum);
		
		num = pstmt2.executeUpdate();
		rs = pstmt2.executeQuery();		
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		finally {
			try {
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				
			}
			
		}
		
		

	}
		sumOverWorkTime=OverWorkTime;
		return OverWorkTime;
	}

	public int overWorkPay(int OverWorkTime){ 		
		return OvWorkPay*OverWorkTime;
	}

	public int sumoverWorkPay(){ 		
		return OvWorkPay*sumOverWorkTime;
	}
}
