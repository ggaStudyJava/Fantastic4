package fantastic_4;

import java.sql.Date;

public class PersonVO {
	private int idNum;
	private String name;
	private String division;
	private String position;
	private Date joinDate;
	private Date birthDate;
	private String address;
	private String phoneNum;
	private String license;
	
	private String accountNum;
	private Date quitDate;
	private int basePay;
	private int overWorkTime;
	private int serverancePay;

	

	public PersonVO(String name) {
		super();
		this.name = name;
	}

	public PersonVO(int idNum, String name, String division, String position, Date joinDate, Date birthDate,
			String address, String phoneNum, String license, String accountNum) {
		super();
		this.idNum = idNum;
		this.name = name;
		this.division = division;
		this.position = position;
		this.joinDate = joinDate;
		this.birthDate = birthDate;
		this.address = address;
		this.phoneNum = phoneNum;
		this.license = license;
		this.accountNum = accountNum;
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		System.out.println(name);
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	public int getBasePay() {
		return basePay;
	}

	public void setBasePay(int basePay) {
		this.basePay = basePay;
	}

	public int getOverWorkTime() {
		return overWorkTime;
	}

	public void setOverWorkTime(int overWorkTime) {
		this.overWorkTime = overWorkTime;
	}

	public int getServerancePay() {
		return serverancePay;
	}

	public void setServerancePay(int serverancePay) {
		this.serverancePay = serverancePay;
	}

	
	


}
