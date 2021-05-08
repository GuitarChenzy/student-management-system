package com.cogu.model;

public class StuMajor {

	private String id; // 人员编号
	private int pno; // 专业编号

	public StuMajor() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	@Override
	public String toString() {
		return "StuMajor [id=" + id + ", pno=" + pno + "]";
	}

}
