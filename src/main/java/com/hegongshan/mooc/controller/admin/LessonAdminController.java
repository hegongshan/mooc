package com.hegongshan.mooc.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.service.ILessonService;

@Controller
@RequestMapping("/admin/lesson")
public class LessonAdminController {
	@Resource
	private ILessonService lessonService;

	@RequestMapping("/remove/{lessonId}")
	public ModelAndView removeLesson(@PathVariable Integer lessonId,ModelAndView mav) {
		lessonService.removeLessonById(lessonId);
		mav.setViewName("redirect:/admin/course/list");
		return mav;
	}
	
}
