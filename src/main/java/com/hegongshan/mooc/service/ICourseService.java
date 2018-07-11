package com.hegongshan.mooc.service;

import java.util.List;

import com.hegongshan.mooc.entity.Course;

public interface ICourseService {
	Integer saveCourse(Course course);

	Integer updateCourse(Course course);

	Integer removeCourseById(Integer id);

	Course getCourseById(Integer id);
	
	List<Course> getCourseByCourseNameLike(String courseName);
		
	List<Course> listCourseByStudentId(Integer studentId);
	
	List<Course> listCourseByTeacherId(Integer teacherId);
	
	List<Course> listCourseByCourseTypeIdAndPage(Integer courseTypeId,Integer startPage,Integer pageSize);
	
	List<Course> listCourseByPage(Integer startPage,Integer pageSize);
		
	Integer countAllCourse();
	
	Integer countCourseByCourseTypeId(Integer courseTypeId);
	
	Integer countCourseByStudentId(Integer studentId);
	
	Integer countCourseByTeacherId(Integer teacherId);
}
