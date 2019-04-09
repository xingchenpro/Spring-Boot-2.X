package com.hly.springbootmybatismultidatasources.entity;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/4/9
 */
public class Video {

    private int v_id;
    private String v_name;
    private int a_id;

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    @Override
    public String toString() {
        return "Video{" +
                "v_id=" + v_id +
                ", v_name='" + v_name + '\'' +
                ", a_id=" + a_id +
                '}';
    }
}
