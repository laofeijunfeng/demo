package com.linjunfeng.demo.mybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.linjunfeng.demo.mybatis.util.HttpUtil;
import com.linjunfeng.demo.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("get")
    @ResponseBody
    public void getUser(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");

        JSONObject object = new JSONObject();
        object.put("userId", userId);
        JSONObject res = userService.getUser(object);

        HttpUtil.printJson(response, res);
    }
}
