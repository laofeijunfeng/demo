package com.linjunfeng.demo.thymeleaf.controller;

import com.linjunfeng.demo.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("message", "i am a thymeleaf test page");
        map.addAttribute("username", "laofeijunfeng");
        map.addAttribute("flag", "yes");
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("user1");
        user1.setAge(25);
        users.add(user1);
        User user2 = new User();
        user2.setName("user2");
        user2.setAge(26);
        users.add(user2);
        map.addAttribute("users", users);
        return "index";
    }

    @RequestMapping("/layout/index")
    public String layoutIndex(ModelMap map) {
        return "layout/index";
    }
}
