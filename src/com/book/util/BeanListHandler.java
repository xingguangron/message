package com.book.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
	结果集处理类（专门处理多行结果的处理类）
*/
public class BeanListHandler implements ResultSetHandler {
	
	private Class clazz;
	
	public BeanListHandler(Class clazz) {
		this.clazz = clazz;
	}

	//对结果集进行处理，处理完成后返回一个Bean对象
	public List handle(ResultSet rs) {
		List result = new ArrayList();
		try {
			//遍历结果集
			while (rs.next()) {
				//通过class类创建对象
				Object o = clazz.newInstance();
				//获取结果集的元数据对象
				ResultSetMetaData metadata = rs.getMetaData();
				//获取列的数量
				int count = metadata.getColumnCount();
				//获取所有列的数据
				for (int i = 1; i <= count; i++) {
					//获取了每一列的数据，把每一列数据设置class对象中？
					//获取列名
					String fieldName = metadata.getColumnLabel(i);
					//使用反射设置对象的属性
					Field field = clazz.getDeclaredField(fieldName);
					//暴力反射
					field.setAccessible(true);
					//设置对象的属性值
					field.set(o, rs.getObject(i));
				}
				result.add(o);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
}
