package com.hegongshan.mooc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer teacherId;
	private String username;
	private String password;
	private String realname;
	private String nickname;
	private String email;
	private String imageSrc;
	private Date birthday;
	private Integer sex;
	private String address;
	private String profile;
	private String field;
	private Integer status;
	private String activeCode;
	private Date registerTime;
	private List<Course> courses;

	public Teacher() {
		super();
	}

	public Teacher(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Teacher(String username, String password, String field) {
		super();
		this.username = username;
		this.password = password;
		this.field = field;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", username=" + username + ", password=" + password + ", realname="
				+ realname + ", nickname=" + nickname + ", email=" + email + ", imageSrc=" + imageSrc + ", birthday="
				+ birthday + ", sex=" + sex + ", address=" + address + ", profile=" + profile + ", field=" + field
				+ ", status=" + status + ", activeCode=" + activeCode + ", registerTime=" + registerTime + ", courses="
				+ courses + "]";
	}

}
