package com.cogu.modelDTO;

import com.cogu.model.Major;

public class MajorDTO {

	private Major major;
	private int num;

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "MajorDTO [major=" + major + ", num=" + num + "]";
	}

}
