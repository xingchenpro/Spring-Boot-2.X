package com.hly.springbootSecurity.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class BCryptPasswordEncoderUtil {

public static void main(String[] args){

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // 加密
    String encodedPassword = passwordEncoder.encode("123");
    System.err.println(encodedPassword);
}
}
