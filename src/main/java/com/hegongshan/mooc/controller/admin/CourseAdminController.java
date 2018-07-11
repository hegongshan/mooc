package com.hegongshan.mooc.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.service.ICourseService;
import com.hegongshan.mooc.service.ICourseTypeService;
import com.hegongshan.mooc.util.PageUtils;

@Controller
@RequestMapping("/admin/course")
public class CourseAdminController {

	@Resource
	private ICourseService courseService;
	@Resource
	private ICourseTypeService courseTypeService;
	
	@RequestMapping("/list")
	public ModelAndView listAllCourse(@RequestParam(value="page",required=false) Integer page,ModelAndView mav) {
		PageUtils<Course> pager = new PageUtils<Course>();
		pager.setCurrentPage(page);
		pager.setPageSize(10);
		
		int totalRows = courseService.countAllCourse();
		pager.setTotalRows(totalRows);

		List<Course> courseList = courseService.listCourseByPage(pager.getStartPage(),pager.getPageSize());
		pager.setList(courseList);
		mav.addObject("pager",pager);
		mav.addObject("mainPage", "/WEB-INF/jsp/admin/course/course_list.jsp");
		mav.setViewName("/admin/index");
		return mav;
	}
	
}
