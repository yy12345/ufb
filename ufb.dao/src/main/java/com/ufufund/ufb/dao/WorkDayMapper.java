package com.ufufund.ufb.dao;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.SysWorkDay;

public interface WorkDayMapper {

	public String getSysTime();
	
	public SysWorkDay isWorkDay(String day);
	
	public SysWorkDay getCurrentWorkDay(String day);
	
	public SysWorkDay getWorkDayForward(@Param("workday")String workday, @Param("index")int index);
	
	public SysWorkDay getWorkDayBack(@Param("workday")String workday, @Param("index")int index);
}
