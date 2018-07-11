package com.hegongshan.mooc.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;

import com.hegongshan.mooc.entity.Student;

@Resource
public interface IStudentDAO {

	Integer doInsert(Student student) throws Exception;
	
	Integer doUpdate(Student student) throws Exception;
	
	public Integer doRemove(Integer id) throws Exception;
	
	Student findById(Integer id) throws Exception;
	
	Student getStudentByUsername(String username) throws Exception;
	
	Student findByUsernameAndPassword(Student student) throws Exception;
	
	List<Student> listStudentByCondition(Map<String,Object> conditions) throws Exception;
	
	Integer saveCourseToMyCourse(Map<String,Object> ids) throws Exception;
	
	Integer removeCourseFromMyCourse(@Param("studentId") Integer studentId,@Param("courseId") Integer courseId) throws Exception;
	
	Integer countMyCourseByStudentIdAndCourseId(@Param("studentId") Integer studentId,@Param("courseId") Integer courseId) throws Exception;
	

}
