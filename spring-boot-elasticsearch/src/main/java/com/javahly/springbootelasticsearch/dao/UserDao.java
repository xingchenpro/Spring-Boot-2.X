package com.javahly.springbootelasticsearch.dao;

import com.javahly.springbootelasticsearch.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/12
 * @QQ :1136513099
 * @desc :
 */

public interface UserDao extends CrudRepository<User,String>{

}
