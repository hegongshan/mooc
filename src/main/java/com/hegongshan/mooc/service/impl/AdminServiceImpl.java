package com.hegongshan.mooc.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hegongshan.mooc.dao.IAdminDAO;
import com.hegongshan.mooc.dao.ICourseDAO;
import com.hegongshan.mooc.dao.ICourseTypeDAO;
import com.hegongshan.mooc.dao.IStudentDAO;
import com.hegongshan.mooc.dao.ITeacherDAO;
import com.hegongshan.mooc.entity.Admin;
import com.hegongshan.mooc.entity.CourseType;
import com.hegongshan.mooc.entity.Student;
import com.hegongshan.mooc.entity.Teacher;
import com.hegongshan.mooc.service.IAdminService;

@Service("adminService")
public class AdminServiceImpl implements IAdminService{
	private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Resource
	private IAdminDAO adminDAO;
	@Resource
	private IStudentDAO studentDAO;
	@Resource
	private ITeacherDAO teacherDAO;
	@Resource
	private ICourseTypeDAO courseTypeDAO;
	@Resource
	private ICourseDAO courseDAO;

	@Override
	public void removeFromBlacklist(String role, Integer id) {
		if("student".equalsIgnoreCase(role)) {
			Student student = new Student();
			student.setStudentId(id);
			student.setStatus(1);
			try {
				studentDAO.doUpdate(student);
			} catch (Exception e) {
				logger.error("更新t_student表失败",e);
			}
		} else if("teacher".equalsIgnoreCase(role)) {
			Teacher teacher = new Teacher();
			teacher.setTeacherId(id);
			teacher.setStatus(1);
			try {
				teacherDAO.updateTeacher(teacher);
			} catch (Exception e) {
				logger.error("更新t_teacher表失败",e);
			}
		}
	}

	@Override
	public Integer saveCourseType(CourseType courseType) {
		try {
			return courseTypeDAO.saveCourseType(courseType);
		} catch (Exception e) {
			logger.error("插入课程类型失败！",e);
			return -1;
		}
	}

	@Override
	public Integer updateCourseType(CourseType courseType) {
		try {
			return courseTypeDAO.updateCourseType(courseType);
		} catch (Exception e) {
			logger.error("更改课程类型失败！",e);
			return -1;
		}
	}

	@Override
	public Integer updateAdmin(Admin admin) {
		try {
			return adminDAO.updateAdmin(admin);
		} catch (Exception e) {
			logger.error("更新t_admin失败", e);
			return -1;
		}
	}

	@Override
	public Admin getAdminById(Integer id) {
		try {
			return adminDAO.getAdminById(id);
		} catch (Exception e) {
			logger.error("从t_admin表中获取数据失败",e);
			return null;
		}
	}

	@Override
	public Admin getAdminByUsernameAndPassword(Admin admin) {
		try {
			return adminDAO.getAdminByUsernameAndPassword(admin);
		} catch (Exception e) {
			logger.error("从t_admin表中获取数据失败",e);
			return null;
		}
	}

	@Override
	public void saveToBlacklist(String role, Integer id) {
		if("student".equalsIgnoreCase(role)) {
			Student student = new Student();
			student.setStudentId(id);
			student.setStatus(2);
			try {
				studentDAO.doUpdate(student);
			} catch (Exception e) {
				logger.error("更新t_student表失败",e);
			}
		} else if("teacher".equalsIgnoreCase(role)) {
			Teacher teacher = new Teacher();
			teacher.setTeacherId(id);
			teacher.setStatus(2);
			try {
				teacherDAO.updateTeacher(teacher);
			} catch (Exception e) {
				logger.error("更新t_teacher表失败",e);
			}
		}	
	}
}
