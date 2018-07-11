package com.hegongshan.mooc.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.entity.Carousel;
import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.entity.CourseType;
import com.hegongshan.mooc.service.ICarouselService;
import com.hegongshan.mooc.service.ICourseService;
import com.hegongshan.mooc.service.ICourseTypeService;

@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {
	
	@Resource
	private ICourseTypeService courseTypeService;
	@Resource
	private ICourseService courseService;
	@Resource
	private ICarouselService carouselService;
	
	@RequestMapping("/refresh")
	public ModelAndView refreshSystem(ModelAndView mav) {
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext application = webApplicationContext.getServletContext();
		
		List<CourseType> courseTypeList = courseTypeService.listAllCourseType();
		application.setAttribute("courseTypeList", courseTypeList);
		
		List<Carousel> carouselList = carouselService.listCarousel();
		application.setAttribute("carouselList", carouselList);
		
		List<Course> courseList = new ArrayList<Course>(24);	
		for (int i = 0; i < courseTypeList.size(); i++) {
			courseList.addAll(courseService.listCourseByCourseTypeIdAndPage(courseTypeList.get(i).getCourseTypeId(),0,4));
		}	
		application.setAttribute("courseList", courseList);
		
		mav.setViewName("/admin/index");
		
		return mav;
	}
}
