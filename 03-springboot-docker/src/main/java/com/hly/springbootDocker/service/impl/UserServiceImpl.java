package com.hly.springbootDocker.service.impl;

import com.hly.springbootDocker.dao.UserDao;
import com.hly.springbootDocker.domain.User;
import com.hly.springbootDocker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author hly
 * @blog:https://blog.csdn.net/Sirius_hly
 * @github:https://github.com/huangliangyun
 * @date 2018年9月13日 下午6:25:46
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public User selectUserById(String userId) {
		
		return userDao.selectUserById(userId);
	}

	@Override
	public boolean insertUser(User user) {
		
		return userDao.insertUser(user);
	}
	
	

	

}

