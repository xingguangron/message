package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.beans.User;
import com.book.dao.UserDao;
import com.book.util.DbUtil;
import com.book.util.StringUtil;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name=request.getParameter("name");
		String password=request.getParameter("password");
		request.setAttribute("name", name);
		request.setAttribute("password", password);
	
	if(StringUtil.isEmpty(name)||StringUtil.isEmpty(password)){
		request.setAttribute("message", "不能为空");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}
	
	Connection con=null;
	UserDao dao=new UserDao();
	User u=dao.valdateUser(name, password);
	if(u==null){
		request.setAttribute("message", "用户名或者密码错误");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}else if("1".equals(u.getRole())){
		System.out.println("管理员进行登录");
		HttpSession session=request.getSession();
		session.setAttribute("currentUser",name);
		session.setAttribute("userid",u.getUserid());
		// �ͻ�����ת
		response.sendRedirect("admin_main.jsp");
	}else{
		System.out.println("普通用户进行登录");
		HttpSession session=request.getSession();
		session.setAttribute("currentUser",name);
		// �ͻ�����ת
		response.sendRedirect("user_main.jsp");
	}
	
}
}