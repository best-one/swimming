package org.swimming.labs.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test1Controller {
    
    @RequestMapping(value = "/test")
    public String test1(Model model){

        model.addAttribute("haha","我的天");
        return "index";
    }
}
