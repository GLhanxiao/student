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
 * 用于添加单条学生信息
 * @author Administrator
 *
 */
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决post乱码
		request.setCharacterEncoding("UTF-8");
		//接受表单参数
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String description = request.getParameter("description");
		String str = request.getParameter("avgscore");
		int avgscore = Integer.parseInt(str);
		//通过uuid自定义用户id
		String id = MyUUID.getUUID();
		//封装学生对象
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setBirthday(birthday);
		student.setDescription(description);
		student.setAvgscore(avgscore);
		//声明jedis对象
		Jedis jedis = null;
		try {
			//获取jedis资源
			jedis = MyJedis.getJedis();
			//将学生id和分数关联，存入hashset中，以便排序
			jedis.zadd("SCORE_INFO", avgscore, id);
			//将学生对象转为json存入hashmap
			JSONObject json = JSONObject.fromObject(student);
			jedis.hset("STUDENT_INFO", id, json.toString());
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
