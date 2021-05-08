package com.cogu.model;

/**
 * 学生表
 * @author czy
 *
 */
public class Student {

	private String sno;
	private String sname;
	private String ssex;
	private String scard;
	private String stele;
	private String sbirth;
	private String sstudytime;

	public Student() {
		super();
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getScard() {
		return scard;
	}

	public void setScard(String scard) {
		this.scard = scard;
	}

	public String getStele() {
		return stele;
	}

	public void setStele(String stele) {
		this.stele = stele;
	}

	public String getSbirth() {
		return sbirth;
	}

	public void setSbirth(String sbirth) {
		this.sbirth = sbirth;
	}

	public String getSstudytime() {
		return sstudytime;
	}

	public void setSstudytime(String sstudytime) {
		this.sstudytime = sstudytime;
	}

	@Override
	public String toString() {
		return "Student [sno=" + sno + ", sname=" + sname + ", ssex=" + ssex + ", scard=" + scard + ", stele=" + stele
				+ ", sbirth=" + sbirth + ", sstudytime=" + sstudytime + "]";
	}

}
