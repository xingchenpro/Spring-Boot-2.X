package com.hly.springbootSecurity.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/10/30
 */
public class BCryptPasswordEncoderUtil {

public static void main(String[] args){

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // 加密
    String encodedPassword = passwordEncoder.encode("123");

    System.err.println(encodedPassword);
}


}
