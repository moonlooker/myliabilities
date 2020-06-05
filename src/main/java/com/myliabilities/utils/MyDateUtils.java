package com.myliabilities.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtils {

	/**
	 * 获取当月起始时间,与下月起始时间,用于整月区间查询
	 * @return Date[]{begin,end}
	 */
	public static Date[] thisMonthBeginEnd() {
		Date[] tmp = new Date[2];
		Calendar cb = Calendar.getInstance();
		cb.add(Calendar.MONTH, 0);
		cb.set(Calendar.DAY_OF_MONTH, 1);
		cb.set(Calendar.HOUR_OF_DAY, 0);
		cb.set(Calendar.MINUTE, 0);
		cb.set(Calendar.SECOND, 0);
		cb.set(Calendar.MILLISECOND, 0);
		tmp[0] = cb.getTime();
		Calendar ce = Calendar.getInstance();
		ce.setTime(tmp[0]);
		ce.set(Calendar.DAY_OF_MONTH, ce.getActualMaximum(Calendar.DAY_OF_MONTH) + 1);
		tmp[1] = ce.getTime();
		return tmp;
	}
	/**
	 * 计算指定月份起始与结束日期
	 * @param date
	 * @return Date[]{begin,end}
	 */
	public static Date[] monthBeginEnd(Date date) {
		Date[] tmp = new Date[2];
		Calendar cb = Calendar.getInstance();
		cb.setTime(date);
		cb.set(Calendar.DAY_OF_MONTH, 1);
		cb.set(Calendar.HOUR_OF_DAY, 0);
		cb.set(Calendar.MINUTE, 0);
		cb.set(Calendar.SECOND, 0);
		cb.set(Calendar.MILLISECOND, 0);
		tmp[0] = cb.getTime();
		Calendar ce = Calendar.getInstance();
		ce.setTime(tmp[0]);
		ce.set(Calendar.DAY_OF_MONTH, ce.getActualMaximum(Calendar.DAY_OF_MONTH) + 1);
		tmp[1] = ce.getTime();
		return tmp;
	}

	public static void main( String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date[] t = MyDateUtils.monthBeginEnd(sdf.parse("20200202"));
		System.out.println(t[0]);
		System.out.println(t[1]);
	}
}
