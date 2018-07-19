package com.lession4.entity;

import java.util.List;

public class PageBean {
	//当前页
	private int currentPage;
	//总页数
	private int totalPage;
	//分页数据集合
	private List list;
	//起始索引
	private int index;
	public int getIndex() {
		//为了页面给学生编号
		return index++;			
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
