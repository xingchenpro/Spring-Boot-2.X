package com.hly.springbootSecurity.dao;

import com.hly.springbootSecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/10/30
 */
public interface UserDao extends JpaRepository<User ,Integer> {
    User findByUsername(String username);
}
