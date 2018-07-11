package com.hegongshan.mooc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hegongshan.mooc.dao.ICourseDAO;
import com.hegongshan.mooc.dao.IStudentDAO;
import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.service.ICourseService;

@Service("courseService")
public class CourseServiceImpl implements ICourseService {
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Resource
	private ICourseDAO courseDAO;	
	@Resource
	private IStudentDAO studentDAO;
	@Override
	public Integer saveCourse(Course course) {
		try {
			return courseDAO.saveCourse(course);
		} catch (Exception e) {
			logger.error("插入数据到t_course数据表失败",e);
			return -1;
		}
	}

	@Override
	public Integer updateCourse(Course course) {
		try {
			return courseDAO.updateCourse(course);
		} catch (Exception e) {
			logger.error("更新t_course数据表失败",e);
			return -1;
		}
	}

	@Override
	public Integer removeCourseById(Integer id) {
		try {
			return courseDAO.removeCourseById(id);
		} catch (Exception e) {
			logger.error("从t_course数据表中删除数据失败",e);
			return -1;
		}
	}

	@Override
	public Course getCourseById(Integer id) {
		try {
			return courseDAO.getCourseById(id);
		} catch (Exception e) {
			logger.error("从t_course数据表中获取数据失败",e);
			return null;
		}
	}

	@Override
	public List<Course> getCourseByCourseNameLike(String courseName) {
		try {
			return courseDAO.getCourseByCourseNameLike(courseName);
		} catch (Exception e) {
			logger.error("从t_course数据表中获取数据失败",e);
			return null;
		}
	}

	@Override
	public List<Course> listCourseByStudentId(Integer studentId) {
		try {
			return courseDAO.listCourseByStudentId(studentId);
		} catch (Exception e) {
			logger.error("从t_r_student_course数据表中获取数据失败",e);
			return null;
		}
	}

	@Override
	public List<Course> listCourseByCourseTypeIdAndPage(Integer courseTypeId,Integer startPage,Integer pageSize) {
		try {
			return courseDAO.listCourseByCourseTypeIdAndPage(courseTypeId,startPage,pageSize);
		} catch (Exception e) {
			logger.error("从t_course数据表中获取数据失败",e);
			return null;
		}
	}

	@Override
	public List<Course> listCourseByTeacherId(Integer teacherId) {
		try {
			return courseDAO.listCourseByTeacherId(teacherId);
		} catch (Exception e) {
			logger.error("从t_course数据表中获取数据失败",e);
			return null;
		}
	}

	@Override
	public List<Course> listCourseByPage(Integer startPage, Integer pageSize) {
		try {
			return courseDAO.listCourseByPage(startPage, pageSize);
		} catch (Exception e) {
			logger.error("从t_course数据表中获取数据失败",e);
			return null;
		}
	}

	@Override
	public Integer countAllCourse(){
		try {
			return courseDAO.countAllCourse();
		} catch (Exception e) {
			logger.error("从t_course数据表中获取数据失败",e);
			return -1;
		}
	}

	@Override
	public Integer countCourseByCourseTypeId(Integer courseTypeId){
		try {
			return courseDAO.countCourseByCourseTypeId(courseTypeId);
		} catch (Exception e) {
			logger.error("从t_course数据表中获取数据失败",e);
			return -1;
		}
	}

	@Override
	public Integer countCourseByStudentId(Integer studentId){
		try {
			return courseDAO.countCourseByStudentId(studentId);
		} catch (Exception e) {
			logger.error("从t_course数据表中获取数据失败",e);
			return -1;
		}
	}

	@Override
	public Integer countCourseByTeacherId(Integer teacherId){
		try {
			return courseDAO.countCourseByTeacherId(teacherId);
		} catch (Exception e) {
			logger.error("从t_course数据表中获取数据失败",e);
			return -1;
		}
	}

}
