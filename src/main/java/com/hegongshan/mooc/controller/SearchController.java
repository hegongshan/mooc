package com.hegongshan.mooc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.entity.Teacher;
import com.hegongshan.mooc.service.ICourseService;
import com.hegongshan.mooc.service.ITeacherService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Resource
	private ICourseService courseService;
	@Resource
	private ITeacherService teacherService;
	
	@RequestMapping("/course")
	public String courseSearch(@RequestParam("keyword") String keyword,
							   HttpServletRequest request) {

		List<Course> courseList = courseService.getCourseByCourseNameLike(keyword);
		request.setAttribute("keyword",keyword);
		request.setAttribute("courseList",courseList);
		return "/common/search_course";
	}	
	
	@RequestMapping("/teacher")
	public String teacherSearch(@RequestParam("keyword") String keyword,
							   HttpServletRequest request) {

		List<Teacher> teacherList = teacherService.listTeacherByNameLike(keyword);
		request.setAttribute("keyword",keyword);
		request.setAttribute("teacherList",teacherList);
		return "/common/search_teacher";
	}
	
	@RequestMapping("/teacher/course/list")
	public ModelAndView teacherCourseList (@RequestParam("teacherId") Integer teacherId,ModelAndView mav) {

		Teacher teacher = teacherService.getTeacherById(teacherId);
		mav.addObject("teacher",teacher);
		mav.setViewName("/common/teacher_course_list");
		return mav;
	}
}
