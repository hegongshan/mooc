package com.hegongshan.mooc.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hegongshan.mooc.entity.CourseType;
import com.hegongshan.mooc.service.ICourseService;
import com.hegongshan.mooc.service.ICourseTypeService;
import com.hegongshan.mooc.util.StringUtils;

@Controller
@RequestMapping("/admin/courseType")
public class CourseTypeAdminController {
	
	@Resource
	private ICourseService courseService;
	
	@Resource
	private ICourseTypeService courseTypeService;
	
	@RequestMapping("/list")
	public String listAllCourseType(Model model) {
		List<CourseType> courseTypeList = courseTypeService.listAllCourseType();
		model.addAttribute("courseTypeList",courseTypeList);
		model.addAttribute("mainPage", "/WEB-INF/jsp/admin/courseType/type_list.jsp");
		return "/admin/index";
	}
	
	@RequestMapping("/save")
	public String saveCourseType(CourseType courseType) {
		courseTypeService.saveCourseType(courseType);
		return "redirect:/admin/courseType/list";
	}

	@RequestMapping("/update")
	public String updateCourseType(CourseType courseType) {
		courseTypeService.updateCourseType(courseType);
		return "redirect:/admin/courseType/list";
	}
	
	@RequestMapping("/remove/{courseTypeId}")
	public String removeCourseType(@PathVariable Integer courseTypeId,HttpServletRequest request) {
		int totalRecords = courseService.countCourseByCourseTypeId(courseTypeId);
		if(totalRecords <= 0) {
			courseTypeService.removeCourseTypeById(courseTypeId);
		} else {
			request.setAttribute("errorMsg","该类别下有课程，不能删除");
		}
		return "forward:/admin/courseType/list";
	}
	
	@RequestMapping("/remove/batch")
	public String removeCourseTypeBatch(@RequestParam("typeIds") String typeIds,HttpServletRequest request) {
		Integer[] courseTypeIds = StringUtils.toIntegerArrayBySplit(typeIds, ",");
		int totalRecords = 0;
		for(Integer courseTypeId : courseTypeIds) {
			int record = courseService.countCourseByCourseTypeId(courseTypeId);
			totalRecords += record;
		}
		if(totalRecords > 0) {
			request.setAttribute("errorMsg","某些类别下有课程，不能删除");
		} else {
			courseTypeService.removeCourseTypeBatch(courseTypeIds);
		}		
		return "forward:/admin/courseType/list";
	}
}
