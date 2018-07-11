package com.hegongshan.mooc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hegongshan.mooc.entity.Carousel;
import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.entity.CourseType;
import com.hegongshan.mooc.service.ICarouselService;
import com.hegongshan.mooc.service.ICourseService;
import com.hegongshan.mooc.service.ICourseTypeService;

@Component
public class InitSystemService implements ServletContextListener, ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		InitSystemService.applicationContext = applicationContext;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();

		ICourseTypeService courseTypeService = (ICourseTypeService) applicationContext.getBean("courseTypeService");
		ICourseService courseService = (ICourseService) applicationContext.getBean("courseService");
		ICarouselService carouselService = (ICarouselService) applicationContext.getBean("carouselService");

		List<CourseType> courseTypeList = courseTypeService.listAllCourseType();
		application.setAttribute("courseTypeList", courseTypeList);

		List<Carousel> carouselList = carouselService.listCarousel();
		application.setAttribute("carouselList", carouselList);

		List<Course> courseList = new ArrayList<Course>(24);
		for (int i = 0; i < courseTypeList.size(); i++) {
			courseList.addAll(
					courseService.listCourseByCourseTypeIdAndPage(courseTypeList.get(i).getCourseTypeId(), 0, 4));
		}
		application.setAttribute("courseList", courseList);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
