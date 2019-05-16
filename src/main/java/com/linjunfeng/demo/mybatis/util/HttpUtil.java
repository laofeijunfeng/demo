package com.linjunfeng.demo.mybatis.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtil {
    public static void printJson(HttpServletResponse response, JSONObject resObject) {
        String strResJson = "";
        if (resObject != null)
            strResJson = JSON.toJSONString(resObject);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");
        try {
            response.getWriter().print(strResJson);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
