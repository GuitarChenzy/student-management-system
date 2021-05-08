package com.cogu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cogu.model.StuCourse;
import com.cogu.modelDTO.CnameAndCnoDTO;

public interface StudentAllService {

	/**
	 * 根据学号得到该学生选择的课程信息
	 * 
	 * @param sno
	 * @return
	 */
	List<StuCourse> getStuCoursesBySno(String sno, int page, int size);

	/**
	 * 根据学号得到该学生选修的课程数量
	 * 
	 * @param sno
	 * @return
	 */
	Integer getStuCourseCountBySno(String sno);

	/**
	 * 根据学号得到该学生的专业名称与学院名称
	 * 
	 * @param sno
	 * @return
	 */
	String getPnameBySno(String sno);

	/**
	 * 根据学号得到该学生的学院名称
	 * 
	 * @param sno
	 * @return
	 */
	String getPdeptBySno(String sno);

	/**
	 * 根据学号得到学生姓名
	 * 
	 * @param sno
	 * @return
	 */
	String getSnameBySno(String sno);

	/**
	 * 根据课程号得到课程名称
	 * 
	 * @param cno
	 * @return
	 */
	String getCnameByCno(int cno);

	/**
	 * 得到课程名和课程号
	 * @return
	 */
	List<CnameAndCnoDTO> getCourses(String sno);
	
	/**
	 * 保存一个学生的选课信息
	 * @param stuCourse
	 * @return
	 */
	boolean saveCourseBySno(StuCourse stuCourse);
	
	/**
	 * 
	 * @param sno
	 * @param cno
	 * @param psenior
	 * @return
	 */
	boolean deleteCourseBySno_Cno_Psenior(@Param("sno")String sno,@Param("cno")int cno,@Param("psenior") String psenior);
}
