package com.cogu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cogu.model.Course;
import com.cogu.model.StuCourse;

public interface CourseMapper {
	/**
	 * 课程信息
	 * @param page
	 * @param size
	 * @return
	 */
	@Select("select * from course limit #{page},#{size}")
	List<Course> getAllWithLimit(@Param("page")int page,@Param("size")int size);
	
	/**
	 * 获取全部课程信息信息
	 * 
	 * @return
	 */
	@Select("select * from course")
	List<Course> getAll();

	/**
	 * 根据课程号获取该课程
	 * 
	 * @param sno
	 * @return
	 */
	@Select("select * from course where cno = #{cno}")
	Course getStuBycno(@Param("cno") int cno);

	/**
	 * 保存一个课程信息
	 * 
	 * @param course
	 * @return 
	 */
	@Insert("insert into course value(#{cno},#{cname},#{cperiod})")
	boolean save(Course course);

	/**
	 * 更新一个课程信息
	 * 此操作也会更新学生课程表里的对应的所有cno的信息
	 * @param course
	 * @return
	 */
	@Update("update course set cname = #{cname} , cperiod = #{cperiod} where cno = #{cno}")
	boolean update(Course course);

	/**
	 * 根据cno删除一个课程
	 * 此操作也会删掉学生课程表里的对应的所有cno的信息
	 * @param cno
	 * @return
	 */
	@Delete("delete from course  where cno = #{cno}")
	boolean delete(@Param("cno")int cno);
	
	/**
	 * 获得课程总数
	 * @return
	 */
	@Select("select count(cno) from course")
	Integer getCourseCount();
	
	/**
	 * 根据课程号得到选择这门课程的学生总数
	 * @param cno
	 * @return
	 */
	@Select("select count(sno) from stu_course where cno = #{cno} ")
	Integer getStuCountByCno(@Param("cno") int cno);
	
}
