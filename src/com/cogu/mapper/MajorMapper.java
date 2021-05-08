package com.cogu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cogu.model.Major;

public interface MajorMapper {

	/**
	 * 专业信息
	 * @param page
	 * @param size
	 * @return
	 */
	@Select("select * from major limit #{page},#{size}")
	List<Major> getAllWithLimit(@Param("page")int page,@Param("size")int size);
	
	/**
	 * 获取全部专业信息
	 * 
	 * @return
	 */
	@Select("select * from major")
	List<Major> getAll();

	/**
	 * 根据专业号获取该专业
	 * 
	 * @param pno
	 * @return
	 */
	@Select("select * from major where pno = #{pno}")
	Major getMajorBypno(@Param("pno") int pno);

	/**
	 * 保存一个专业信息
	 * 
	 * @param major
	 * @return 
	 */
	@Insert("insert into major value(#{pno},#{pname},#{pdept})")
	boolean save(Major major);

	/**
	 * 更新一个专业信息
	 * 此操作也会更新学生学生表里的对应的所有pno的信息
	 * @param major
	 * @return
	 */
	@Update("update major set pname = #{pname} , pdept = #{pdept} where pno = #{pno}")
	boolean update(Major major);

	/**
	 * 根据pno删除一个专业
	 * 此操作也会删掉学生专业表里的对应的所有pno的信息
	 * @param pno
	 * @return
	 */
	@Delete("delete from major where pno = #{pno}")
	boolean delete(@Param("pno")int pno);
	
	/**
	 * 根据专业号获得专业总数
	 * @return
	 */
	@Select("select count(pno) from major")
	Integer getMajorCount();
	
	/**
	 * 根据课程号得到该专业的学生总数
	 * @param pno
	 * @return
	 */
	@Select("select count(sno) from stu_major where pno = #{pno} ")
	Integer getStuCountByCno(@Param("pno") int pno);
	
}
