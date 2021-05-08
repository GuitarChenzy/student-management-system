package com.cogu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cogu.model.Student;
import com.cogu.model.User;

public interface UserMapper {

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Select("select * from user where username = #{username} and password = #{password} and status = #{status}")
	Integer userLogin(@Param("username") String username, @Param("password") String password,
			@Param("status") int status);

	/**
	 * 注册一个user
	 * 
	 * @param user
	 */
	void save(User user);

	/**
	 * 更新一个角色
	 * 
	 * @param user
	 * @return
	 */
	boolean update(User user);

	/**
	 * 删除一个角色
	 * 
	 * @param userid
	 * @return
	 */
	boolean delete(int userid);

	/**
	 * 根据userid得到对应用户
	 * 
	 * @param username
	 * @return
	 */
	User findByUserid(int userid);

	/**
	 * 得到所有用户
	 * 
	 * @return
	 */
	List<User> findAll();
	
}
