package com.hly.springBootRedisSession.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;
/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/11/25
 */
@Controller
public class SessionController {

    @ResponseBody
    @RequestMapping("/uuid")
    public String sessionTest(HttpSession session){
        UUID uuid = (UUID)session.getAttribute("uuid");
        if(uuid == null){
            uuid = UUID.randomUUID();
        }
        session.setAttribute("uuid",uuid);
        return session.getId();
    }
}
