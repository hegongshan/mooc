package com.hegongshan.mooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public ModelAndView toIndexPage(ModelAndView mav) {
		mav.setViewName("/common/index");
		return mav;
	}

}
