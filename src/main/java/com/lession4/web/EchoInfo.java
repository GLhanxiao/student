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
 * 用于回显单条学生信息
 * @author lnovo
 *
 */

public class EchoInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		//取到要回显的学生id
		String id = request.getParameter("currentId");
		//声明jedis对象
		Jedis jedis = null;
		try {
			//取到jedis对象
			jedis = MyJedis.getJedis();
			//通过学生id取到学生信息json
			String hget = jedis.hget("STUDENT_INFO", id);
			//把json写给页面回显
			response.getWriter().write(hget);
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
