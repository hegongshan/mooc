package com.hegongshan.mooc.dao;

import javax.annotation.Resource;

import com.hegongshan.mooc.entity.Admin;

@Resource
public interface IAdminDAO {
	
	Integer updateAdmin(Admin admin) throws Exception;
	
	Admin getAdminById(Integer id) throws Exception;
	
	Admin getAdminByUsernameAndPassword(Admin admin) throws Exception;
	
}
