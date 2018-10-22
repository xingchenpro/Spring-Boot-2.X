package com.hly.springbootDocker.controller;

import com.hly.springbootDocker.domain.User;
import com.hly.springbootDocker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author hly
 * @blog:https: //blog.csdn.net/Sirius_hly
 * @github: https://github.com/huangliangyun
 * @date 2018年9月13日 下午6:22:04
 */
@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * 登录
	 * @param userId
	 * @param userPassword
	 * @return
	 */
	@RequestMapping("/login")
   public ModelAndView login(@RequestParam(value="user_id",required=false) String userId,@RequestParam(value="user_password",required=false)String userPassword){
	   System.out.println(userId);
	   ModelAndView mView = new ModelAndView();
	   User user = userService.selectUserById(userId);
	   if(user!=null&&userId.equals(user.getUserId())&&userPassword.equals(user.getUserPassword())){
		   mView.setViewName("/index");
		   mView.addObject("message", "欢迎回来!");
		   return mView;
	   }
	   mView.addObject("error", "用户名或密码错误");
	   mView.setViewName("/login");
	   return mView;
	   
   }
	
	/**
	 * 注册
	 * @param
	 * @return
	 */
	@RequestMapping("/register")
	public ModelAndView register(@RequestParam(value="user_id",required=false)String userId,@RequestParam(value="user_password",required=false)String userPassword){
		
		User user = new User(userId, userPassword);
		ModelAndView mView = new ModelAndView();
		
			System.out.println(user.getUserId());
			try {
				userService.insertUser(user);
				mView.setViewName("/login");
				return mView;
			} catch (Exception e) {
				mView.setViewName("/register");
				return mView;
			}
	}
	
	/**
	 * index
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView index(){
	
		ModelAndView mView = new ModelAndView();
		mView.setViewName("/login");
		return mView;
	}

}
