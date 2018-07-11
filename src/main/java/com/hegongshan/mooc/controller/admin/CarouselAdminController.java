package com.hegongshan.mooc.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.entity.Carousel;
import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.service.ICarouselService;

@Controller
@RequestMapping("/admin/carousel")
public class CarouselAdminController {
	
	@Resource
	private ICarouselService carouselService;
	
	@RequestMapping("/list")
	public ModelAndView listCarousel(ModelAndView mav) {
		List<Carousel> carouselList = carouselService.listCarousel();
		mav.addObject("mainPage", "/WEB-INF/jsp/admin/carousel/carousel_list.jsp");
		mav.addObject("carouselList", carouselList);
		mav.setViewName("/admin/index");
		return mav;
	}
	
	@RequestMapping("/save")
	public ModelAndView saveCarousel(@RequestParam("priority") Integer priority,@RequestParam("courseId") Integer courseId,ModelAndView mav) {
		Carousel carousel = new Carousel();
		Course course = new Course();
		
		course.setCourseId(courseId);
		carousel.setCourse(course);
		carousel.setPriority(priority);
		
		int result = carouselService.saveCarousel(carousel);
		if(result == 0) {
			mav.addObject("errorMsg", "不存在该优先级");
		} else if(result < 0) {
			mav.addObject("errorMsg", "设置轮播失败，请重新设置");
		}
		mav.setViewName("forward:/admin/carousel/list");
		return mav;
	}
	
	@RequestMapping("/remove/{priority}") 
	public ModelAndView remove(@PathVariable Integer priority,ModelAndView mav) {
		carouselService.removeCarouselByPriority(priority);
		mav.setViewName("redirect:/admin/carousel/list");
		return mav;
	}
}
