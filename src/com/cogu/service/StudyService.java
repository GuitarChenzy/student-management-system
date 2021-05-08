package com.cogu.service;

import java.util.List;

import com.cogu.model.Study;

public interface StudyService {

	/**
	 * 得到所有学生就读信息
	 * @return
	 */
	List<Study> getALlStudy();
	/**
	 * 得到所有学生的就读信息
	 * 分页
	 * @return
	 */
	List<Study> getAllStudy(int page, int size);

	/**
	 * 根据学号得到该学生的就读信息
	 * 
	 * @param sno
	 * @param page
	 * @param size
	 * @return
	 */
	List<Study> getAllStudyBySno(String sno, int page, int size);

	/**
	 * 根据学号得到该学生的所有学期
	 * 
	 * @param sno
	 * @return
	 */
	List<String> getTermsBySno(String sno);

	/**
	 * 保存一个学生的就读信息
	 * 
	 * @param study
	 * @return
	 */
	boolean save(Study study);

	/**
	 * 更新一个学生的就读信息
	 * 
	 * @param studt
	 * @return
	 */
	boolean update(Study study);

	/**
	 * 删除一个学生的就读信息
	 * 
	 * @param sno
	 * @param term
	 * @return
	 */
	boolean delete(String sno, String term);
	
	/**
	 * 得到所有就读信息的个数
	 * @return
	 */
	Integer getAllStudyCount();
	
	/**
	 * 根据学号得到该学生的就读信息数
	 * @param sno
	 * @return
	 */
	Integer getAllStudyCountBySno(String sno);

}
