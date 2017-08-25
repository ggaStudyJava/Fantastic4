package fantastic_4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//국경아 DB DAO 소스코드
public class PersonDAO { 
	private  Connection conn; 
	private  Statement stmt;
	private  PreparedStatement pstmt;
	private ResultSet rs;
	
	String url = "jdbc:oracle:thin:@127.0.01:1521:XE";
//	String id = "ga";
//	String pw = "1234";
	String id = "ck";
	String pw = "123456";
	
	// 사원 추가
	public int insertPerson(int IdNum,String name,String birthDate,String address,
			String phoneNum,String division,String position,String license,String accountNum,
			String joinDate){
		
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
			pstmt.setString(4, position);
			pstmt.setString(5, joinDate);
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

	//사원 삭제(바로 삭제 되게 설정함)
	public int deletePerson(int IdNum){
		int num = -1;
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//해당 드라이버를 메모리에 업로드			
			conn = DriverManager.getConnection(url,id,pw); 	
			String sql1 = "delete PInfo where IdNum = ?"; 
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, IdNum); //해당 IdNum을 가진 사람 삭제		
			
			num = pstmt.executeUpdate();	
			
		} catch (ClassNotFoundException e) {// try에서 에러가 발생할 경우 catch실행
			e.printStackTrace(); //오류를 콘솔창에 자세히 출력해줌
		} catch (SQLException e) { 
			e.printStackTrace();
		}finally{ 
				try {
					if(pstmt !=null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		}
		return num;
	}
	
	//사원 수정
	public int updatePerson(int IdNum,String address,String division,String position,
			String license,String accountNum,String phoneNum){
		int num = -1;
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//해당 드라이버를 메모리에 업로드			
			conn = DriverManager.getConnection(url,id,pw); 	
			String sql1 = "update PInfo set address = ?, phoneNum = ?, "
					+ "division = ?, postion = ?, license = ?, accountNum = ?"
					+ "where IdNum = ?"; 
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, address);
			pstmt.setString(2, phoneNum);
			pstmt.setString(3, division);
			pstmt.setString(4, position);
			pstmt.setString(5, license);
			pstmt.setString(6, accountNum);
			pstmt.setInt(7, IdNum); //해당 IdNum을 가진 사람 수정			
			
			num = pstmt.executeUpdate();	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		} catch (SQLException e) { 
			e.printStackTrace();
		}finally{ 
				try {
					if(pstmt !=null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		}
		return num;
		
	}

	//검색기능
	public PersonVO searchPerson(int IdNum,String birthDate,String name){
		PersonVO vo = null;
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw); 
			String sql2 = "select * from PInfo where IdNum = ? OR birthDate = ?"
					+ "OR name = ?";
			pstmt = conn.prepareStatement(sql2);
			
			pstmt.setInt(1, IdNum);
			pstmt.setString(2, birthDate); 
			pstmt.setString(3, name); 
		
			rs = pstmt.executeQuery(); 
			
			while(rs.next()){
				String tempName = rs.getString(2); //2번째 있는 column을 꺼냄
				//select * 일 경우 getString(N) N번째 column을 꺼냄 
				//select name, ppp 일경우 getString(1번은 name, 2번은 ppp) 
				//select name// column이 하나일 경우는 getString(1)이면 됨
				vo = new PersonVO(tempName);
			}	
			
		} catch (ClassNotFoundException e) {
			// try에서 에러가 발생할 경우 catch실행
			e.printStackTrace(); //오류를 콘솔창에 자세히 출력해줌
		} catch (SQLException e) { //DriverManager.getConnection(url,id,pw)에서 add.catch 
			e.printStackTrace();
		} 		//finally는 try 에러와 상관없이 실행
		finally{ //반드시 close 해야함, 닫을때는 나중에 열었던것부터 닫기
				try {
					if(pstmt !=null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		}		
		return vo;
	}
	
	//정렬 메소드
	public ArrayList<PersonVO> sortName(){//이름 정렬
		PersonVO vo = null;
		ArrayList<PersonVO> aLName = new ArrayList<>();
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw); 
			String sql1 = "select name from PInfo order by name";
			pstmt = conn.prepareStatement(sql1);
		
			rs = pstmt.executeQuery(); 
			
			while(rs.next()){
				String name = rs.getString(1); //
				vo = new PersonVO(name);
				aLName.add(vo);
			}	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //오류를 콘솔창에 자세히 출력해줌
		} catch (SQLException e) {  
			e.printStackTrace();
		} 		//finally는 try 에러와 상관없이 실행
		finally{ //반드시 close 해야함, 닫을때는 나중에 열었던것부터 닫기
				try {
					if(pstmt !=null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		}		
		return aLName;
		
	}
	public ArrayList<PersonVO> sortDivision(){//부서정렬
		PersonVO vo = null;
		ArrayList<PersonVO> aLDivision = new ArrayList<>();
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw); 
			String sql1 = "select name from PInfo order by division";
			pstmt = conn.prepareStatement(sql1);
		
			rs = pstmt.executeQuery(); 
			
			while(rs.next()){
				String name = rs.getString(1); //
				vo = new PersonVO(name);
				aLDivision.add(vo);
			}	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //오류를 콘솔창에 자세히 출력해줌
		} catch (SQLException e) {  
			e.printStackTrace();
		} 		//finally는 try 에러와 상관없이 실행
		finally{ //반드시 close 해야함, 닫을때는 나중에 열었던것부터 닫기
				try {
					if(pstmt !=null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		}		
		return aLDivision;
		
	}
	public ArrayList<PersonVO> sortPosition(){//직책정렬
		PersonVO vo = null;
		ArrayList<PersonVO> aLPosition = new ArrayList<>();
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw); 
			String sql1 = "select name from PInfo order by position";
			pstmt = conn.prepareStatement(sql1);
		
			rs = pstmt.executeQuery(); 
			
			while(rs.next()){
				String name = rs.getString(1); //
				vo = new PersonVO(name);
				aLPosition.add(vo);
			}	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //오류를 콘솔창에 자세히 출력해줌
		} catch (SQLException e) {  
			e.printStackTrace();
		} 		//finally는 try 에러와 상관없이 실행
		finally{ //반드시 close 해야함, 닫을때는 나중에 열었던것부터 닫기
				try {
					if(pstmt !=null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		}		
		return aLPosition;
		
	}
	public ArrayList<PersonVO> sortJoinDate(){//입사일순 정렬
		PersonVO vo = null;
		ArrayList<PersonVO> aLJoinDate = new ArrayList<>();
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw); 
			String sql1 = "select name from PInfo order by joinDate";
			pstmt = conn.prepareStatement(sql1);
		
			rs = pstmt.executeQuery(); 
			
			while(rs.next()){
				String name = rs.getString(1); //
				vo = new PersonVO(name);
				aLJoinDate.add(vo);
			}	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //오류를 콘솔창에 자세히 출력해줌
		} catch (SQLException e) {  
			e.printStackTrace();
		} 		//finally는 try 에러와 상관없이 실행
		finally{ //반드시 close 해야함, 닫을때는 나중에 열었던것부터 닫기
				try {
					if(pstmt !=null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		}		
		return aLJoinDate;
		
	}
	
	// 이름 입력시 해당 IdNum가져오는 메소드
		public int getPersonIdNum(String name) {
			int num = -1;
			int IdNum=0;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// 해당 드라이버를 메모리에 업로드
				conn = DriverManager.getConnection(url, id, pw);
				String sql1 = "select IdNum from PInfo where name = ?";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, name);

				num = pstmt.executeUpdate();
				rs = pstmt.executeQuery();

				if (rs.next()) { 
					IdNum = rs.getInt(1);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return IdNum;
		}

	public ArrayList<PersonVO> getpersoninfo(String selected) {
		PersonVO vo = null;
		ArrayList<PersonVO> getpersoninfo = new ArrayList<>();
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw); 
			String sql1 = "select * from PINFO where NAME = '"+selected+"'";
			System.out.println(sql1);
			
			pstmt = conn.prepareStatement(sql1);//****이거 오류생김
		
			rs = pstmt.executeQuery(); 
			
			while(rs.next()){
				int idnum = rs.getInt(1);				
				String name = rs.getString(2);				
				String div = rs.getString(3);
				String pos = rs.getString(4);
				Date join = rs.getDate(5);				
				Date birth = rs.getDate(6);
				String add = rs.getString(7);
				String phone = rs.getString(8);
				String lic = rs.getString(9);
				String acc = rs.getString(10);
						
				vo = new PersonVO(idnum,name,div,pos,join,birth,add,phone,lic,acc);
				getpersoninfo.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //오류를 콘솔창에 자세히 출력해줌
		} catch (SQLException e) {  
			e.printStackTrace();
		} 		//finally는 try 에러와 상관없이 실행
		finally{ //반드시 close 해야함, 닫을때는 나중에 열었던것부터 닫기
				try {
					if(pstmt !=null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}		
		}		
		return getpersoninfo;
		// TODO Auto-generated method stub
		
	}
	
	

}
