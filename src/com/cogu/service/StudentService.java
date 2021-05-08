package com.cogu.service;

import java.util.List;

import com.cogu.model.Student;

public interface StudentService {
	
	/**
	 * 获取当前登录学生的信息
	 * @return
	 */
	Student getStudentBySno(String sno);
	/**
	 * 分页获取全部学生信息
	 * @param page
	 * @param size
	 * @return
	 */
	List<Student> getAllStudent(int page,int size);
	/**
	 * 获取所有学生
	 * @return
	 */
	List<Student> getAllStudent();
	/**
	 * 学生更新自己信息
	 * @param student
	 */
	boolean updateStudent(Student student);
	
	/**
	 * 保存学生信息
	 * @param student
	 * @return
	 */
	boolean saveStudent(Student student);
	
	/**
	 * 删除学生信息
	 * @param sno
	 * @return
	 */
	boolean deleteStudent(String sno);
	/**
	 * 删除学生相关的选课
	 * @param sno
	 * @return
	 */
	boolean deleteCourseBySno(String sno);
	/**
	 * 获取学生总数
	 * @return
	 */
	Integer getCount();
	
	/**
	 * 增加学生到学生专业表中
	 * @param sno
	 * @param pno
	 * @return
	 */
	boolean saveMajor(String sno,int pno);
	/**
	 * 更新学生专业信息
	 * @param sno
	 * @param pno
	 * @return
	 */
	boolean updateMajor(String sno,int pno);
}
