package com.cogu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cogu.mapper.StudyMapper;
import com.cogu.model.Study;
import com.cogu.service.StudyService;

@Service
@Transactional
public class StudyServiceImpl implements StudyService{

	@Resource
	private StudyMapper studyMapper;
	
	@Override
	public List<Study> getAllStudy(int page, int size) {
		return studyMapper.getAllStudy(page, size);
	}

	@Override
	public List<Study> getAllStudyBySno(String sno, int page, int size) {
		return studyMapper.getAllStudyBySno(sno, page, size);
	}

	@Override
	public List<String> getTermsBySno(String sno) {
		return studyMapper.getTermsBySno(sno);
	}

	@Override
	public boolean save(Study study) {
		return studyMapper.save(study);
	}

	@Override
	public boolean update(Study study) {
		return studyMapper.update(study);
	}

	@Override
	public boolean delete(String sno, String term) {
		return studyMapper.delete(sno, term);
	}

	@Override
	public Integer getAllStudyCount() {
		return studyMapper.getAllStudyCount();
	}

	@Override
	public Integer getAllStudyCountBySno(String sno) {
		return studyMapper.getAllStudyCountBySno(sno);
	}

	@Override
	public List<Study> getALlStudy() {
		return studyMapper.getAll();
	}

	
	
}
