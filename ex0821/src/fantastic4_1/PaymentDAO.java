package fantastic4_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaymentDAO {
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private Connection conn;
	private ResultSet rs;
	private int sumOverWorkTime = 0;

	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";// DB URL
	private String user = "fantastic4";
	private String password = "123456";

	// 세율
	private double Tax = 10;
	public final int OvWorkPay = 30000; // 초과근무수당

	private final int sawonPay = 30000000;
	private final int juimPay = 50000000;
	private final int daeriPay = 70000000;
	private final int gwajangPay = 90000000;
	private final int chajangPay = 110000000;
	private final int bujangPay = 130000000;
	private final int esaPay = 150000000;
	private final int sajangPay = 250000000;

	
	// 초과근무
		private int overPay = 30000; // 초과근무수당
		private int overTime = 5; // 초과근무시간
		private int totalOverTime = 0; // 총초과근무시간
		
		// 퇴직금
		private int outPay = 0;		// 퇴직금
		private int yearPay = 0;	// 연봉
		private double realPay = 0;	// 실수령금
			
		public double getTax() {
			return Tax;
		}
		public void setTax(double tax) {
			Tax = tax;
		}
		public int getOverTime() {
			return overTime;
		}
		public void setOverTime(int overTime) {
			this.overTime = overTime;
		}
		public int getTotalOverTime() {
			return totalOverTime;
		}
		public void setTotalOverTime(int totalOverTime) {
			this.totalOverTime = totalOverTime;
		}
		public int getSawonPay() {
			return sawonPay;
		}
		public int getJuimPay() {
			return juimPay;
		}
		public int getDaeriPay() {
			return daeriPay;
		}
		public int getGwajangPay() {
			return gwajangPay;
		}
		public int getChajangPay() {
			return chajangPay;
		}
		public int getBujangPay() {
			return bujangPay;
		}
		public int getEsaPay() {
			return esaPay;
		}
		public int getSajangPay() {
			return sajangPay;
		}

		public int getOverPay() {
			return overPay;
		}
		public void setOverPay(int overPay) {
			this.overPay = overPay;
		}
		public int getOutPay() {
			return outPay;
		}
		public void setOutPay(int outPay) {
			this.outPay = outPay;
		}
		public int getYearPay() {
			return yearPay;
		}
		public void setYearPay(int yearPay) {
			this.yearPay = yearPay;
		}
		public double getRealPay(int basePayment) {		
			realPay = (basePayment + (overPay*overTime)) * ((1-Tax)/100);
			return realPay;
		}
		public void setRealPay(int realPay) {
			this.realPay = realPay;
		}


}
