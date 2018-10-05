package com.hly.springBoot.service;


import com.hly.springBoot.domain.User;

/**
 * @author hly
 * @blog:https://blog.csdn.net/Sirius_hly
 * @github:https://github.com/SiriusHly
 * @date 2018年9月13日 下午6:22:04
 */
public interface UserService {
	
	/**
	 * 查询用户
	 * @param userId
	 * @return
	 */
	public User selectUserById(String userId);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean insertUser(User user);

}

