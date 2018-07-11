package com.hegongshan.mooc.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hegongshan.mooc.entity.Teacher;

@Resource
public interface ITeacherDAO {
	
	Integer saveTeacher(Teacher teacher) throws Exception;
	
	Integer updateTeacher(Teacher teacher) throws Exception;
	
	Integer removeTeacherById(Integer id) throws Exception;
	
	Teacher getTeacherById(Integer id) throws Exception;
	
	Teacher getTeacherByUsername(String username) throws Exception;
	
	Teacher getTeacherByUsernameAndPassword(Teacher teacher) throws Exception;
	
	List<Teacher> listTeacherByCondition(Map<String,Object> conditions) throws Exception;
	
	List<Teacher> listTeacherByNameLike(String name) throws Exception;
	
}
