package com.cogu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cogu.mapper.StudentMapper;
import com.cogu.model.Student;
import com.cogu.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapper studentMapper;

	@Override
	public Student getStudentBySno(String sno) {
		return studentMapper.getStuBySno(sno);
	}

	@Override
	public boolean updateStudent(Student student) {
		return studentMapper.update(student);
	}

	@Override
	public List<Student> getAllStudent() {
		return studentMapper.getAll();
		
	}
	
	@Override
	public boolean saveStudent(Student student){
		return studentMapper.save(student);
	}
	
	@Override
	public boolean deleteStudent(String sno){
		return studentMapper.delete(sno);
	}
	
	@Override
	public List<Student> getAllStudent(int page,int size){
		return studentMapper.getAllWithLimit(page, size);
	}

	@Override
	public Integer getCount() {
		return studentMapper.getCount();
	}

	@Override
	public boolean saveMajor(String sno, int pno) {
		return studentMapper.saveMajor(sno, pno);
	}

	@Override
	public boolean deleteCourseBySno(String sno) {
		return studentMapper.deleteCourseBySno(sno);
	}

	@Override
	public boolean updateMajor(String sno, int pno) {
		return studentMapper.updateMajor(sno, pno);
	}
	
}
