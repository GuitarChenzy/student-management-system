package com.cogu.modelDTO;

import com.cogu.model.Course;

public class CourseDTO {

	private Course course;
	private int num;
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "CourseDTO [course=" + course + ", num=" + num + "]";
	}
	
	
	
}
