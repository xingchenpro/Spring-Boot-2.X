package com.hly.springbootSecurity.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/10/29
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //不需要验证的资源
                .antMatchers("/css/**","/index").permitAll()
                //需要验证，角色为Role
                .antMatchers("/article/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("USER")
                .and()
                //表单的登录地址和失败地址
                .formLogin().loginPage("/login").failureForwardUrl("/login-error")
                .and()
                //异常处理界面
                .exceptionHandling().accessDeniedPage("/401");
        http.logout().logoutSuccessUrl("/");
    }





}
