package com.ufufund.ufb.biz.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.dao.WorkDayMapper;
import com.ufufund.ufb.model.db.SysWorkDay;
import com.ufufund.ufb.model.db.Today;

/**
 * 工作日manager接口定义
 * @author ayis
 * 2015年4月5日
 */
@Service
public class WorkDayManager{
	
	@Autowired
	private WorkDayMapper workDayMapper;

	/**
	 * 获取系统时间：来源数据库
	 * @return
	 */
	public String getSysTime() {
		return workDayMapper.getSysTime();
	}
	
	/**
	 * 判断当前是否工作日
	 * @param day 日期
	 * @return
	 */
	public boolean isWorkDay(){
		String systime = workDayMapper.getSysTime();
		SysWorkDay workday = workDayMapper.isWorkDay(systime.substring(0, 8));
		if(workday == null){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 判断指定日期是否工作日
	 * @param day 日期
	 * @return
	 */
	public boolean isWorkDay(String date) {
		SysWorkDay workday = workDayMapper.isWorkDay(date);
		if(workday == null){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 获取当前时间的工作日
	 * <br/>若15点以前（包括15点），则取当前自然日 的最近一个工作日；
	 * 		若15点后，则取下一自然的最近一个工作日
	 * @return
	 */
	public String getCurrentWorkDay() {
		String systime = workDayMapper.getSysTime();
		String date = dealNaturalDay(systime);
		return getWorkDay(date);
	}
	
	/**
	 * 获取自然日的最近一个工作日
	 * 备注：若自然日为工作日，则指15点以前的正式工作日
	 * @param date 自然日
	 * @return
	 */
	public String getWorkDay(String date) {
		SysWorkDay workday = workDayMapper.getCurrentWorkDay(date);
		return workday.getWorkdate();
	}
	
	/**
	 * 获取当前工作日的后N个工作日
	 * <br/>若n大于或等于0，则为当前工作日后N个工作日；
	 * 		若n小于0，则为当前工作日的前N个工作日
	 * @param workday 必须为工作日，否则数据可能有误
	 * @param n 
	 * @return
	 */
	public String getNextWorkDay(String workday, int n) {
		SysWorkDay sysWorkDay = null;
		if(n == 0){
			return workday;
		}else if(n > 0){
			sysWorkDay = workDayMapper.getWorkDayForward(workday, n);
		}else{
			sysWorkDay = workDayMapper.getWorkDayBack(workday, Math.abs(n));
		}
		return sysWorkDay.getWorkdate();
	}

	/**
	 * 获取当前工作日及申请时间信息
	 * @return
	 */
	public Today getSysDayInfo() {
		String systime = workDayMapper.getSysTime();
		String date = dealNaturalDay(systime);
		SysWorkDay workday = workDayMapper.getCurrentWorkDay(date);
		
		Today today = new Today();
		today.setDate(systime.substring(0, 8));
		today.setTime(systime.substring(8));
		today.setWorkday(workday.getWorkdate());
		return today;
	}
	
	/**
	 * 当前日期预处理
	 * @param systime 系统时间
	 * @return 若15点以前，则返回当前日；否则返回下一自然日
	 */
	private String dealNaturalDay(String systime){
		String date = systime.substring(0, 8);
		int hour = Integer.parseInt(systime.substring(8, 10));
		
		if(hour < 15){
			return date;
		}else{
			Date date1 = DateUtil.parse(systime, DateUtil.FULL_PATTERN_1);
			Date date2 = DateUtil.getNextDay(date1, 1);
			return DateUtil.format(date2, DateUtil.DATE_PATTERN_1);
		}
	}

	
}
