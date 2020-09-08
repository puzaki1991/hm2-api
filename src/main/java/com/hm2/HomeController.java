package com.hm2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hm2.common.utils.UserLoginUtils;

@Controller
public class HomeController {

    @RequestMapping(value = "/**/{[path:[^\\.]*}")
    public String redirectUi() {
        return "forward:index.html";
    }
    
    @GetMapping("/login")
    public  ModelAndView login() {
        return new ModelAndView("login.html");
    }

    @GetMapping("/")
    public ModelAndView home() {
    	ModelAndView mav = new ModelAndView("home.html");
    	mav.addObject("username", UserLoginUtils.getCurrentUsername());
    	return mav;
    }
    
    
}
