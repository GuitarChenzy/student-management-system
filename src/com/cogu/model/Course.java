package com.cogu.model;

public class Course {

	private int cno; // 课程编号
	private String cname; // 课程名称
	private int cperiod; // 学时

	public Course() {
		super();
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getCperiod() {
		return cperiod;
	}

	public void setCperiod(int cperiod) {
		this.cperiod = cperiod;
	}

	@Override
	public String toString() {
		return "Course [cno=" + cno + ", cname=" + cname + ", cperiod=" + cperiod + "]";
	}

}
