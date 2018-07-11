package com.hegongshan.mooc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;

import com.hegongshan.mooc.entity.Course;

@Resource
public interface ICourseDAO {
	Integer saveCourse(Course course) throws Exception;

	Integer updateCourse(Course course) throws Exception;

	Integer removeCourseById(Integer id) throws Exception;
		
	Course getCourseById(Integer id) throws Exception;
	
	List<Course> getCourseByCourseNameLike(String courseName) throws Exception;
	
	List<Course> listCourseByStudentId(Integer studentId) throws Exception;
	
	List<Course> listCourseByTeacherId(Integer teacherId) throws Exception;
	
	List<Course> listCourseByCourseTypeIdAndPage(@Param("courseTypeId") Integer courseTypeId,@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize) throws Exception;
	
	List<Course> listCourseByPage(@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize) throws Exception;
		
	Integer countAllCourse() throws Exception;
	
	Integer countCourseByCourseTypeId(Integer courseTypeId) throws Exception;
	
	Integer countCourseByStudentId(Integer studentId) throws Exception;
	
	Integer countCourseByTeacherId(Integer teacherId) throws Exception;
}
