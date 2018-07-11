package com.hegongshan.mooc.entity;

import java.io.Serializable;

public class Carousel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer carouselId;
	private Integer priority;
	private Course course;

	public Integer getCarouselId() {
		return carouselId;
	}

	public void setCarouselId(Integer carouselId) {
		this.carouselId = carouselId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Carousel [carouselId=" + carouselId + ", priority=" + priority ;
	}
}
