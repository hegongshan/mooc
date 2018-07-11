package com.hegongshan.mooc.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 评论表实体
 * @author hegongshan http://www.hegongshan.com
 *
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer commentId;
	private String content;
	private Date releaseTime;
	private Student student;
	private Integer parentId;
	private Course course;
	
	public Comment() {}

	public Comment(Integer commentId, String content, Date releaseTime, Student student, Integer parentId,
			Course course) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.releaseTime = releaseTime;
		this.student = student;
		this.parentId = parentId;
		this.course = course;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
