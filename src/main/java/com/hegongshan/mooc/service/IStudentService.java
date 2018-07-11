package com.hegongshan.mooc.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hegongshan.mooc.entity.Student;

public interface IStudentService {
	Integer doRegister(Student student,String contextPath);
	
	Student doConfirm(String username);
	
	Integer doUpdate(Student student);
	
	Integer doRemove(Integer id);
		
	Student doLogin(Student student);
	
	Student findById(Integer id);
	
	Student getStudentByUsername(String username);
	
	Integer saveCourseToMyCourse(Map<String,Object> ids);
	
	Integer removeCourseFromMyCourse(Integer studentId, Integer courseId);
	
	List<Student> listStudentByCondition(Map<String,Object> conditions);
	
	boolean isMyCourse(@Param("studentId") Integer studentId,@Param("courseId") Integer courseId);
}
