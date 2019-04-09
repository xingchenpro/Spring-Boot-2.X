package com.hly.springbootmybatismultidatasources.entity;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/4/9
 */
public class Article {

    private int a_id;
    private String a_name;

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    @Override
    public String toString() {
        return "Article{" +
                "a_id=" + a_id +
                ", a_name='" + a_name + '\'' +
                '}';
    }
}
