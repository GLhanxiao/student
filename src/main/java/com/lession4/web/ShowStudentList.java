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
 * 用于通过分数倒序显示学生信息列表
 * @author Administrator
 *
 */

public class ShowStudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前页
		String str = request.getParameter("currentPage");
		if(str==null)str="1";
		int currentPage = Integer.parseInt(str);
		//声明jedis对象
		Jedis jedis = null;
		try {
		//获取jedis对象
		jedis = MyJedis.getJedis();
		//每页显示条数
		int page = 10;
		//获得总记录数
		Long totalCount = jedis.zcard("SCORE_INFO");
		//起始索引
		int index = (currentPage-1)*page;
		//获得总页数
		int totalPage=(int) Math.ceil(1.0*totalCount/page);
		//获得倒序的学生id集合
		Set<String> zrangeByScore = jedis.zrevrangeByScore("SCORE_INFO", 100, 0, index, page);
		if(zrangeByScore!=null){
			List<Student> list = new ArrayList<Student>();
			//遍历学生id集合
			for (String string : zrangeByScore) {
				//通过学生id取得学生对象json串
				String json = jedis.hget("STUDENT_INFO", string);
				//将学生对象json转为学生对象
				JSONObject fromObject = JSONObject.fromObject(json);
				Student student = (Student) JSONObject.toBean(fromObject, Student.class);
				//将学生对象添加到集合中
				list.add(student);
			}
			//封装pageBean
			PageBean pageBean = new PageBean();
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setCurrentPage(currentPage);
			pageBean.setIndex(index);
			//学生对象集合放入request域中
			request.setAttribute("pageBean", pageBean);
			//转发
			request.getRequestDispatcher("/student/list.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//关闭jedis连接
			jedis.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
