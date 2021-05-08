package com.cogu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cogu.mapper.MajorMapper;
import com.cogu.model.Major;
import com.cogu.service.MajorService;

@Service
@Transactional
public class MajorServiceImpl implements MajorService {

	@Resource
	private MajorMapper majorMapper;
	
	@Override
	public Major getMajorByPno(int pno) {
		return majorMapper.getMajorBypno(pno);
	}

	@Override
	public List<Major> getAllMajor(int page, int size) {
		return majorMapper.getAllWithLimit(page, size);
	}

	@Override
	public List<Major> getAllMajor() {
		return majorMapper.getAll();
	}

	@Override
	public boolean updateMajor(Major major) {
		return majorMapper.update(major);
	}

	@Override
	public boolean saveMajor(Major major) {
		return majorMapper.save(major);
	}

	@Override
	public boolean deleteMajor(int pno) {
		return majorMapper.delete(pno);
	}

	@Override
	public Integer getMajorCount() {
		return majorMapper.getMajorCount();
	}

	@Override
	public Integer getStuCountByPno(int pno) {
		return majorMapper.getStuCountByCno(pno);
	}

}
