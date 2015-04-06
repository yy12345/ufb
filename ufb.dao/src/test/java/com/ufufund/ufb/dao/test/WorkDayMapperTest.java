package com.ufufund.ufb.dao.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ufufund.ufb.dao.WorkDayMapper;
import com.ufufund.ufb.model.db.SysWorkDay;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-dao.xml" })
@TransactionConfiguration(defaultRollback = false)
public class WorkDayMapperTest {
	
	@Autowired
	private WorkDayMapper workDayMapper;

	@Test
	public void testGetSysTime(){
		
		String systime = workDayMapper.getSysTime();
		System.out.println(systime);
		Assert.assertEquals(14, systime.length());
	}
	
	@Test
	public void testIsWorkDay(){
		SysWorkDay sysWorkDay = workDayMapper.isWorkDay("20150405");
		System.out.println("isWorkDay:"+sysWorkDay);
	}
	
	@Test
	public void testGetCurrentWorkDay(){
		SysWorkDay sysWorkDay = workDayMapper.getCurrentWorkDay("20150405");
		System.out.println("getCurrentWorkDay:"+sysWorkDay);
	}
	
	@Test
	public void testGetWorkDayForward(){
		SysWorkDay sysWorkDay = workDayMapper.getCurrentWorkDay("20150405");
		sysWorkDay = workDayMapper.getWorkDayForward(sysWorkDay.getWorkdate(), 2);
		System.out.println("getWorkDayForward:"+sysWorkDay);
	}
	
	@Test
	public void testGetWorkDayBack(){
		SysWorkDay sysWorkDay = workDayMapper.getCurrentWorkDay("20150405");
		sysWorkDay = workDayMapper.getWorkDayBack(sysWorkDay.getWorkdate(), 2);
		System.out.println("getWorkDayBack:"+sysWorkDay);
	}
	
}
