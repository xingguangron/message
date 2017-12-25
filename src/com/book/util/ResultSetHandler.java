package com.book.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler {

	//专门处理结果集的方法
	public Object handle(ResultSet rs) throws Exception;
	
}
