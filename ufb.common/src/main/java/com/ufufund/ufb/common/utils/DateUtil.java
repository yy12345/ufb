package com.ufufund.ufb.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String FULL_PATTERN_1 = "yyyyMMddHHmmss";
	public static final String FULL_PATTERN_2 = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATE_PATTERN_1 = "yyyyMMdd";
	public static final String DATE_PATTERN_2 = "yyyy-MM-dd";
	
	public static final String TIME_PATTERN_1 = "HHmmss";
	public static final String TIME_PATTERN_2 = "HH:mm:ss";
	
	
	/**
	 * 获取Date型时间
	 * @param time 时间
	 * @param pattern 模式
	 * @return
	 */
	public static Date parse(String time,String pattern){
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
		return date;
	}
	
	/**
	 * 格式化Date型时间
	 * @param time Date型时间
	 * @param pattern 模式
	 * @return
	 */
	public static String format(Date time, String pattern){
		String str = "";
		if(time == null || "".equals(time)){
			return str;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		str = sdf.format(time);
		return str;
	}
	
	/**
	 * 时间转型
	 * @param time 时间
	 * @param sourcePattern 源模式
	 * @param destPattern 目标模式
	 * @return
	 */
	public static String transfer(String time,String sourcePattern, String destPattern){
		String str = "";
		if(time == null || "".equals(time)){
			return str;
		}
		SimpleDateFormat source = new SimpleDateFormat(sourcePattern);
		SimpleDateFormat dest = new SimpleDateFormat(destPattern);
		try {
			str = dest.format(source.parse(time));
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
		return str;
	}
	
	/**
	 * 取当前日期的N天后
	 * @param date
	 * @param n N天后（可为负数，为负数则前N天）
	 * @return
	 */
	public static Date getNextDay(Date date,int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 判断两个日期的天数差
	 * <br/>若d1晚于d2，则为正数；否则为0或者负数
	 * @param d1
	 * @param d2
	 * @return <code>int</code> (d1-d2)
	 */
	public static int subDateToDay(Date d1,Date d2){
		long l = d1.getTime() - d2.getTime();
		long day = l/(24*60*60*1000);
		return (int)day;
	}
	

	public static void main(String args[]){
		
		Date date = DateUtil.parse("20150405120015", DateUtil.FULL_PATTERN_1);
		
		Date date1 = DateUtil.getNextDay(date, -10);
		
		int str1 = DateUtil.subDateToDay(date1, date);
		System.out.println(str1);
	}
}
