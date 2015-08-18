package com.ufufund.ufb.biz.manager.op.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.op.AutotradeManagerOP;
import com.ufufund.ufb.model.enums.AutoTradeType;

import lombok.extern.slf4j.Slf4j;

@Service("testJob")
@Slf4j
public class TestJob {

	
	
	@Autowired
	private AutotradeManagerOP autotradeManagerOP;
	public void run(){
		
		log.info("this is a test job.....");
		autotradeManagerOP.startAutotrade(AutoTradeType.AUTORECHARGE, "20150824");
	}
}
