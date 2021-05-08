package com.cogu.service;

import java.util.List;

import com.cogu.model.Course;

public interface CourseService {

	/**
	 * 根据课程号获取课程信息
	 * @return
	 */
	Course getCourseByCno(int cno);
	/**
	 * 分页获取全部课程信息
	 * @param page
	 * @param size
	 * @return
	 */
	List<Course> getAllCourse(int page,int size);
	/**
	 * 获取所有课程
	 * @return
	 */
	List<Course> getAllCourse();
	/**
	 * 更新课程
	 * @param course
	 */
	boolean updateCourse(Course course);
	
	/**
	 * 保存课程信息
	 * @param course
	 * @return
	 */
	boolean saveCourse(Course course);
	
	/**
	 * 删除课程信息
	 * @param cno
	 * @return
	 */
	boolean deleteCourse(int cno);
	
	/**
	 * 获取课程总数
	 * @return
	 */
	Integer getCourseCount();
	
	/**
	 * 根据课程号获得选择该课程的学生总数
	 * @param cno
	 * @return
	 */
	Integer getStuCountByCno(int cno);
	
}
