package com.cogu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cogu.mapper.UserMapper;
import com.cogu.model.Student;
import com.cogu.model.User;
import com.cogu.service.UserService;

@Service
@Transactional // 此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper mapper;

	public boolean delete(int userid) {

		return mapper.delete(userid);
	}

	public List<User> findAll() {
		List<User> findAllList = mapper.findAll();
		return findAllList;
	}

	public void save(User user) {

		mapper.save(user);
	}

	public boolean update(User user) {

		return mapper.update(user);
	}

	@Override
	public User findByUserid(int userid) {

		User user = mapper.findByUserid(userid);

		return user;
	}

	@Override
	public Integer userLogin(User user) {
		
		int status = user.getStatus();
		try {
			if (mapper.userLogin(user.getUsername(), user.getPassword(),status) != 0) {
				status = 200;
				System.out.println("用户" + user.getUsername() + "登录");
			} else {
				status = 401;
			}
		} catch (Exception e) {
			System.out.println("查无此人");
		}
		return status;

	}

}
