package com.lession4.entity;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
	/**
	 * 学生实体类
	 */
	private static final long serialVersionUID = 1L;
	public Student() {
	}
	private String id;
	private String name;
	private String birthday;
	private String description;
	private int avgscore;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAvgscore() {
		return avgscore;
	}
	public void setAvgscore(int avgscore) {
		this.avgscore = avgscore;
	}
	
}
