package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.vo.Today;

/**
 * 工作日manager接口定义
 * @author ayis
 * 2015年4月5日
 */
public interface WorkDayManager {
	
	/**
	 * 获取系统时间：来源数据库
	 * @return
	 */
	public String getSysTime();
	
	/**
	 * 判断当前是否工作日
	 * @param day 日期
	 * @return
	 */
	public boolean isWorkDay();
	
	/**
	 * 判断指定日期是否工作日
	 * @param day 日期
	 * @return
	 */
	public boolean isWorkDay(String date);

	/**
	 * 获取当前工作日
	 * <br/>若15点以前（包括15点），则取当前自然日 的最近一个工作日；
	 * 		若15点后，则取下一自然的最近一个工作日
	 * @return
	 */
	public String getCurrentWorkDay();
	
	
	/**
	 * 获取当前工作日的后N个工作日
	 * <br/>若n大于或等于0，则为当前工作日后N个工作日；
	 * 		若n小于0，则为当前工作日的前N个工作日
	 * @param workday 必须为工作日，否则数据可能有误
	 * @param n 
	 * @return
	 */
	public String getNextWorkDay(String workday, int n);
	
	/**
	 * 取当前自然日后的下一个工作日
	 * @param day
	 * @return
	 */
	public String getNextWorkDay(String day);
	
	/**
	 * 获取当前工作日及申请时间信息
	 * @return
	 */
	public Today getSysDayInfo();
	
	
	
	
	
}
