package fantastic_4;

import java.util.Scanner;

public class testMain {

	public static void main(String[] args) {
		// 관리자 ID, PW
		PmLoginDAO pmLogin = new PmLoginDAO("admin","1234");

		// 로그인 시도
		System.out.print("id : ");
		String id = new Scanner(System.in).nextLine();
		System.out.print("pw : ");
		String pw = new Scanner(System.in).nextLine();
		
		
		
		

	}

}
