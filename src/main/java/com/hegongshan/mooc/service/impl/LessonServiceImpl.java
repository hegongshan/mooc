package com.hegongshan.mooc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hegongshan.mooc.dao.ILessonDAO;
import com.hegongshan.mooc.entity.Lesson;
import com.hegongshan.mooc.service.ILessonService;

@Service("lessonService")
public class LessonServiceImpl implements ILessonService {
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	@Resource
	private ILessonDAO lessonDAO;
	@Override
	public Integer saveLesson(Lesson lesson) {
		try {
			return lessonDAO.saveLesson(lesson);
		} catch (Exception e) {
			logger.error("插入数据到t_lesson数据表失败", e);
			return -1;
		}
	}

	@Override
	public Integer updateLesson(Lesson lesson) {
		try {
			return lessonDAO.updateLesson(lesson);
		} catch (Exception e) {
			logger.error("更新t_lesson数据表失败", e);
			return -1;
		}
	}

	@Override
	public Integer removeLessonById(Integer id) {
		try {
			return lessonDAO.removeLessonById(id);
		} catch (Exception e) {
			logger.error("从t_lesson数据表中删除数据失败", e);
			return -1;
		}
	}

	@Override
	public Lesson getLessonById(Integer id) {
		try {
			return lessonDAO.getLessonById(id);
		} catch (Exception e) {
			logger.error("从t_lesson数据表中获取数据失败", e);
			return null;
		}
	}

	@Override
	public List<Lesson> listLessonByCourseId(Integer courseId) {
		try {
			return lessonDAO.listLessonByCourseId(courseId);
		} catch (Exception e) {
			logger.error("从t_lesson数据表中获取数据失败", e);
			return null;
		}
	}

}
