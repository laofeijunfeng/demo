package com.linjunfeng.demo.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("message", "i am a thymeleaf test page");
        return "index";
    }

    @RequestMapping("/layout/index")
    public String layoutIndex(ModelMap map) {
        return "layout/index";
    }
}
