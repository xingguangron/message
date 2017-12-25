package com.book.dao;
import com.book.beans.*;
import com.book.util.*;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class BookDao extends BaseDao{

	public ResultSet bookList(Connection con,PageBean pageBean,Book book)throws Exception{
		StringBuffer sb=new StringBuffer("select * from book");
	
		if(book!=null && StringUtil.isNotEmpty(book.getBook_name())){
			sb.append(" and book_name like '%"+book.getBook_name()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int bookCount(Connection con,Book book)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from book");
		if(StringUtil.isNotEmpty(book.getBook_name())){
			sb.append(" and book_name like '%"+book.getBook_name()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}

	public void addBook(Connection con,String book_name, String author,String publish,String content,String reason) throws Exception {
	//生成id
		int count=this.bookCount(con, new Book());
		count++;
		System.out.println("count:"+count);
		StringBuilder str=new StringBuilder("B1000");
		str.append(count);
	String book_id=str.toString();
	
	//生成state:
	String state="0";
	
		book_id.concat(String.valueOf(count));
		
		String sql = "insert into book (book_id,book_name,author,publish,content,reason,state)"+
				" values (?,?,?,?,?,?,?)";
		
		update(sql, new Object[]{book_id,book_name,author,publish,content,reason,state});
	}

	public int gradeDelete(Connection con,String ids[])throws Exception{
		PreparedStatement pstmt=null;
		Statement stmt=null;
		for(int i=0;i<ids.length;i++){
		String sql="delete from book where book_id="+"'"+ids[i]+"'";//in("+"'"+id+"'"+")";
		System.out.println(sql);
		//pstmt=con.prepareStatement(sql);
		stmt=con.createStatement();
		stmt.executeUpdate(sql);
		}
		
		return ids.length;
	}
	public void updateBook(String book_id, String book_name, String author,String publish,String content,String reason) {
		String sql = "update book set book_name=?,author=?,publish=?,content=?,reason=?  where book_id = ?";
		
		update(sql, new Object[]{book_name,author,publish,content,reason,book_id});
	}
	
	
}
