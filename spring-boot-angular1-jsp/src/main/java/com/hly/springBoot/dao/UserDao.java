package com.hly.springBoot.dao;


import com.hly.springBoot.domain.User;
import org.springframework.stereotype.Component;

/**
 * @author hly
 * @blog:https://blog.csdn.net/Sirius_hly
 * @github:https://github.com/SiriusHly
 * @date 2018年9月12日 下午7:21:50
 */
public interface UserDao {
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public boolean insertUser(User user);
	/**
	 * &#x6839;&#x636e;Id&#x67e5;&#x8be2;&#x7528;&#x6237;
	 * @param userId
	 * @return
	 */
	public User selectUserById(String userId);
}

