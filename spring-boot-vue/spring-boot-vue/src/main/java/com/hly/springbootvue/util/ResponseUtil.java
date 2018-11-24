package com.hly.springbootvue.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/11/24
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response,Object object) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(object.toString());
        out.flush();
        out.close();
    }
}
