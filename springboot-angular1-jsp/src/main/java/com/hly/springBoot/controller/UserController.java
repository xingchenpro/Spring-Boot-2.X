package com.hly.springBoot.controller;


import com.hly.springBoot.service.UserService;
import com.hly.springBoot.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.catalina.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hly
 * @blog:https://blog.csdn.net/Sirius_hly
 * @github:https://github.com/SiriusHly
 * @date 2018年9月13日 下午6:22:04
 */
@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("/login")
   public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JSONObject result  = new JSONObject();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = (User) userService.selectUserById(username);
		if(user!=null)
			result.put("result",1);
		else
			result.put("result",0);
		ResponseUtil.write(response,result);
		return null;
	}

	/**
	 * 注册
	 * @param
	 * @return
	 */
	//@RequestMapping("/register")
	public ModelAndView register(@RequestParam(value="user_id",required=false)String userId,@RequestParam(value="user_password",required=false)String userPassword){
		ModelAndView mView = new ModelAndView();

			return null;
	}
	


}
