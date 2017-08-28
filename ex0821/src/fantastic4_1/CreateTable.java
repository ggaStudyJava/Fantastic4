package fantastic4_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	private PreparedStatement psmt;
	private Connection conn;
	private ResultSet rs;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	String id = "fantastic4";
	String pw = "123456";
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private PreparedStatement pstmt3;
	private PreparedStatement pstmt4;
	private PreparedStatement pstmt5;
	private PreparedStatement pstmt6;
	private PreparedStatement pstmt7;

	public CreateTable() {
		int num=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 테이블이 존재할 경우 생성하지 않도록 함
		boolean exist = false;
		String chktbl = "select idnum from PINFO";
		try {
			pstmt = conn.prepareStatement(chktbl);
			num = pstmt.executeUpdate();
		} catch (Exception e) {
			exist = true;
		}
		if (exist) {
			try {
				String createPInfo = "create table pinfo " + "(idnum number not null primary key,"
						+ "name varchar(20) not null," + "division varchar(20) not null,"
						+ "postion varchar(20) not null," + "joinDate date not null," + "birthDate date not null,"
						+ "address varchar(50)," + "phoneNum varchar(20)," + "license varchar(20),"
						+ "accountNum varchar(20))";

				String createpayment = "create table payment " + "(idnum number not null primary key,"
						+ "basepay number not null," + "overworktime number," + "serverancepay number)";
				
				String createmonth = "create table monthrecord (idnum number not null primary key)";
				
				//회계팀
				String createSeq_Account = "create sequence seq_Account start with 1001, default increment by 1,"
						+ "maxvalue 1999";
				//사업제안팀
				String createSeq_Proposal = "create sequence seq_Proposal start with 2001, default increment by 1,"
						+ "maxvalue 2999";
				
				//영업1팀
				String createSeq_Sales = "create sequence seq_Sales start with 3001, default increment by 1,"
						+ "maxvalue 3999";
				
				//연구소 
				String createSeq_Lap = "create sequence seq_Lap start with 4001, default increment by 1,"
						+ "maxvalue 4999";
				
				pstmt = conn.prepareStatement(createPInfo);
				num = pstmt.executeUpdate();
				
				pstmt4 = conn.prepareStatement(createSeq_Account);
				num = pstmt4.executeUpdate();
				
				pstmt5 = conn.prepareStatement(createSeq_Proposal);
				num = pstmt5.executeUpdate();
				
				pstmt6 = conn.prepareStatement(createSeq_Sales);
				num = pstmt6.executeUpdate();
				
				pstmt7 = conn.prepareStatement(createSeq_Lap);
				num = pstmt7.executeUpdate();
				
				pstmt2 = conn.prepareStatement(createpayment);
				num = pstmt2.executeUpdate();
//				System.out.println("1");
				pstmt3 = conn.prepareStatement(createmonth);
				num = pstmt3.executeUpdate();	
				
				
				
			} catch (SQLException e) {

			} finally {
				try {
					if (pstmt3 != null)
						pstmt3.close();
					if (pstmt2 != null)
						pstmt2.close();
					if (pstmt7 != null)
						pstmt7.close();
					if (pstmt6 != null)
						pstmt6.close();
					if (pstmt5 != null)
						pstmt5.close();
					if (pstmt4 != null)
						pstmt4.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // if문 닫음
		else {
		}
	}

}

// package fantastic_4;
//
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.sql.Statement;
//
// public class Createtable {
// private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
// private String pinfosql = "create table Pinfo (id number not null PRIMARY
// KEY, "
// + "name varchar(20) not null, birthdate varchar(20) not null,"
// + "address varchar(50),phonenum varchar(20),division varchar(20) not null,"
// + "position varchar(20)not null,license varchar(20),accountnum varchar(20),"
// + "joindate date not null,quitdate date);";
// private String paysql = "create table paymentable (id number not null PRIMARY
// KEY, "
// + "basepay int not null,overworktime int servarancepay int);";
// private Connection conn;
// private Statement stmt;
//
// public void createtable() {
// try {
// conn = DriverManager.getConnection(url);
// stmt = conn.createStatement();
// stmt.execute(pinfosql);
// stmt.execute(paysql);
//
// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.getMessage();
// } finally {
// try {
// if (stmt != null)
// stmt.close();
// if (conn != null)
// conn.close();
// } catch (SQLException e) {
// e.printStackTrace();
// }
// }
//
// }
//
// }
