package com.lession4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lession4.utils.MyJedis;

import redis.clients.jedis.Jedis;
/**
 * ����ͨ��ѧ��idɾ��ѧ����Ϣ
 * @author Administrator
 *
 */
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҪɾ����ѧ��id
		String id = request.getParameter("id");
		if(id==null) return;
		//����jedis����
		Jedis jedis = null;
		try {
			//ȡ��jedis����
		    jedis = MyJedis.getJedis();
		    //ͨ��idɾ��hashmap�е�ѧ����¼
			jedis.hdel("STUDENT_INFO", id);
			//ͨ��idɾ��set�еķ�����¼
			jedis.zrem("SCORE_INFO", id);
			//�ض����б�ҳ
			response.sendRedirect(request.getContextPath()+"/showStudentList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
