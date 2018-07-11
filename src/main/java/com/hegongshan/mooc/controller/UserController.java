package com.hegongshan.mooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/login")
	public ModelAndView toLoginPage(ModelAndView mv) {
		mv.setViewName("/common/login");
		return mv;
	}
	
	@RequestMapping("/resetpwd")
	public ModelAndView toForgetPasswordPage(ModelAndView mv) {
		mv.setViewName("/common/resetpwd");
		return mv;
	}
}
