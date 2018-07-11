package com.hegongshan.mooc.entity;

import java.io.Serializable;

/**
 * 管理员实体 
 * @author hegongshan http://www.hegongshan.com
 */
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer adminId;
	private String username;
	private String password;
	private String nickname;
	private String imageSrc;

	public Admin() {
		super();
	}

	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
}
