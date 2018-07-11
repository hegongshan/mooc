package com.hegongshan.mooc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer courseId;
	private String courseName;
	private String description;
	private String coverImagePath;
	private Date releaseTime;
	private Integer studentNumber;
	// private Integer starLevel;
	private String resourceSrc;
	private Teacher teacher;
	private CourseType courseType;
	private List<Lesson> lessons;
	private List<Comment> comments;

	public Course() {
		super();
	}

	public Course(String courseName, String description, String coverImagePath, Teacher teacher,
			CourseType courseType) {
		super();
		this.courseName = courseName;
		this.description = description;
		this.coverImagePath = coverImagePath;
		this.teacher = teacher;
		this.courseType = courseType;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public String getResourceSrc() {
		return resourceSrc;
	}

	public void setResourceSrc(String resourceSrc) {
		this.resourceSrc = resourceSrc;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
