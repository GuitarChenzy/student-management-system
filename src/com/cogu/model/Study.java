package com.cogu.model;

public class Study {

	private String sno;
	private int register;
	private int graduate;
	private int studying;
	private int remain;
	private String term;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public int getRegister() {
		return register;
	}

	public void setRegister(int register) {
		this.register = register;
	}

	public int getGraduate() {
		return graduate;
	}

	public void setGraduate(int graduate) {
		this.graduate = graduate;
	}

	public int getStudying() {
		return studying;
	}

	public void setStudying(int studying) {
		this.studying = studying;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Override
	public String toString() {
		return "Study [sno=" + sno + ", register=" + register + ", graduate=" + graduate + ", studying=" + studying
				+ ", remain=" + remain + ", term=" + term + "]";
	}

}
