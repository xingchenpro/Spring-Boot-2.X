package com.hly.springbootDocker.dao;

import com.hly.springbootDocker.domain.User;
import org.springframework.stereotype.Component;

/**
 * @author hly
 * @blog:https://blog.csdn.net/Sirius_hly
 * @github:https://github.com/huangliangyun
 * @date 2018年9月12日 下午7:21:50
 */
@Component
public interface UserDao {
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public boolean insertUser(User user);
	/**
	 * 根据Id查询用户
	 * @param userId
	 * @return
	 */
	public User selectUserById(String userId);
	
	
}

