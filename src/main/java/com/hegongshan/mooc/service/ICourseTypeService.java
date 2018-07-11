package com.hegongshan.mooc.service;

import java.util.List;

import com.hegongshan.mooc.entity.CourseType;

public interface ICourseTypeService {
	Integer saveCourseType(CourseType courseType);

	Integer updateCourseType(CourseType courseType);

	Integer removeCourseTypeById(Integer id);

	Integer removeCourseTypeBatch(Integer[] ids);
	
	CourseType getCourseTypeById(Integer id);
	
	List<CourseType> listAllCourseType();
}
