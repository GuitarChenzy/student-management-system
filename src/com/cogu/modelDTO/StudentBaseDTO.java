package com.cogu.modelDTO;

import com.cogu.model.Student;

public class StudentBaseDTO {

	private Student student;
	private String pname;
	private String pdept;
	private int num;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPdept() {
		return pdept;
	}

	public void setPdept(String pdept) {
		this.pdept = pdept;
	}

	@Override
	public String toString() {
		return "StudentBaseDTO [student=" + student + ", pname=" + pname + ", pdept=" + pdept + ", num=" + num + "]";
	}

}
