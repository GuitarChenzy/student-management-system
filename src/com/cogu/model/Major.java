package com.cogu.model;

public class Major {

	private int pno;      //专业编号
	private String pname; //专业名称
	private String pdept; //专业所在院系

	public Major() {
		super();
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdept() {
		return pdept;
	}

	public void setPdept(String pdept) {
		this.pdept = pdept;
	}

	@Override
	public String toString() {
		return "Major [pno=" + pno + ", pname=" + pname + ", pdept=" + pdept + "]";
	}


}
