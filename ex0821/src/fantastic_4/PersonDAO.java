package fantastic_4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//국경아 DB DAO 소스코드
public class PersonDAO { 
	private  Connection conn; 
	private  Statement stmt;
	private  PreparedStatement pstmt;
	private ResultSet rs;
	
	String url = "jdbc:oracle:thin:@127.0.01:1521:XE";
	String id = "ga";
	String pw = "1234";
	
	
	public int insertPerson(int IdNum,String name,String birthDate,String address,
			String phoneNum,String division,String postion,String license,String accountNum,
			Date joinDate){
		
		int num = -1;
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//해당 드라이버를 메모리에 업로드			
			conn = DriverManager.getConnection(url,id,pw); 	
			String sql1 = "insert into PInfo(IdNum,name,division,postion,joinDate,birthDate,address,"
					+ "phoneNum,license,accountNum) values(?,?,?,?,?,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, IdNum); //물음표 위치 번호, 1번부터 시작
			pstmt.setString(2, name);
			pstmt.setString(3, division);
			pstmt.setString(4, postion);
			pstmt.setDate(5, joinDate);
			pstmt.setString(6, birthDate);
			pstmt.setString(7, address);
			pstmt.setString(8, phoneNum);
			pstmt.setString(9, license);
			pstmt.setString(10, accountNum);			
			
			num = pstmt.executeUpdate();	
			
		} catch (ClassNotFoundException e) {// try에서 에러가 발생할 경우 catch실행
			e.printStackTrace(); //오류를 콘솔창에 자세히 출력해줌
		} catch (SQLException e) { 
			e.printStackTrace();
		}		
		//finally는 try 에러와 상관없이 실행
		finally{ //반드시 close 해야함, 닫을때는 나중에 열었던것부터 닫기
				try {
					if(pstmt !=null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		}
		return num;
	}

}
