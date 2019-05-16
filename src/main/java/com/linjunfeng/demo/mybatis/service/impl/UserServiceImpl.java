package com.linjunfeng.demo.mybatis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.linjunfeng.demo.mybatis.dao.UserMapper;
import com.linjunfeng.demo.mybatis.model.User;
import com.linjunfeng.demo.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject getUser(JSONObject object) {
        String userId = object.getString("userId");
        User user = userMapper.selectByPrimaryKey(Long.parseLong(userId));

        User u = new User();
        u.setName("test2");
        u.setAge(30);
        u.setTmCreate(System.currentTimeMillis());
        userMapper.insertSelective(u);

        user = userMapper.selectByPrimaryKey(Long.parseLong(userId));
        JSONObject obj = new JSONObject();
        obj.put("obj", user);
        return obj;
    }
}
