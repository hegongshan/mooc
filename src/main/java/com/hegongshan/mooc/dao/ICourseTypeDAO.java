package com.hegongshan.mooc.dao;

import java.util.List;

import javax.annotation.Resource;

import com.hegongshan.mooc.entity.CourseType;

@Resource
public interface ICourseTypeDAO {
	Integer saveCourseType(CourseType courseType) throws Exception;

	Integer updateCourseType(CourseType courseType) throws Exception;

	Integer removeCourseTypeById(Integer id) throws Exception;
	
	Integer removeCourseTypeBatch(Integer[] ids) throws Exception;

	CourseType getCourseTypeById(Integer id) throws Exception;
	
	List<CourseType> listAllCourseType() throws Exception;
}
