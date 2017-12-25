package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.beans.*;
import com.book.dao.BookDao;
import com.book.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
public class BookaddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//<!-- String book_name, String author,String publish,String content,String reason -->
		String book_name=request.getParameter("book_name");
		System.out.println(book_name);
		String author=request.getParameter("author");
		String publish=request.getParameter("publish");
		String content=request.getParameter("content");
		String reason=request.getParameter("reason");
		Connection con=null;
		BookDao dao=new BookDao();
		
			try {
				con=DbUtil.getConnection();
				dao.addBook(con, book_name, author, publish, content, reason);
				System.out.println("save调用了");
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
	
	}

}
