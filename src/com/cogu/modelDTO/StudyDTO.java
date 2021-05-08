package com.cogu.modelDTO;

public class StudyDTO {

	private String sno;
	private String register;
	private String graduate;
	private String studying;
	private String remain;
	private String term;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getGraduate() {
		return graduate;
	}

	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}

	public String getStudying() {
		return studying;
	}

	public void setStudying(String studying) {
		this.studying = studying;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
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
		return "StudyDTO [sno=" + sno + ", register=" + register + ", graduate=" + graduate + ", studying=" + studying
				+ ", remain=" + remain + ", term=" + term + "]";
	}

}
