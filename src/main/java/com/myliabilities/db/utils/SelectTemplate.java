package com.myliabilities.db.utils;

import com.myliabilities.utils.MyClassUtils;

/**
 * 查询语句模板
 * @author LL
 *
 */
public class SelectTemplate {

	/**
	 * 根据数据库映射生成结果字段值
	 * 数据对象成员变量必须与表字段一致
	 * @param <T> 数据对象
	 * @param clazz 数据对象.class
	 * @return column1,column2,column3...
	 */
	public static <T> String selectResultColumn(Class<T> clazz) {
		String[] columns = MyClassUtils.getFields(clazz);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < columns.length; i++) {
			sb.append(columns[i]);
			if (i < columns.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
}
