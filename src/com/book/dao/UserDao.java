package com.book.dao;

import com.book.beans.User;
import com.book.util.BeanHandler;
import com.book.util.StringUtil;

public class UserDao extends BaseDao {
	/*
	 * public int valdateUser(String userName, String userPass) { String
	 * admin="1"; String user="0"; Object o1 =null; Object o2 =null; String sql
	 * = "select * from admin where admin_name=? and password=? and role=?";
	 * 
	 * o1= find(sql, new Object[]{userName,userPass,admin}, new
	 * BeanHandler(User.class)); o2= find(sql, new
	 * Object[]{userName,userPass,user}, new BeanHandler(User.class));
	 * 
	 * if(o1!=null){ return 1;//用户是管理员 }else if(o2!=null){//用户是普通用户 return 0; }
	 * else{//用户密码错误 return 2; } }
	 */

	public User valdateUser(String userName, String userPass) {
		String sql = "select * from user where name=? and password=? ";
		User u = (User) find(sql, new Object[] { userName, userPass },
				new BeanHandler(User.class));
		return u;
	}

	public void addUser(String name, String password, String email, String phone) {
		String role = "0";// 普通用户
		String userid = StringUtil.getId();
		String sql = "insert into user (name,password,email,phone,role)"
				+ " values (?,?,?,?,?)";

		update(sql, new Object[] { name, password, email, phone, role });
	}

	public void changPassword(String userid, String newpassword) {
		String sql = "update user set password=? where userid = ?";

		update(sql, new Object[] { newpassword, userid });
	}

}
