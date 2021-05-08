package com.cogu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cogu.model.StuCourse;
import com.cogu.modelDTO.CnameAndCnoDTO;

public interface StudentAllMapper {
	
	/**
	 * 根据学号得到该学生选择的课程信息
	 * @param sno
	 * @return
	 */
	@Select("select * from stu_course where sno = #{sno} limit #{page},#{size} ")
	List<StuCourse> getStuCoursesBySno(@Param("sno") String sno,@Param("page") int page, @Param("size") int size);
	
	/**
	 * 根据学号得到该学生选修的课程数量
	 * @param sno
	 * @return
	 */
	@Select("select count(cno) from stu_course where sno = #{sno} ")
	Integer getStuCourseCountBySno(@Param("sno") String sno);
	
	/**
	 * 根据学号得到该学生的专业名称
	 * @param sno
	 * @return
	 */
	@Select("select pname from stu_major,major where sno = #{sno} and stu_major.pno = major.pno")
	String getPnameBySno(@Param("sno")String sno );

	/**
	 * 根据学号得到该学生的学院名称
	 * @param sno
	 * @return
	 */
	@Select("select pdept from stu_major,major where sno = #{sno} and stu_major.pno = major.pno")
	String getPdeptBySno(@Param("sno")String sno);
	
	/**
	 * 根据学号得到学生姓名
	 * @param sno
	 * @return
	 */
	@Select("select sname from student where sno = #{sno}")
	String getSnameBySno(@Param("sno")String sno);
	
	/**
	 * 根据课程号得到课程名称
	 * @param cno
	 * @return
	 */
	@Select("select cname from course where cno = #{cno}")
	String getCnameByCno(@Param("cno")int cno);
	
	/**
	 * 得到课程名和课程号
	 * @return
	 */
	@Select("select cno,cname from course where "
			+ "cno not in(select cno from stu_course where "
			+ "sno in(select sno from student where sno = #{sno}))")
	List<CnameAndCnoDTO> getCourses(@Param("sno")String sno);
	
	/**
	 * 保存一个学生的选课信息
	 * @param stuCourse
	 * @return
	 */
	@Insert("insert into stu_course values(#{sno},#{cno},#{psenior},#{grade})")
	boolean saveCourseBySno(StuCourse stuCourse);
	
	/**
	 * 删除一个学生的课程
	 * @param sno
	 * @param cno
	 * @param psenior
	 * @return
	 */
	@Delete("delete from stu_course where sno = #{sno} and cno = #{cno} and psenior = #{psenior}")
	boolean deleteCourseBySno_Cno_Psenior(@Param("sno")String sno,@Param("cno")int cno,@Param("psenior") String psenior);
}
