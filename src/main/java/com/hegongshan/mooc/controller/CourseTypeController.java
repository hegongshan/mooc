package com.hegongshan.mooc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.entity.CourseType;
import com.hegongshan.mooc.service.ICourseService;
import com.hegongshan.mooc.service.ICourseTypeService;
import com.hegongshan.mooc.util.PageUtils;

@Controller
@RequestMapping("/courseType")
public class CourseTypeController {
	
	@Resource
	private ICourseService courseService;
	
	@Resource
	private ICourseTypeService courseTypeService;
	
	@RequestMapping("/{courseTypeId}")
	public String listCourseByType(@PathVariable Integer courseTypeId,
								   @RequestParam(value="page",required=false) Integer page,
								   HttpServletRequest request) {
		List<CourseType> courseTypeList = courseTypeService.listAllCourseType();
		request.setAttribute("courseTypeList", courseTypeList);
		PageUtils<Course> pager = new PageUtils<Course>();
		pager.setCurrentPage(page);
		pager.setTotalRows(courseService.countCourseByCourseTypeId(courseTypeId));
		pager.setPageSize(4);	
		pager.setList(courseService.listCourseByCourseTypeIdAndPage(courseTypeId,pager.getStartPage(),pager.getPageSize()));
		CourseType activeCourseType = courseTypeService.getCourseTypeById(courseTypeId);
		request.setAttribute("activeCourseType", activeCourseType);
		request.setAttribute("pager", pager);
		return "/common/course_list_by_type";
	}
}
