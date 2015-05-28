package com.ufufund.ufb.biz.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.dao.WorkDayMapper;
import com.ufufund.ufb.model.db.SysWorkDay;
import com.ufufund.ufb.model.vo.Today;

@Service
public class WorkDayManagerImpl implements WorkDayManager{
	
	@Autowired
	private WorkDayMapper workDayMapper;

	@Override
	public String getSysTime() {
		return workDayMapper.getSysTime();
	}
	
	@Override
	public boolean isWorkDay(){
		String systime = workDayMapper.getSysTime();
		SysWorkDay workday = workDayMapper.isWorkDay(systime.substring(0, 8));
		if(workday == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean isWorkDay(String date) {
		SysWorkDay workday = workDayMapper.isWorkDay(date);
		if(workday == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public String getCurrentWorkDay() {
		String systime = workDayMapper.getSysTime();
		String date = dealNaturalDay(systime);
		SysWorkDay workday = workDayMapper.getCurrentWorkDay(date);
		return workday.getWorkdate();
	}
	
	@Override
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
	
	@Override
	public String getNextWorkDay(String day){
		if(isWorkDay(day)){
			return getNextWorkDay(day, 1);
		}else{
			return getCurrentWorkDay();
		}
	}

	@Override
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
