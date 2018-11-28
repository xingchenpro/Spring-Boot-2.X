package com.hly.springBootSecurityMybatis.config;
import com.hly.springBootSecurityMybatis.service.UserService;
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

   @Bean
   UserDetailsService userService(){
       return  new UserService();
   }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //从数据库中获取用户认证信息
        auth.userDetailsService(userService());
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
