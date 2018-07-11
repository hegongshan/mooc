package com.hegongshan.mooc.service;

import java.util.List;
import java.util.Map;

import com.hegongshan.mooc.entity.Teacher;

public interface ITeacherService {
	Integer saveTeacher(Teacher teacher,String contextPath);
	
	Integer updateTeacher(Teacher teacher);
	
	Integer removeTeacherById(Integer id);
		
	Teacher getTeacherById(Integer id);
	
	Teacher getTeacherByUsername(String username);
	
	Teacher getTeacherByUsernameAndPassword(Teacher teacher);
	
	List<Teacher> listTeacherByCondition(Map<String,Object> conditions);
	
	List<Teacher> listTeacherByNameLike(String name);
	
}