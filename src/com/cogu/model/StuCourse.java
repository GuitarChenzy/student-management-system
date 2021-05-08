package com.cogu.model;

public class StuCourse {

	private String sno;
	private String psenior; // 学期
	private int cno;
	private double grade;

	public StuCourse() {
		super();
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getPsenior() {
		return psenior;
	}

	public void setPsenior(String psenior) {
		this.psenior = psenior;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "StuCourse [sno=" + sno + ", psenior=" + psenior + ", cno=" + cno + ", grade=" + grade + "]";
	}

}
