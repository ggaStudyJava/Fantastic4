package fantastic_4;

import java.sql.Date;

public class PmLoginDAO {

	private String id;
	private String pw;

	public PmLoginDAO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}



}
