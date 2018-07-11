package com.hegongshan.mooc.dao;

import java.util.List;

import javax.annotation.Resource;

import com.hegongshan.mooc.entity.Lesson;

@Resource
public interface ILessonDAO {
	Integer saveLesson(Lesson lesson) throws Exception;

	Integer updateLesson(Lesson lesson) throws Exception;

	Integer removeLessonById(Integer id) throws Exception;

	Lesson getLessonById(Integer id) throws Exception;
	
	List<Lesson> listLessonByCourseId(Integer courseId) throws Exception;
}
