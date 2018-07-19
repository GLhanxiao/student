package com.lession4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lession4.utils.MyJedis;

import redis.clients.jedis.Jedis;
/**
 * 用于通过学生id删除学生信息
 * @author Administrator
 *
 */
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要删除的学生id
		String id = request.getParameter("id");
		if(id==null) return;
		//声明jedis对象
		Jedis jedis = null;
		try {
			//取得jedis对象
		    jedis = MyJedis.getJedis();
		    //通过id删除hashmap中的学生记录
			jedis.hdel("STUDENT_INFO", id);
			//通过id删除set中的分数记录
			jedis.zrem("SCORE_INFO", id);
			//重定向到列表页
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
