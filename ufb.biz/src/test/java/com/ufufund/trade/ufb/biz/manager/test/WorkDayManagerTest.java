package com.ufufund.trade.ufb.biz.manager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.biz.manager.WorkDayManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-biz.xml" })
public class WorkDayManagerTest {

	@Autowired
	private WorkDayManager workDayManager;
	
	/**
	 * 获取系统时间：来源数据库
	 * @return
	 */
	@Test
	public void getSysTime(){
		String systime = workDayManager.getSysTime();
		System.out.println("getSysTime:"+systime);
	}
	
	/**
	 * 判断当前是否工作日
	 * @param day 日期
	 * @return
	 */
//	@Test
	public void isWorkDay(){
		boolean result = workDayManager.isWorkDay();
		System.out.println("isWorkDay:"+result);
	}
	
	/**
	 * 判断指定日期是否工作日
	 * @param day 日期
	 * @return
	 */
//	@Test
	public void isWorkDay2(){
		boolean result = workDayManager.isWorkDay("20150403");
		System.out.println("isWorkDay2:"+result);
	}

	/**
	 * 获取当前工作日
	 * <br/>若15点以前（包括15点），则取当前自然日 的最近一个工作日；
	 * 		若15点后，则取下一自然的最近一个工作日
	 * @return
	 */
//	@Test
	public void getCurrentWorkDay(){
		String workday = workDayManager.getCurrentWorkDay();
		System.out.println("getCurrentWorkDay:"+workday);
	}
	
	
	/**
	 * 获取当前工作日的后N个工作日
	 * <br/>若n大于或等于0，则为当前工作日后N个工作日；
	 * 		若n小于0，则为当前工作日的前N个工作日
	 * @param workday 必须为工作日，否则数据可能有误
	 * @param n 
	 * @return
	 */
//	@Test
	public void getNextWorkDay(){
		String workday = workDayManager.getNextWorkDay("20150407", 0);
		System.out.println("getNextWorkDay:"+workday);
	}
}
