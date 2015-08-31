package com.ufufund.ufb.biz.manager.op.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.op.AutotradeManagerOP;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.vo.Today;

import lombok.extern.slf4j.Slf4j;

@Service("testJob")
@Slf4j
public class TestJob {

	@Autowired
	private WorkDayManager workDayManager;
	
	@Autowired
	private AutotradeManagerOP autotradeManagerOP;
	public void run(){
		
		log.info("自动充值引擎启动...");
		Today today = workDayManager.getSysDayInfo();
		autotradeManagerOP.startAutotrade(AutoTradeType.AUTORECHARGE, today.getWorkday());
		//autotradeManagerOP.startAutotrade(AutoTradeType.AUTOWITHDRAWAL, today.getWorkday());
	}
	public void cashRun(){
		
		log.info("自动取现引擎启动...");
		Today today = workDayManager.getSysDayInfo();
		autotradeManagerOP.startAutotrade(AutoTradeType.AUTOWITHDRAWAL, today.getWorkday());
	}
}
