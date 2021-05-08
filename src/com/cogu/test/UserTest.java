package com.cogu.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cogu.mapper.StudentMapper;
import com.cogu.mapper.UserMapper;
import com.cogu.model.Student;
import com.cogu.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring-common.xml")
public class UserTest {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	@Test
	public void testFindAll(){
		/*List<User> findAllList = userMapper.findAll();
		System.out.println(findAllList.size());
		int i = userMapper.userLogin("chenzeyu", "123456",1);
		System.out.println(i);*/
		Student s = studentMapper.getStuBySno("201516080210");
		System.out.println(s);
	}
	
}
