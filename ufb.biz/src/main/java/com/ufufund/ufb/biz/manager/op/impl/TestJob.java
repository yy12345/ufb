package com.ufufund.ufb.biz.manager.op.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service("testJob")
@Slf4j
public class TestJob {

	public void run(){
		
		log.info("this is a test job.....");
	}
}
