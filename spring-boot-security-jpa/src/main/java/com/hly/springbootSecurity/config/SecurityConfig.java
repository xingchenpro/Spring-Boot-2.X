package com.hly.springbootSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity//开启WebSecurity功能
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法上的保护
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //无视报错，程序运行时执行，编译器也许不知道
    @Autowired
    UserDetailsService userDetailsService;

    /**
     *
     * @param auth
     * @throws Exception
     */

    /**
     * 注入 AuthenticationManagerBuilder Bean
     * 1.认证应用每一个请求
     * 2.自动生成登录表单
     * 3.u,p进行认证
     * 4.用户可注销
     * 5.阻止CSRF攻击
     * 6.Session Fixation保护
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()),相当于登陆时用BCrypt加密方式对用户密码进行处理，和下面比对一致，允许登录
        //从内存中读取用户信息
        /*auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("hly").password(passwordEncoder().encode("123")).roles("USER", "ADMIN").and()
                .withUser("user").password(passwordEncoder().encode("123")).roles("USER");*/

        /*auth.inMemoryAuthentication()
                //添加测试用户
                .withUser("hly").password("$2a$10$NmtmORbN/ATToou17gvjl.CUu1yTNxxRjsO2GOJUbJFsWd21pYmFi").roles("USER", "ADMIN").and()
                .withUser("user").password("$2a$10$NmtmORbN/ATToou17gvjl.CUu1yTNxxRjsO2GOJUbJFsWd21pYmFi").roles("USER");*/

        //从数据库中获取用户认证信息
        auth.userDetailsService(userDetailsService);
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //不需要验证的资源
                .antMatchers("/css/**", "/index").permitAll()
                //需要验证，角色为Role
                .antMatchers("/article/**").hasAnyRole("ADMIN","STUDENT","TEACHER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN","STUDENT","TEACHER")
                .and()
                //表单的登录地址和失败地址
                .formLogin().loginPage("/login").failureForwardUrl("/loginError")
                .and()
                //异常处理界面
                .exceptionHandling().accessDeniedPage("/401");
        http.logout().logoutSuccessUrl("/");
    }

}
