package com.ufufund.ufb.biz.manager.op;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.enums.AutoTradeType;

public interface AutotradeManagerOP { //extends CustInterface{
	
	/*
	 * 启动自动交易任务
	 * 
	 */
	public void startAutotrade(AutoTradeType tradetype,String workDate) throws BizException;
	
	
	/*
	 * 终止自动交易任务
	 * 
	 */
	public void stopAutotrade(AutoTradeType tradetype,String workDate) throws BizException;
}
