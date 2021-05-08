package com.cogu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cogu.model.Study;

public interface StudyMapper {

	/**
	 * 得到所有学生的就读信息
	 * @return
	 */
	@Select("select * from study")
	List<Study> getAll();
	/**
	 * 得到所有学生的就读信息
	 * 分页
	 * @return
	 */
	@Select("select * from study limit #{page},#{size}")
	List<Study> getAllStudy(@Param("page") int page, @Param("size") int size);

	/**
	 * 根据学号得到该学生的就读信息
	 * 
	 * @param sno
	 * @param page
	 * @param size
	 * @return
	 */
	@Select("select * from study where sno = #{sno} limit #{page},#{size}")
	List<Study> getAllStudyBySno(@Param("sno") String sno, @Param("page") int page, @Param("size") int size);

	/**
	 * 根据学号得到该学生的所有学期
	 * 
	 * @param sno
	 * @return
	 */
	@Select("select term from study where sno = #{sno}")
	List<String> getTermsBySno(@Param("sno") String sno);

	/**
	 * 保存一个学生的就读信息
	 * 
	 * @param study
	 * @return
	 */
	@Insert("insert into study values(#{sno},#{register},#{graduate},#{studying},#{remain},#{term})")
	boolean save(Study study);

	/**
	 * 更新一个学生的就读信息
	 * @param studt
	 * @return
	 */
	@Update("update study set register = #{register},graduate = #{graduate},studying = #{studying},remain = #{remain} where sno = #{sno} and term = #{term}  ")
	boolean update(Study study);
		
	/**
	 * 删除一个学生的就读信息
	 * @param sno
	 * @param term
	 * @return
	 */
	@Delete("delete from study where sno = #{sno} and term = #{term}")
	boolean delete(@Param("sno")String sno,@Param("term") String term);
	
	/**
	 * 得到所有就读信息的个数
	 * @return
	 */
	@Select("select count(*) from study")
	Integer getAllStudyCount();
	
	/**
	 * 根据学号得到该学生的就读信息数
	 * @param sno
	 * @return
	 */
	@Select("select count(*) from study where sno = #{sno} ")
	Integer getAllStudyCountBySno(@Param("sno")String sno);
}
