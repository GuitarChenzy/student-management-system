package com.cogu.modelDTO;

public class CnameAndCnoDTO {

	private String cname;
	private int cno;
	
	
	
	/*public CnameAndCnoDTO(String cname, int cno) {
		super();
		this.cname = cname;
		this.cno = cno;
	}*/
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	@Override
	public String toString() {
		return "CnameAndCnoDTO [cname=" + cname + ", cno=" + cno + "]";
	}
	
	
	
}
