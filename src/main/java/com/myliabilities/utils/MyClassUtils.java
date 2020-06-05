package com.myliabilities.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

public class MyClassUtils {

	/**
	 * 根据类获取类成员变量集合
	 * @param clazz
	 * @return String[]
	 */
	public static String[] getFields( Class<?> clazz) {
		Field[] fs = clazz.getDeclaredFields();
		String[] fields = new String[fs.length];
		for (int i = 0; i < fs.length; i++) {
			fields[i] = fs[i].getName();
		}
		return fields;
	}

	/**
	 * 给类中某个字段赋值
	 * 标准数据对象,成员变量私有,使用setxxx设置
	 * 
	 * @param <T> 需要赋值的对象
	 * @param field 成员变量名
	 * @param o 需要赋值的值
	 * @param t 需要赋值的对象
	 * @param clazz 需要赋值的对象.class
	 * @throws Exception 会有异常
	 */
	public static <T> void setField( String field, Object o, T t, Class<?> clazz) throws Exception {
		if (o != null) {
			Field f = clazz.getDeclaredField(field);
			String mname = "set" + upFirstChar(field);
			Method m = clazz.getDeclaredMethod(mname, f.getType());
			if (f.getType() == Long.class && o instanceof Integer) {
				//因为sqllite没有long型,根据存入来判断,没有达到long长度的id查询返回是integer
				m = clazz.getDeclaredMethod(mname, Long.class);
				m.invoke(t, new BigDecimal((Integer) o).longValue());
			} else if (f.getType() == Date.class) {
				//日期类型存储都是long型,需要转换
				m.invoke(t, new Date((Long) o));
			} else if (o instanceof Double) {
				//double需要转成BigDecimal才能处理
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(false);
				m.invoke(t, new BigDecimal(nf.format((Double) o)));
			} else {
				m.invoke(t, o);
			}
		}
	}

	/**
	 * 字符串首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upFirstChar( String str) {
		char[] chars = str.toCharArray();
		if (chars[0] >= 97 && chars[0] <= 122) {
			chars[0] -= 32;
		}
		return String.valueOf(chars);
	}

	/**
	 * 将一个标准数据对象输出为String
	 * 
	 * @param <T> 数据对象
	 * @param t 数据对象
	 * @param clazz 数据对象.class
	 * @return 成员变量名=值
	 * @throws Exception
	 */
	public static <T> String objectToString( T t, Class<?> clazz) throws Exception {
		StringBuilder sb = new StringBuilder();
		Field[] fs = clazz.getDeclaredFields();
		for (Field f : fs) {
			String mname = "get" + upFirstChar(f.getName());
			Method m = clazz.getDeclaredMethod(mname);
			sb.append(f.getName()).append("=").append(m.invoke(t)).append(",");
		}
		return sb.toString();
	}

	/**
	 * 根据成员变量名获取标准数据对象值
	 * 非对象类型可能会有问题,例如long,int等
	 * 
	 * @param <T> 数据对象
	 * @param field 成员变量名
	 * @param t 数据对象
	 * @param clazz 数据对象.class
	 * @return 成员变量值
	 * @throws Exception
	 */
	public static <T> Object getFieldValue( String field, T t, Class<?> clazz) throws Exception {
		String mname = "get" + upFirstChar(field);
		Method m = clazz.getDeclaredMethod(mname);
		return m.invoke(t);
	}

	public static void main( String[] args) {
		double d = 34645231231.019623123;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		System.out.println(nf.format(d));
	}
}
