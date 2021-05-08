package com.cogu.service;

import java.util.List;

import com.cogu.model.Course;
import com.cogu.model.Major;

public interface MajorService {

	/**
	 * 根据专业号获取专业信息
	 * @return
	 */
	Major getMajorByPno(int pno);
	/**
	 * 分页获取全部专业信息
	 * @param page
	 * @param size
	 * @return
	 */
	List<Major> getAllMajor(int page,int size);
	/**
	 * 获取所有专业信息
	 * @return
	 */
	List<Major> getAllMajor();
	/**
	 * 更新专业
	 * 
	 * @param major
	 */
	boolean updateMajor(Major major);
	
	/**
	 * 保存专业信息
	 * @param major
	 * @return
	 */
	boolean saveMajor(Major major);
	
	/**
	 * 删除专业信息
	 * @param pno
	 * @return
	 */
	boolean deleteMajor(int pno);
	
	/**
	 * 获取专业总数
	 * @return
	 */
	Integer getMajorCount();
	
	/**
	 * 根据专业号统计该专业有多少人
	 * @param pno
	 * @return
	 */
	Integer getStuCountByPno(int pno);
	
}
