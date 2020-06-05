package com.myliabilities.db.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Date;

import com.myliabilities.utils.MyClassUtils;

/**
 * 插入语句模板
 * 
 * @author Pactera
 * 2020年5月25日
 */
public class InsertTemplate {

	/**
	 * 生成一条insert语句
	 * 
	 * @param tableName 表名
	 * @param columns 字段集合
	 * @return sql
	 */
	public static String createInsertSQL( String tableName, String... columns) {

		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		sb.append("insert into ").append(tableName).append(" (");
		for (int i = 0; i < columns.length; i++) {
			sb.append(columns[i]);
			sb2.append("?");
			if (i < columns.length - 1) {
				sb.append(",");
				sb2.append(",");
			}
		}
		sb.append(") values (").append(sb2.toString()).append(");");

		return sb.toString();
	}

	/**
	 * 根据字段集合组装对应的PreparedStatement
	 * 
	 * @param <T> 数据对象
	 * @param ps PreparedStatement
	 * @param t 数据对象
	 * @param clazz 数据对象.class
	 * @param columns 字段集合
	 * @throws Exception
	 */
	public static <T> void createPreparedStatement( PreparedStatement ps, T t, Class<T> clazz, String[] columns)
			throws Exception {
		for (int i = 0; i < columns.length; i++) {
			String mname = "get" + MyClassUtils.upFirstChar(columns[i]);
			Field f = clazz.getDeclaredField(columns[i]);
			Method m = clazz.getDeclaredMethod(mname);
			Object o = m.invoke(t);
			if ("id".equals(columns[i])) {
				/*因为有自增,所以物理主键id赋值null就好*/
				ps.setObject(i + 1, null);
			} else if (f.getType() == Date.class) {
				/*日期都是用long存储*/
				if (o == null) {
					ps.setObject(i + 1, System.currentTimeMillis());
				} else {
					ps.setObject(i + 1, ((Date) o).getTime());
				}
			} else if (f.getType() == Long.class || f.getType() == Integer.class || f.getType() == Double.class
					|| f.getType() == Float.class || f.getType() == BigDecimal.class) {
				/*推荐都使用对象,数字类如果为空需要默认值0*/
				if (o == null) {
					ps.setObject(i + 1, 0);
				} else {
					ps.setObject(i + 1, o);
				}
			} else {
				ps.setObject(i + 1, o);
			}
		}
	}
}
