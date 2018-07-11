package com.hegongshan.mooc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hegongshan.mooc.dao.ICourseTypeDAO;
import com.hegongshan.mooc.entity.CourseType;
import com.hegongshan.mooc.service.ICourseTypeService;

@Service("courseTypeService")
public class CourseTypeServiceImpl implements ICourseTypeService {
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	@Resource
	private ICourseTypeDAO courseTypeDAO;

	@Override
	public Integer saveCourseType(CourseType courseType) {
		try {
			return courseTypeDAO.saveCourseType(courseType);
		} catch (Exception e) {
			logger.error("插入数据到t_course_type数据表失败", e);
			return -1;
		}
	}

	@Override
	public Integer updateCourseType(CourseType courseType) {
		try {
			return courseTypeDAO.updateCourseType(courseType);
		} catch (Exception e) {
			logger.error("更新t_course_type数据表失败", e);
			return -1;
		}
	}

	@Override
	public Integer removeCourseTypeById(Integer id) {
		try {
			return courseTypeDAO.removeCourseTypeById(id);
		} catch (Exception e) {
			logger.error("从t_course_type数据表中删除数据失败", e);
			return -1;
		}
	}
	

	@Override
	public Integer removeCourseTypeBatch(Integer[] ids) {
		try {
			return courseTypeDAO.removeCourseTypeBatch(ids);
		} catch (Exception e) {
			logger.error("从t_course_type数据表中删除数据失败", e);
			return -1;
		}
	}

	@Override
	public CourseType getCourseTypeById(Integer id) {
		try {
			return courseTypeDAO.getCourseTypeById(id);
		} catch (Exception e) {
			logger.error("从t_course_type数据表中获取数据失败", e);
			return null;
		}
	}

	@Override
	public List<CourseType> listAllCourseType() {
		try {
			return courseTypeDAO.listAllCourseType();
		} catch (Exception e) {
			logger.error("从t_course_type数据表中获取数据失败", e);
			return null;
		}
	}

}
