package com.lession4.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.lession4.entity.PageBean;
import com.lession4.entity.Student;
import com.lession4.utils.MyJedis;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
/**
 * ����ͨ������������ʾѧ����Ϣ�б�
 * @author Administrator
 *
 */

public class ShowStudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰҳ
		String str = request.getParameter("currentPage");
		if(str==null)str="1";
		int currentPage = Integer.parseInt(str);
		//����jedis����
		Jedis jedis = null;
		try {
		//��ȡjedis����
		jedis = MyJedis.getJedis();
		//ÿҳ��ʾ����
		int page = 10;
		//����ܼ�¼��
		Long totalCount = jedis.zcard("SCORE_INFO");
		//��ʼ����
		int index = (currentPage-1)*page;
		//�����ҳ��
		int totalPage=(int) Math.ceil(1.0*totalCount/page);
		//��õ����ѧ��id����
		Set<String> zrangeByScore = jedis.zrevrangeByScore("SCORE_INFO", 100, 0, index, page);
		if(zrangeByScore!=null){
			List<Student> list = new ArrayList<Student>();
			//����ѧ��id����
			for (String string : zrangeByScore) {
				//ͨ��ѧ��idȡ��ѧ������json��
				String json = jedis.hget("STUDENT_INFO", string);
				//��ѧ������jsonתΪѧ������
				JSONObject fromObject = JSONObject.fromObject(json);
				Student student = (Student) JSONObject.toBean(fromObject, Student.class);
				//��ѧ��������ӵ�������
				list.add(student);
			}
			//��װpageBean
			PageBean pageBean = new PageBean();
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setCurrentPage(currentPage);
			pageBean.setIndex(index);
			//ѧ�����󼯺Ϸ���request����
			request.setAttribute("pageBean", pageBean);
			//ת��
			request.getRequestDispatcher("/student/list.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ر�jedis����
			jedis.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
