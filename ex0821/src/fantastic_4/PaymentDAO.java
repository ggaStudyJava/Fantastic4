package fantastic_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAO {
	PreparedStatement psmt;
	Connection conn;
	ResultSet rs;
	
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";// DB URL
	private String user = "ck";
	private String password = "123456";
	
	ArrayList<PaymentVO> volist = new ArrayList<>();
	
	public ArrayList<PaymentVO> manageoverworktime(String selected, int hour) {
		int tmpoverworkhour;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,user,password);
			String sql = "select OVERWORKTIME from PAYMENT where NAME = '"+selected+"'";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			
			while(rs.next()){
				tmpoverworkhour = rs.getInt(3);
				PaymentVO vo = new PaymentVO();
				volist.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

}
