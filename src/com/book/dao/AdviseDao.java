package com.book.dao;

import com.book.beans.*;
import com.book.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdviseDao extends BaseDao {
	
	//状态等于0部分
	public ResultSet adviseList(Connection con, PageBean pageBean, Book book)
			throws Exception {
		StringBuffer sb = new StringBuffer("select * from book");
		String state = "0";
		sb.append(" where state=" + "'" + state + "'" + " ");// ：0：未审核,1:审核通过,2:未审核通过
		if (book != null && StringUtil.isNotEmpty(book.getBook_name())) {
			sb.append(" and book_name like '%" + book.getBook_name() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + ","
					+ pageBean.getRows());
		}

		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int adivseCount(Connection con, Book book) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from book");

		String state = "0";
		sb.append(" where state=" + "'" + state + "'" + " ");// ：0：未审核,1:审核通过,2:未审核通过
		if (StringUtil.isNotEmpty(book.getBook_name())) {
			sb.append(" and book_name like '%" + book.getBook_name() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public ResultSet adviseListed(Connection con, PageBean pageBean, Book book)
			throws Exception {
		StringBuffer sb = new StringBuffer("select * from book");
		String state = "1";
		sb.append(" where state=" + "'" + state + "'" + " ");// ：0：未审核,1:审核通过,2:未审核通过
		if (book != null && StringUtil.isNotEmpty(book.getBook_name())) {
			sb.append(" and book_name like '%" + book.getBook_name() + "%'");
		}
		if (pageBean != null) {
			sb.append("limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		System.out.println(sb.toString());
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int adivseCounted(Connection con, Book book) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from book");

		String state = "1";
		sb.append(" where state=" + "'" + state + "'" + " ");// ：0：未审核,1:审核通过,2:未审核通过
		if (StringUtil.isNotEmpty(book.getBook_name())) {
			sb.append(" and book_name like '%" + book.getBook_name() + "%'");
		}
		System.out.println(sb.toString());
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public int bookAgree(Connection con, String ids[]) throws Exception {
		for (String id : ids) {
			String sql = "update book set state='1'  where book_id = ?";

			update(sql, new Object[] {id});

		}
		return ids.length;
	}
}
