package com.hegongshan.mooc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.hegongshan.mooc.dao.ICourseDAO;
import com.hegongshan.mooc.dao.ITeacherDAO;
import com.hegongshan.mooc.entity.Teacher;
import com.hegongshan.mooc.service.ITeacherService;
import com.hegongshan.mooc.util.CryptographyUtils;
import com.hegongshan.mooc.util.MailUtils;

@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService{
	private static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
	@Resource
	private ITeacherDAO teacherDAO;
	@Resource
	private ICourseDAO courseDAO;	
	@Resource
	private JavaMailSenderImpl mailSender;

	@Override
	public Integer saveTeacher(Teacher teacher,String contextPath) {
		Long currentTime = System.currentTimeMillis();
		String activeCode =  CryptographyUtils.encryptByMd5("username", currentTime.toString());
		String originalPassword = teacher.getPassword();
		teacher.setPassword(CryptographyUtils.encryptByMd5(originalPassword, teacher.getUsername()));
		teacher.setActiveCode(activeCode);
		teacher.setEmail(teacher.getUsername());
		try {
			int result = teacherDAO.saveTeacher(teacher);
			String url = contextPath+"/teacher/confirm?username=" + 
					teacher.getUsername()+"&activeCode=" + activeCode;
			MailUtils.sendHtmlMailConfirmRegister(mailSender, teacher.getEmail(), teacher.getRealname(), teacher.getUsername(), originalPassword, url);
			return result;			
		} catch (Exception e) {
			logger.error("插入教师信息到t_teacher数据表中失败", e);
			return -1;
		}
	}

	@Override
	public Integer updateTeacher(Teacher teacher) {
		
		try {
			return teacherDAO.updateTeacher(teacher);
		} catch (Exception e) {
			logger.error("更新t_teacher数据表失败", e);
			return -1;
		}
	}

	@Override
	public Integer removeTeacherById(Integer id) {
		try {
			return teacherDAO.removeTeacherById(id);
		} catch (Exception e) {
			logger.error("从t_teacher数据表中删除教师信息失败", e);
			return -1;
		}
	}

	@Override
	public Teacher getTeacherById(Integer id) {
		try {
			return teacherDAO.getTeacherById(id);
		} catch (Exception e) {
			logger.error("从t_teacher数据表中获取教师信息失败", e);
			return null;
		}
	}

	@Override
	public Teacher getTeacherByUsernameAndPassword(Teacher teacher) {
		String originalPassword = teacher.getPassword();
		teacher.setPassword(CryptographyUtils.encryptByMd5(originalPassword, teacher.getUsername()));
		try {
			return teacherDAO.getTeacherByUsernameAndPassword(teacher);
		} catch (Exception e) {
			logger.error("从t_teacher数据表中获取教师信息失败", e);
			return null;
		}
	}

	@Override
	public List<Teacher> listTeacherByCondition(Map<String,Object> conditions) {
		try {
			return teacherDAO.listTeacherByCondition(conditions);
		} catch (Exception e) {
			logger.error("从t_teacher数据表中获取教师信息失败", e);
			return null;
		}
	}

	@Override
	public Teacher getTeacherByUsername(String username) {
		try {
			return teacherDAO.getTeacherByUsername(username);
		} catch (Exception e) {
			logger.error("从t_teacher数据表中获取教师信息失败", e);
			return null;
		}
	}

	@Override
	public List<Teacher> listTeacherByNameLike(String name) {
		try {
			return teacherDAO.listTeacherByNameLike(name);
		} catch (Exception e) {
			logger.error("从t_teacher数据表中获取教师信息失败", e);
			return null;
		}
	}
}
