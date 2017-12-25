package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.beans.User;
import com.book.dao.UserDao;

public class changPassword extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
request.setCharacterEncoding("utf8");
System.out.println("修改密码被调用");
HttpSession session=request.getSession();
String userid=(String) session.getAttribute("userid");
String name=(String) session.getAttribute("currentUser");
String oldpassword=request.getParameter("oldpassword");
String password1=request.getParameter("password1");
String password2=request.getParameter("password2");
		UserDao dao=new UserDao();
		User u=dao.valdateUser(name, oldpassword);
		if(u==null){
			request.setAttribute("message", "原密码不对");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(password1==null ||password2==null||"".equals(password1)||"".equals(password1)){
			request.setAttribute("message", "密码不能为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(!password1.equals(password2)){
			request.setAttribute("message", "两次密码不一致");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if(password1.equals(password2)){
			dao.changPassword(userid, password1);
			request.setAttribute("message", "修改成功");
		}
		
	}

}
