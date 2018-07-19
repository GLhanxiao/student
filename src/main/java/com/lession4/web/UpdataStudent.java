package com.lession4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lession4.entity.Student;
import com.lession4.utils.MyJedis;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
/**
 * �����޸ĵ���ѧ����Ϣ
 * @author lnovo
 *
 */

public class UpdataStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���post��������
		request.setCharacterEncoding("UTF-8");
		//��ȡѧ��id
		String id = request.getParameter("id");
		//��ȡѧ��name
		String name = request.getParameter("name");
		//��ȡѧ������
		String birthday = request.getParameter("birthday");
		//��ȡѧ����ע
		String description = request.getParameter("description");
		//��ȡѧ��ƽ����
		String str = request.getParameter("avgscore");
		int avgscore = Integer.parseInt(str);
		//��װѧ������
		Student student = new Student();
		student.setAvgscore(avgscore);
		student.setBirthday(birthday);
		student.setDescription(description);
		student.setId(id);
		student.setName(name);
		//����jedis����
		Jedis jedis = null;
		try {
			//ȡ��redis����
			jedis = MyJedis.getJedis();
			//��ѧ������תΪjson����
			JSONObject json = JSONObject.fromObject(student);
			//��json�������hashmap��
			jedis.hset("STUDENT_INFO", id, json.toString());
			//��������Ϣ��id����hashset�У���������
			jedis.zadd("SCORE_INFO", avgscore, id);
			//�ض����б�ҳ
			response.sendRedirect(request.getContextPath()+"/showStudentList");
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
