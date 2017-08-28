package fantastic4_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MonthRecordDAO {
	// ��,���� üũ�ؼ� ������ �ʰ��ٹ��ð��� ����ϴ� ���̺� �÷��� ����� Ŭ����

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
			// �ش� ����̹��� �޸𸮿� ���ε�
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "select "+yy+"y"+mm+"m"+" from monthrecord where IdNum = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, IdNum);

			num = pstmt.executeUpdate();
			rs = pstmt.executeQuery();//�ش� ��,���� ������ �÷��� �ִ��� Ȯ�� ������ ������ ��µ�
										//������ ����� ����üũ=true, �÷��� ���ٴ¶��̹Ƿ� �÷��� �����ϵ��� �� 			
			}
		catch (Exception e) {
			exist = true;				
			}
		if(exist){		
		try{//������ ����� �÷� ����.
			String sql2 = "ALTER TABLE monthrecord ADD("+yy+"y"+mm+"m"+" VARCAHR2(13))" ; 
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, IdNum);
			} catch (SQLException e) {
			}
		//�÷��� ���� ��� �÷� ���� �Ϸ�, ������(Ȥ�� �����ϴ�) �ʵ忡 ���� ������
		try {
			if(rs.next()){ //dayOverWorkTime�� DB�� �ִ� OverWorkTime�� �ջ�
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
