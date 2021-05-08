package com.cogu.modelDTO;

import com.cogu.model.StuCourse;

public class AllCoursesDTO {

	private StuCourse stuCourse;
	private CnameAndCnoDTO cnameAndCnoDTO;
	private String sname;

	public AllCoursesDTO(StuCourse stuCourse, CnameAndCnoDTO cnameAndCnoDTO, String sname) {
		super();
		this.stuCourse = stuCourse;
		this.cnameAndCnoDTO = cnameAndCnoDTO;
		this.sname = sname;
	}

	public StuCourse getStuCourse() {
		return stuCourse;
	}

	public void setStuCourse(StuCourse stuCourse) {
		this.stuCourse = stuCourse;
	}

	public CnameAndCnoDTO getCnameAndCnoDTO() {
		return cnameAndCnoDTO;
	}

	public void setCnameAndCnoDTO(CnameAndCnoDTO cnameAndCnoDTO) {
		this.cnameAndCnoDTO = cnameAndCnoDTO;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	@Override
	public String toString() {
		return "AllCoursesDTO [stuCourse=" + stuCourse + ", cnameAndCnoDTO=" + cnameAndCnoDTO + ", sname=" + sname
				+ "]";
	}

}
