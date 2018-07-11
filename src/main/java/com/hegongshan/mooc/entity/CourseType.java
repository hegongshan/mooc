package com.hegongshan.mooc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CourseType implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer courseTypeId;
	private String courseTypeName;
	private Date releaseTime;
	private List<Course> courses;

	public CourseType() {
		super();
	}

	public Integer getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(Integer courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "CourseType [courseTypeId=" + courseTypeId + ", courseTypeName=" + courseTypeName + ", releaseTime="
				+ releaseTime + ", courses=" + courses + "]";
	}
}
