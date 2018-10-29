package com.hly.springbootSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/10/29
 */

@Configuration
@EnableWebSecurity//开启WebSecurity功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
        //inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()),相当于登陆时用BCrypt加密方式对用户密码进行处理，和下面比对一致，允许登录
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("hly").password(passwordEncoder().encode("123")).roles("USER","ADMIN").and()
                .withUser("sirius").password(passwordEncoder().encode("123")).roles("USER");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //不需要验证的资源
                .antMatchers("/css/**","/index").permitAll()
                //需要验证，角色为Role
                .antMatchers("/article/**").hasAnyRole("ADMIN","USER")
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
