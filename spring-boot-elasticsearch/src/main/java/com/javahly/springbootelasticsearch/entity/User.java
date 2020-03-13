package com.javahly.springbootelasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/12
 * @QQ :1136513099
 * @desc :
 */
@Data
@Document(indexName = "hly", type = "user")
public class User {

    @Id
    private String id;
    private String name;
    private String age;

}
