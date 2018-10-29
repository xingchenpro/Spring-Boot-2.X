package com.hly.springbootSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/10/29
 */

@EnableWebSecurity//开启WebSecurity功能
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * 注入 AuthenticationManagerBuilder Bean
     * 1.认证应用每一个请求
     * 2.自动生成登录表单
     * 3.u,p进行认证
     * 4.用户可注销
     * 5.阻止CSRU攻击
     * 6.Session Fixation保护
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("hly").password("123").roles("USER");
    }

}
