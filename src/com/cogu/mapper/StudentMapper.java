package com.cogu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cogu.model.Student;

public interface StudentMapper {

	/**
	 * 学生信息分页
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@Select("select * from student limit #{page},#{size}")
	List<Student> getAllWithLimit(@Param("page") int page, @Param("size") int size);

	/**
	 * 获取全部学生信息
	 * 
	 * @return
	 */
	@Select("select * from student")
	List<Student> getAll();

	/**
	 * 根据学号获取该学生
	 * 
	 * @param sno
	 * @return
	 */
	@Select("select * from student where sno = #{sno}")
	Student getStuBySno(@Param("sno") String sno);

	/**
	 * 保存一个学生信息
	 * 
	 * @param student
	 * @return 
	 */
	@Insert("insert into student value(#{sno},#{sname},#{ssex}," + "#{scard},#{stele},#{sbirth},#{sstudytime})")
	boolean save(Student student);

	/**
	 * 更新一个学生信息
	 * 
	 * @param student
	 * @return
	 */
	@Update("update student set sname = #{sname} , ssex = #{ssex},scard = #{scard},"
			+ "stele = #{stele},sbirth = #{sbirth} ,sstudytime = #{sstudytime} where sno = #{sno}")
	boolean update(Student student);

	/**
	 * 根据sno删除一个学生
	 * 
	 * @param sno
	 * @return
	 */
	@Delete("delete from student where sno = #{sno}")
	boolean delete(@Param("sno")String sno);
	
	/**
	 * 根据学号删除对应的学生的课程
	 * @param sno
	 * @return
	 */
	@Delete("delete from stu_course where sno = #{sno}")
	boolean deleteCourseBySno(@Param("sno")String sno);
	/**
	 * 获得学生总数
	 * @return
	 */
	@Select("select count(*) from student")
	Integer getCount();

	/**
	 * 增加学生的同时增加该学生的专业
	 * @param pno
	 * @param sno
	 * @return
	 */
	@Insert("insert into stu_major values(#{sno},#{pno})")
	boolean saveMajor(@Param("sno") String sno,@Param("pno") int pno);
	
	/**
	 * 更新学生的专业信息
	 * @param sno
	 * @param pno
	 * @return
	 */
	@Update("update stu_major set pno = #{pno} where sno = #{sno} ")
	boolean updateMajor(@Param("sno")String sno,@Param("pno") int pno);
	
}
