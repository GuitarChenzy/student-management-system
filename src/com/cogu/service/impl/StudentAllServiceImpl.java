package com.cogu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cogu.mapper.StudentAllMapper;
import com.cogu.model.StuCourse;
import com.cogu.modelDTO.CnameAndCnoDTO;
import com.cogu.service.StudentAllService;

@Service
@Transactional
public class StudentAllServiceImpl implements StudentAllService {

	@Resource
	private StudentAllMapper studentAllMapper;

	@Override
	public List<StuCourse> getStuCoursesBySno(String sno, int page, int size) {
		return studentAllMapper.getStuCoursesBySno(sno, page, size);
	}

	@Override
	public Integer getStuCourseCountBySno(String sno) {
		return studentAllMapper.getStuCourseCountBySno(sno);
	}

	@Override
	public String getPnameBySno(String sno) {
		return studentAllMapper.getPnameBySno(sno);
	}

	@Override
	public String getPdeptBySno(String sno) {
		return studentAllMapper.getPdeptBySno(sno);
	}

	@Override
	public String getSnameBySno(String sno) {
		return studentAllMapper.getSnameBySno(sno);
	}

	@Override
	public String getCnameByCno(int cno) {
		return studentAllMapper.getCnameByCno(cno);
	}

	@Override
	public List<CnameAndCnoDTO> getCourses(String sno) {
		return studentAllMapper.getCourses(sno);
	}

	@Override
	public boolean saveCourseBySno(StuCourse stuCourse) {
		return studentAllMapper.saveCourseBySno(stuCourse);
	}

	@Override
	public boolean deleteCourseBySno_Cno_Psenior(String sno, int cno, String psenior) {
		return studentAllMapper.deleteCourseBySno_Cno_Psenior(sno, cno, psenior);
	}

}
