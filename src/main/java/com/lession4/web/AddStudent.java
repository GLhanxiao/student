package com.lession4.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lession4.entity.Student;
import com.lession4.utils.MyJedis;
import com.lession4.utils.MyUUID;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * ������ӵ���ѧ����Ϣ
 * @author Administrator
 *
 */
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���post����
		request.setCharacterEncoding("UTF-8");
		//���ܱ�����
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String description = request.getParameter("description");
		String str = request.getParameter("avgscore");
		int avgscore = Integer.parseInt(str);
		//ͨ��uuid�Զ����û�id
		String id = MyUUID.getUUID();
		//��װѧ������
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setBirthday(birthday);
		student.setDescription(description);
		student.setAvgscore(avgscore);
		//����jedis����
		Jedis jedis = null;
		try {
			//��ȡjedis��Դ
			jedis = MyJedis.getJedis();
			//��ѧ��id�ͷ�������������hashset�У��Ա�����
			jedis.zadd("SCORE_INFO", avgscore, id);
			//��ѧ������תΪjson����hashmap
			JSONObject json = JSONObject.fromObject(student);
			jedis.hset("STUDENT_INFO", id, json.toString());
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
