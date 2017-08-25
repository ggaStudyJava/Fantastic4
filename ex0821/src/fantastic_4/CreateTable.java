package fantastic_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateTable {
	private PreparedStatement psmt;
	private Connection conn;
	private ResultSet rs;

	String url = "jdbc:oracle:thin:@127.0.01:1521:XE";
	String id = "fantastic4";
	String pw = "123456";

	public CreateTable() throws SQLException {
		
		//db파일 추가해야 실행 될 것 같음.

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw); 	
			String createPInfo = "create table pinfo " + "(idnum number not null primary key,"
					+ "name varchar(20) not null," + "division varchar(20) not null," + "postion varchar(20) not null,"
					+ "joinDate date not null," + "birthDate date not null," + "address varchar(50),"
					+ "phoneNum varchar(20)," + "license varchar(20)," + "accountNum varchar(20));";
		
			String createpayment = "create table payment " + "(idnum number not null primary key,"
					+ "basepay number not null," + "overworktime number," + "serverancepay number);";
			psmt.execute(createPInfo);
			psmt.execute(createpayment);
			System.out.println("1");

		}catch(SQLException e){
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			try {
				if (psmt!=null) psmt.close();
				if (conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}

	}

}




//package fantastic_4;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class Createtable {
//	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
//	private String pinfosql = "create table Pinfo (id number not null PRIMARY KEY, "
//			+ "name varchar(20) not null, birthdate varchar(20) not null,"
//			+ "address varchar(50),phonenum varchar(20),division varchar(20) not null,"
//			+ "position varchar(20)not null,license varchar(20),accountnum varchar(20),"
//			+ "joindate date not null,quitdate date);";
//	private String paysql = "create table paymentable (id number not null PRIMARY KEY, "
//			+ "basepay int not null,overworktime int servarancepay int);";
//	private Connection conn;
//	private Statement stmt;
//
//	public void createtable() {
//		try {
//			conn = DriverManager.getConnection(url);
//			stmt = conn.createStatement();
//			stmt.execute(pinfosql);
//			stmt.execute(paysql);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.getMessage();
//		} finally {
//			try {
//				if (stmt != null)
//					stmt.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//}
