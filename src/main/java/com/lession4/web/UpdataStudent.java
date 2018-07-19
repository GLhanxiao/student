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
 * 用于修改单条学生信息
 * @author lnovo
 *
 */

public class UpdataStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决post中文乱码
		request.setCharacterEncoding("UTF-8");
		//获取学生id
		String id = request.getParameter("id");
		//获取学生name
		String name = request.getParameter("name");
		//获取学生生日
		String birthday = request.getParameter("birthday");
		//获取学生备注
		String description = request.getParameter("description");
		//获取学生平均分
		String str = request.getParameter("avgscore");
		int avgscore = Integer.parseInt(str);
		//封装学生对象
		Student student = new Student();
		student.setAvgscore(avgscore);
		student.setBirthday(birthday);
		student.setDescription(description);
		student.setId(id);
		student.setName(name);
		//声明jedis对象
		Jedis jedis = null;
		try {
			//取到redis对象
			jedis = MyJedis.getJedis();
			//将学生对象转为json对象
			JSONObject json = JSONObject.fromObject(student);
			//将json对象存入hashmap中
			jedis.hset("STUDENT_INFO", id, json.toString());
			//将分数信息和id存入hashset中，方便排序
			jedis.zadd("SCORE_INFO", avgscore, id);
			//重定向到列表页
			response.sendRedirect(request.getContextPath()+"/showStudentList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//关闭jedis
			jedis.close();
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
