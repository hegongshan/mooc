package com.hegongshan.mooc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.hegongshan.mooc.dao.ICourseDAO;
import com.hegongshan.mooc.dao.IStudentDAO;
import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.entity.Student;
import com.hegongshan.mooc.service.IStudentService;
import com.hegongshan.mooc.util.CryptographyUtils;
import com.hegongshan.mooc.util.MailUtils;

@Service("studentService")
public class StudentServiceImpl implements IStudentService {
	private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Resource
	private IStudentDAO studentDAO;
	@Resource
	private ICourseDAO courseDAO;
	@Resource
	private JavaMailSenderImpl mailSender;

	@Override
	public Integer doRegister(Student student,String contextPath) {	
		Long currentTime = System.currentTimeMillis();

		String activeCode =  CryptographyUtils.encryptByMd5("username", currentTime.toString());
		student.setActiveCode(activeCode);
		
		String originalPassword = student.getPassword();
		String realPassword = CryptographyUtils.encryptByMd5(originalPassword, student.getUsername());
		student.setPassword(realPassword);
		student.setEmail(student.getUsername());
		try {
			int result = studentDAO.doInsert(student);
			String url = contextPath+"/student/confirm?username=" + 
					 student.getUsername()+"&activeCode=" + activeCode;	
			MailUtils.sendHtmlMailConfirmRegister(mailSender, student.getUsername(), student.getNickname(), student.getUsername(), originalPassword, url);
			return result;
		} catch (Exception e) {
			logger.error("插入数据失败！",e);
			return -1;
		}
	}

	@Override
	public Student doConfirm(String username) {
		try {
			return studentDAO.getStudentByUsername(username);
		} catch (Exception e) {
			logger.error("查询数据失败！",e);
			return null;
		}
	}
	
	@Override
	public Integer doUpdate(Student student) {
		try {
			return studentDAO.doUpdate(student);
		} catch (Exception e) {
			logger.error("更新数据失败！",e);
			return -1;
		}
	}

	@Override
	public Integer doRemove(Integer id) {
		try {
			return studentDAO.doRemove(id);
		} catch (Exception e) {
			logger.error("删除数据失败！",e);
			return -1;
		}
	}

	@Override
	public Student doLogin(Student student) {
		String originalPassword = student.getPassword();
		String realPassword = CryptographyUtils.encryptByMd5(originalPassword, student.getUsername());
		student.setPassword(realPassword);
		try {
			return studentDAO.findByUsernameAndPassword(student);
		} catch (Exception e) {
			logger.error("查询数据失败！",e);
			return null;
		}
	}

	@Override
	public Student findById(Integer id) {
		
		try {
			return studentDAO.findById(id);
		} catch (Exception e) {
			logger.error("查询数据失败！",e);
			return null;
		}
	}

	@Override
	public Integer saveCourseToMyCourse(Map<String, Object> ids) {
		
		try {
			Course course = courseDAO.getCourseById((Integer)ids.get("courseId"));
			int originalStudetnNumber = course.getStudentNumber();
			course.setStudentNumber(originalStudetnNumber + 1);
			courseDAO.updateCourse(course);
			return studentDAO.saveCourseToMyCourse(ids);
		} catch (Exception e) {
			logger.error("插入数据到t_r_student_course表中失败",e);
			return -1;
		}
	}

	@Override
	public Student getStudentByUsername(String username) {
		try {
			return studentDAO.getStudentByUsername(username);
		} catch (Exception e) {
			logger.error("从t_student表中查询数据失败",e);
			return null;
		}
	}

	@Override
	public List<Student> listStudentByCondition(Map<String, Object> conditions) {
		try {
			return studentDAO.listStudentByCondition(conditions);
		} catch (Exception e) {
			logger.error("从t_student表中查询数据失败",e);
			return null;
		}
	}

	@Override
	public Integer removeCourseFromMyCourse(Integer studentId, Integer courseId) {
		try {
			return studentDAO.removeCourseFromMyCourse(studentId, courseId);
		} catch (Exception e) {
			logger.error("从t_student表中删除数据失败",e);
			return -1;
		}
	}
	/**
	 * 待改进
	 */
	@Override
	public boolean isMyCourse(Integer studentId, Integer courseId) {
		try {
			int result = studentDAO.countMyCourseByStudentIdAndCourseId(studentId, courseId);
			return result > 0 ? true : false;
		} catch (Exception e) {
			logger.error("从t_r_student_course表中查询数据失败",e);
			return false;//todo
		}
	}
}
