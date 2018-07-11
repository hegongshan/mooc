package com.hegongshan.mooc.service;

import java.util.List;

import com.hegongshan.mooc.entity.Lesson;

public interface ILessonService {
	Integer saveLesson(Lesson lesson);

	Integer updateLesson(Lesson lesson);

	Integer removeLessonById(Integer id);

	Lesson getLessonById(Integer id);
	
	List<Lesson> listLessonByCourseId(Integer courseId);
}
