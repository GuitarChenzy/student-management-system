package com.cogu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cogu.mapper.CourseMapper;
import com.cogu.model.Course;
import com.cogu.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseMapper courseMapper;
	
	@Override
	public Course getCourseByCno(int cno) {
		return courseMapper.getStuBycno(cno);
	}

	@Override
	public List<Course> getAllCourse(int page, int size) {
		return courseMapper.getAllWithLimit(page, size);
	}

	@Override
	public List<Course> getAllCourse() {
		return courseMapper.getAll();
	}

	@Override
	public boolean updateCourse(Course course) {
		return courseMapper.update(course);
	}

	@Override
	public boolean saveCourse(Course course) {
		return courseMapper.save(course);
	}

	@Override
	public boolean deleteCourse(int cno) {
		return courseMapper.delete(cno);
	}

	@Override
	public Integer getCourseCount() {
		return courseMapper.getCourseCount();
	}

	@Override
	public Integer getStuCountByCno(int cno) {
		return courseMapper.getStuCountByCno(cno);
	}

}
