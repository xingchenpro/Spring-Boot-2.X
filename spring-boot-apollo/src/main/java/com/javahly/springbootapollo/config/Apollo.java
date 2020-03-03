package com.javahly.springbootapollo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/3
 * @QQ :1136513099
 * @desc : 配置
 */
@Component
public class Apollo {

    @Value("${key:v}")
    public String key;

}
