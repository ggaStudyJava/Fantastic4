package fantastic_4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//����� DB DAO �ҽ��ڵ�
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
			//�ش� ����̹��� �޸𸮿� ���ε�			
			conn = DriverManager.getConnection(url,id,pw); 	
			String sql1 = "insert into PInfo(IdNum,name,division,postion,joinDate,birthDate,address,"
					+ "phoneNum,license,accountNum) values(?,?,?,?,?,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, IdNum); //����ǥ ��ġ ��ȣ, 1������ ����
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
			
		} catch (ClassNotFoundException e) {// try���� ������ �߻��� ��� catch����
			e.printStackTrace(); //������ �ܼ�â�� �ڼ��� �������
		} catch (SQLException e) { 
			e.printStackTrace();
		}		
		//finally�� try ������ ������� ����
		finally{ //�ݵ�� close �ؾ���, �������� ���߿� �������ͺ��� �ݱ�
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
