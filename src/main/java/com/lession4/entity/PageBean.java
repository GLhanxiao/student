package com.lession4.entity;

import java.util.List;

public class PageBean {
	//��ǰҳ
	private int currentPage;
	//��ҳ��
	private int totalPage;
	//��ҳ���ݼ���
	private List list;
	//��ʼ����
	private int index;
	public int getIndex() {
		//Ϊ��ҳ���ѧ�����
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
