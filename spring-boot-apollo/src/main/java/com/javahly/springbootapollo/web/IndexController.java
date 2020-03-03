package com.javahly.springbootapollo.web;

import com.javahly.springbootapollo.config.Apollo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/3
 * @QQ :1136513099
 * @desc :
 */
@RestController
public class IndexController {

    @Autowired
    Apollo apollo;

    @RequestMapping("/index")
    public String index() {
        return apollo.key;
    }
}
