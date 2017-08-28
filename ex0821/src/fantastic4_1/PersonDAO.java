package fantastic4_1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//����� DB DAO �ҽ��ڵ�
public class PersonDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private PreparedStatement pstmt3;
	private ResultSet rs;

	String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	// String id = "ga";
	// String pw = "1234";
	String id = "fantastic4";
	String pw = "123456";


	// ��� �߰�
	public int insertPerson(String name, String birthDate, String address, String phoneNum, String division,
			String position, String license, String accountNum, String joinDate) {

		String IdNum = setIdSequence(division)+".NEXTVAL,";//�ڵ� �������� ��� �����ϰ� ��****��Ͼȵ�
		System.out.println(IdNum);
		int num = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// �ش� ����̹��� �޸𸮿� ���ε�
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "insert into PInfo values("+IdNum+",?,?,?,?,?,?,?,?,?)";
			System.out.println(division);

			pstmt = conn.prepareStatement(sql1);
			//pstmt.setString(1, IdNum); // ����ǥ ��ġ ��ȣ, 1������ ����
			pstmt.setString(1, name);
			System.out.println(name);
			pstmt.setString(2, division);
			pstmt.setString(3, position);
			pstmt.setString(4, joinDate);
			pstmt.setString(5, birthDate);
			pstmt.setString(6, address);
			pstmt.setString(7, phoneNum);
			pstmt.setString(8, license);
			pstmt.setString(9, accountNum);
			num = pstmt.executeUpdate();
			
			
			PaymentDAO pdao = new PaymentDAO();
			//int basepay = position();
			String sql2 = "insert into Payment values(?,?)";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, IdNum+".curval");
			//pstmt2.setString(2, basepay);
			num = pstmt2.executeUpdate();
			

			String sql3 = "insert into monthrecord values(?)";
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, IdNum+".curval");
			num = pstmt3.executeUpdate();
			

			//���̺���� �⺻Ű�� ����� �Էµ� ���� �ڵ����� �����ǰ� ��

		} catch (ClassNotFoundException e) {// try���� ������ �߻��� ��� catch����
			e.printStackTrace(); // ������ �ܼ�â�� �ڼ��� �������
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// finally�� try ������ ������� ����
		finally { // �ݵ�� close �ؾ���, �������� ���߿� �������ͺ��� �ݱ�
			try {
				if (pstmt3 != null)
					pstmt3.close();
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

		return num;
	}

	private String setIdSequence(String division) {
		String seq = "";
		switch(division){
		case "ȸ����":
			seq = "seq_Account";
			break;
		case "����1��":
			seq = "seq_Sales";
			break;
		case "�μ�������":
			seq = "seq_Lap";
			break;
		case "���������":
			seq = "seq_Proposal";
			break;
		}
		return seq;
	}


	
	
	// ��� ����(�ٷ� ���� �ǰ� ������)
 	public int deletePerson(int IdNum) {
		int num = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// �ش� ����̹��� �޸𸮿� ���ε�
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "delete PInfo where IdNum = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, IdNum); // �ش� IdNum�� ���� ��� ����

			num = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {// try���� ������ �߻��� ��� catch����
			e.printStackTrace(); // ������ �ܼ�â�� �ڼ��� �������
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
		return num;
	}

	// ��� ����
	public int updatePerson(int IdNum, String address, String division, String position, String license,
			String accountNum, String phoneNum) {
		int num = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// �ش� ����̹��� �޸𸮿� ���ε�
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "update PInfo set address = ?, phoneNum = ?, "
					+ "division = ?, postion = ?, license = ?, accountNum = ?" + "where IdNum = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, address);
			pstmt.setString(2, phoneNum);
			pstmt.setString(3, division);
			pstmt.setString(4, position);
			pstmt.setString(5, license);
			pstmt.setString(6, accountNum);
			pstmt.setInt(7, IdNum); // �ش� IdNum�� ���� ��� ����

			num = pstmt.executeUpdate();

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
		return num;

	}

	// �˻����
	// ��ư� ������ �˻������ �Է°��� ���� �ٸ��� ����ǵ��� �����ε��� , int IdNum, String name
	public ArrayList<PersonVO> searchPerson(int IdNum) {
		ArrayList<PersonVO> temp = new ArrayList<>();
		PersonVO vo = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			String sql2 = "select * from PInfo where IdNum = ?";
			pstmt = conn.prepareStatement(sql2);

			pstmt.setInt(1, IdNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String tempIdNum = rs.getString(1); // 2��° �ִ� column�� ����
				// select * �� ��� getString(N) N��° column�� ����
				// select name, ppp �ϰ�� getString(1���� name, 2���� ppp)
				// select name// column�� �ϳ��� ���� getString(1)�̸� ��
				if (IdNum == Integer.parseInt(tempIdNum)) {
					vo = new PersonVO(rs.getString(1));
					System.out.println(tempIdNum);
					temp.add(vo);
				}
			}

		} catch (ClassNotFoundException e) {
			// try���� ������ �߻��� ��� catch����
			e.printStackTrace(); // ������ �ܼ�â�� �ڼ��� �������
		} catch (SQLException e) { // DriverManager.getConnection(url,id,pw)����
									// add.catch
			e.printStackTrace();
		} // finally�� try ������ ������� ����
		finally { // �ݵ�� close �ؾ���, �������� ���߿� �������ͺ��� �ݱ�
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	public ArrayList<PersonVO> searchPerson(String name) {
		ArrayList<PersonVO> temp = new ArrayList<>();
		PersonVO vo = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			String sql2 = "select * from PInfo where name = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String tempName = rs.getString(2); // 2��° �ִ� column�� ����
				// select * �� ��� getString(N) N��° column�� ����
				// select name, ppp �ϰ�� getString(1���� name, 2���� ppp)
				// select name// column�� �ϳ��� ���� getString(1)�̸� ��
				if (name.equals(tempName)) {
					vo = new PersonVO(tempName);
					temp.add(vo);
				}
			}

		} catch (ClassNotFoundException e) {
			// try���� ������ �߻��� ��� catch����
			e.printStackTrace(); // ������ �ܼ�â�� �ڼ��� �������
		} catch (SQLException e) { // DriverManager.getConnection(url,id,pw)����
									// add.catch
			e.printStackTrace();
		} // finally�� try ������ ������� ����
		finally { // �ݵ�� close �ؾ���, �������� ���߿� �������ͺ��� �ݱ�
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	// ���� �޼ҵ�
	public ArrayList<PersonVO> sortName() {// �̸� ����
		PersonVO vo = null;
		ArrayList<PersonVO> aLName = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "select name from PInfo order by name";
			pstmt = conn.prepareStatement(sql1);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1);
				vo = new PersonVO(name);
				aLName.add(vo);
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
		return aLName;

	}

	public ArrayList<PersonVO> sortDivision() {// �μ�����
		PersonVO vo = null;
		ArrayList<PersonVO> aLDivision = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "select name from PInfo order by division";
			pstmt = conn.prepareStatement(sql1);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1); //
				vo = new PersonVO(name);
				aLDivision.add(vo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // ������ �ܼ�â�� �ڼ��� �������
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
		return aLDivision;

	}

	public ArrayList<PersonVO> sortPosition() {// ��å����
		PersonVO vo = null;
		ArrayList<PersonVO> aLPosition = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "select name from PInfo order by position";
			pstmt = conn.prepareStatement(sql1);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1); //
				vo = new PersonVO(name);
				aLPosition.add(vo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // ������ �ܼ�â�� �ڼ��� �������
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
		return aLPosition;

	}

	public ArrayList<PersonVO> sortJoinDate() {// �Ի��ϼ� ����
		PersonVO vo = null;
		ArrayList<PersonVO> aLJoinDate = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "select name from PInfo order by joinDate";
			pstmt = conn.prepareStatement(sql1);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1); //
				vo = new PersonVO(name);
				aLJoinDate.add(vo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // ������ �ܼ�â�� �ڼ��� �������
		} catch (SQLException e) {
			e.printStackTrace();
		} // finally�� try ������ ������� ����
		finally { // �ݵ�� close �ؾ���, �������� ���߿� �������ͺ��� �ݱ�
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aLJoinDate;

	}

	// �̸� �Է½� �ش� IdNum�������� �޼ҵ�
	// public int getPersonIdNum(String name) {
	// int num = -1;
	// int IdNum=0;
	// try {
	// Class.forName("oracle.jdbc.driver.OracleDriver");
	// // �ش� ����̹��� �޸𸮿� ���ε�
	// conn = DriverManager.getConnection(url, id, pw);
	// String sql1 = "select IdNum from PInfo where name = ?";
	// pstmt = conn.prepareStatement(sql1);
	// pstmt.setString(1, name);
	//
	// num = pstmt.executeUpdate();
	// rs = pstmt.executeQuery();
	//
	// if (rs.next()) {
	// IdNum = rs.getInt(1);
	// }
	//
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (pstmt != null)
	// pstmt.close();
	// if (conn != null)
	// conn.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// return IdNum;
	// }

	// �̸� �Է½� �ش� IdNum�������� �޼ҵ�
	public int getPersonIdNum(String name) {
		int num = -1;
		int IdNum = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// �ش� ����̹��� �޸𸮿� ���ε�
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

	// ��������������� �޼ҵ� ������ : ������
	public ArrayList<PersonVO> getpersoninfo(String inputName) {
		PersonVO vo = null;
		ArrayList<PersonVO> getpersoninfo = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			String sql1 = "select * from PINFO where NAME = ?"; //����κ� ������ : �����
			System.out.println(sql1);

			pstmt = conn.prepareStatement(sql1);// ****�̰� ��������
			pstmt.setString(1, inputName); //����κ� ������ : �����

			rs = pstmt.executeQuery();

			while (rs.next()) {
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

				System.out.println(acc);

				vo = new PersonVO(idnum, name, div, pos, join, birth, add, phone, lic, acc);

				getpersoninfo.add(vo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // ������ �ܼ�â�� �ڼ��� �������
		} catch (SQLException e) {
			e.printStackTrace();
		} // finally�� try ������ ������� ����
		finally { // �ݵ�� close �ؾ���, �������� ���߿� �������ͺ��� �ݱ�
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getpersoninfo;

	}

}
