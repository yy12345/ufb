package com.ufufund.ufb.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.constant.Constant.DateFormat;
import com.ufufund.ufb.common.utils.bean.Today;

public class DateUtil {

	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
	
	public static long subDateToDay(Date d1,Date d2){
		long l = d1.getTime() - d2.getTime();
		long day=l/(24*60*60*1000);
		return day;
	}
	
	/**
	 * 统一返回现在的时间信息
	 * @param 
	 * @param model
	 * <br><b>作者： goodrich</b>
	 * <br>创建时间：2015-3-30
	 */
	public static Today getToday(){
		Date date = new Date();
		String day = getFormatDay(date,Constant.DateFormat.yyyyMMdd);
		String hhmmss = getFormatDay(date,Constant.DateFormat.HHmmss);
		Today today = new Today();
		today.setDay(day);
		today.setHhmmss(hhmmss);
		return today;
		
	}
	/**
	* 格式化日期
	 * Constant.DateFormat,各种格式
	 * @since
	 * @param format
	 * @return
	 * <br><b>作者： goodrich</b>
	 * <br>创建时间：2015-3-30
	 * @return
	 */
	public static String getFormatDay(DateFormat format){
		return getFormatDay(new Date(),format);
	}
	
	/**
	 * 通过字符串获取Date日期
	 * @since
	 * @param date
	 * @param format
	 * @return
	 * <br><b>作者： goodrich</b>
	 * <br>创建时间：2015-3-30
	 */
	public static Date getFormatDate(String date,DateFormat format){
		SimpleDateFormat source = new SimpleDateFormat(format.toString());
		Date newDate = null;
		try {
			newDate =source.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}
	
	public static String getFormatDay(Date date, DateFormat format){
		
		String str = "";
		if(date == null || "".equals(date)){
			return str;
		}
		SimpleDateFormat dest = new SimpleDateFormat(format.toString());
		
		str = dest.format(date);
		
		return str;
	}
	
	public static String getFormatDay(String date,DateFormat srv, DateFormat format){
		
		String str = "";
		if(date == null || "".equals(date)){
			return str;
		}
		SimpleDateFormat source = new SimpleDateFormat(srv.toString());
		
		try {
			str =getFormatDay(source.parse(date),format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	/**
	 * 获取格式化的时间
	 * @since
	 * @param time 时间格式
	 * @param sourceFormat
	 * @param destFormat
	 * @return
	 * <br><b>作者： goodrich</b>
	 * <br>创建时间：2015-3-30
	 */
	public static String getFormatTime(String time, String sourceFormat, String destFormat){
		
		String str= "";
		if(StringUtils.isBlank(time)){
			return str;
		}
		SimpleDateFormat source = new SimpleDateFormat(sourceFormat);
		SimpleDateFormat dest = new SimpleDateFormat(destFormat);
		
		try {
			str = dest.format(source.parse(time));
		} catch (ParseException e) {
			LOG.error("格式化时间出现异常！time="+time+",sourceFormat="+sourceFormat, e);
		}
		return str;
	}
	
	/**
	 * 转换日期格式
	 * @since
	 * @param date
	 * @param flag true：从yyyyMMdd转向yyyy-MM-dd
	 * @return
	 * <br><b>作者： goodrich</b>
	 * <br>创建时间：2015-3-30
	 */
	public static String convertDateFormat(String date, boolean flag) {
		
		String str = "";
		if (StringUtils.isBlank(date)) {
			return str;
		}
		
		SimpleDateFormat sf1 = new SimpleDateFormat(Constant.DateFormat.yyyyMMdd.toString());
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			if (flag) {
				
				str = sf2.format(sf1.parse(date));
			} else {
				str = sf1.format(sf2.parse(date));
			}
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
		
		return str;
	}
	
	/**
	 * 取当前日期的N天后
	 * @since
	 * @param date
	 * @return
	 * <br><b>作者： goodrich</b>
	 * <br>创建时间：2015-3-30
	 */
	public static Date getNextDay(Date date,int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		date = calendar.getTime();
		return date;
	}

}
