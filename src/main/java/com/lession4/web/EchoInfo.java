package com.lession4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lession4.utils.MyJedis;

import redis.clients.jedis.Jedis;
/**
 * 
 * ���ڻ��Ե���ѧ����Ϣ
 * @author lnovo
 *
 */

public class EchoInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		//ȡ��Ҫ���Ե�ѧ��id
		String id = request.getParameter("currentId");
		//����jedis����
		Jedis jedis = null;
		try {
			//ȡ��jedis����
			jedis = MyJedis.getJedis();
			//ͨ��ѧ��idȡ��ѧ����Ϣjson
			String hget = jedis.hget("STUDENT_INFO", id);
			//��jsonд��ҳ�����
			response.getWriter().write(hget);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ر�jedis
			jedis.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
