package com.book.controllerUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.book.beans.Book;
import com.book.beans.PageBean;
import com.book.dao.AdviseDao;
import com.book.util.DbUtil;
import com.book.util.JsonUtil;
import com.book.util.ResponseUtil;

public class userListServlet extends HttpServlet {
/*
 1.只显示：管理员同意推荐部分
*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		System.out.println("page:"+page+",rows:"+rows);
		String book_name=request.getParameter("book_name");
		System.out.println("List:"+book_name);
		if(book_name==null){
			book_name="";
		}
		Book book=new Book();
		AdviseDao dao=new AdviseDao();
		book.setBook_name(book_name);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		
			try {
				con=DbUtil.getConnection();
				JSONObject result=new JSONObject();
				JSONArray jsonArray=JsonUtil.formatRsToJsonArray(dao.adviseListed(con, pageBean,book));
				int total=dao.adivseCounted(con,book);
				result.put("rows", jsonArray);
				result.put("total", total);
				ResponseUtil.write(response, result);
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}

}
