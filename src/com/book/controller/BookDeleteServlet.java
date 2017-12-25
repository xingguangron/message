package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.book.dao.BookDao;
import com.book.util.DbUtil;
import com.book.util.ResponseUtil;

public class BookDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//jsp页面数据:$.post("BookDelete",{delIds:ids}
		String book_ids=request.getParameter("delIds");
		System.out.println("ids:"+book_ids);
		String ids[]=book_ids.split(",");
		Connection con=null;
		BookDao dao=new BookDao();
		try{
			con=DbUtil.getConnection();
			JSONObject result=new JSONObject();
			int delNums=dao.gradeDelete(con,ids);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除异常");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		
	}

}
