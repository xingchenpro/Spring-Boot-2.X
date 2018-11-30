package com.hly.springbootSecurity.dao;

import com.hly.springbootSecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User ,Integer> {
    User findByUsername(String username);
}
