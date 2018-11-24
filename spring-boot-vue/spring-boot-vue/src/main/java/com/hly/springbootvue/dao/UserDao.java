package com.hly.springbootvue.dao;

import com.hly.springbootvue.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/11/24
 */
@Repository
public interface UserDao {

    public User selectUserByUsername(String username);

}
