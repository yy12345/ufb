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
	public static String convert(String time,String sourcePattern, String destPattern){
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
	 * 取当前日期的N天后
	 * @param date yyyyMMdd型字符串日期
	 * @param n N天后（可为负数，为负数则前N天）
	 * @return
	 */
	public static String getNextDay(String date,int n) {
		Date now = parse(date, DATE_PATTERN_1);
		Date later = getNextDay(now, n);
		return format(later, DATE_PATTERN_1);
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
	
	
	
	
	/**
	 * 获取每个月dat日，如果dat>该月最大日期，则自动返回下个月1日
	 * @param 
	 * @param dat 日期
	 * @return
	 */
	public static String getDateByMM(String nowdate,String dat){
		String str = "";
		if(dat == null || "".equals(dat)){
			return str;
		}
		if(Integer.parseInt(dat)<0 || Integer.parseInt(dat)>31){
			return str;
		}
		int year = Integer.parseInt(nowdate.substring(0,4));
		int moth = Integer.parseInt(nowdate.substring(4,6));
		int day =  Integer.parseInt(nowdate.substring(6,8));
		int datint = Integer.parseInt(dat);
		if(day>=datint){
			moth = moth + 1; 
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,  year);
		calendar.set(Calendar.MONTH, moth-1);
		if(datint>calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
			moth = moth + 1;
			calendar.set(Calendar.MONTH, moth-1);
			calendar.set(Calendar.DATE,  1);	
		}else{
			calendar.set(Calendar.DATE,  datint);	
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_1);
		str = sdf.format(calendar.getTime());
		return str;
	}

	
	
	public static String getDateByWW(String nowdate,String dat){
		String str = "";
		if(dat == null || "".equals(dat)){
			return str;
		}
		if(Integer.parseInt(dat)<0 || Integer.parseInt(dat)>6){
			return str;
		}
		int year = Integer.parseInt(nowdate.substring(0,4));
		int moth = Integer.parseInt(nowdate.substring(4,6));
		int day =  Integer.parseInt(nowdate.substring(6,8));
		int datint = Integer.parseInt(dat);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,  year);
		calendar.set(Calendar.MONTH, moth-1);
		calendar.set(Calendar.DATE,  day);
		int weeknow = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if(weeknow == datint){
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+7);
		} else if(weeknow < datint){
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+(datint-weeknow));
		} else if(weeknow > datint){
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+7-(weeknow-datint));
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_1);
		str = sdf.format(calendar.getTime());
		return str;
	}
	
	public static String getDateByDD(String nowdate,String dat){
		String str = "";
		if(dat == null || "".equals(dat)){
			return str;
		}
		if(Integer.parseInt(dat)<0){
			return str;
		}
		int year = Integer.parseInt(nowdate.substring(0,4));
		int moth = Integer.parseInt(nowdate.substring(4,6));
		int day =  Integer.parseInt(nowdate.substring(6,8));
		int datint = Integer.parseInt(dat);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,  year);
		calendar.set(Calendar.MONTH, moth-1);
		calendar.set(Calendar.DATE,  day);
		calendar.set(Calendar.DATE,  calendar.get(Calendar.DATE)+datint);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_1);
		str = sdf.format(calendar.getTime());
		return str;
	}
	
	
	public static void main(String args[]){
		
//		Date date = DateUtil.parse("20150405120015", DateUtil.FULL_PATTERN_1);
//		
//		Date date1 = DateUtil.getNextDay(date, -10);
//		
//		int str1 = DateUtil.subDateToDay(date1, date);
//		System.out.println(str1);
//		System.out.println(DateUtil.getDateByMM("20150528", "29"));
//		DateUtil.getDateByWW("20150525","20150528", "7");
//		DateUtil.getDateByWW("20150526","20150528", "7");
//		DateUtil.getDateByWW("20150527","20150528", "7");
//		DateUtil.getDateByWW("20150528","20150528", "7");
//		DateUtil.getDateByWW("20150529","20150528", "7");
//		DateUtil.getDateByWW("20150530","20150528", "7");
//		DateUtil.getDateByWW("20150531","20150528", "7");
		System.out.println(DateUtil.getDateByDD("20150528", "1"));
		
	}
	
	
	
	
	
}
