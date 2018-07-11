package com.hegongshan.mooc.service;

import com.hegongshan.mooc.entity.Admin;
import com.hegongshan.mooc.entity.CourseType;


public interface IAdminService {
	//Integer saveAdmin(Admin admin);
	
	Integer updateAdmin(Admin admin);
	
	//Integer removeAdminById(Integer id);
	
	Admin getAdminById(Integer id);
	
	Admin getAdminByUsernameAndPassword(Admin admin);
		
	void saveToBlacklist(String role,Integer id);
	
	void removeFromBlacklist(String role,Integer id);
		
	Integer saveCourseType(CourseType courseType);
	
	Integer updateCourseType(CourseType courseType);
}
