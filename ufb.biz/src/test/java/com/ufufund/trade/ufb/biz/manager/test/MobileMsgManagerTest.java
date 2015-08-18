package com.ufufund.trade.ufb.biz.manager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.biz.manager.MobileMsgManager;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-biz.xml" })
@Slf4j
public class MobileMsgManagerTest {

	@Autowired
	private MobileMsgManager mobileMsgManager;
	
	@Test
	public void sendMsg(){
		
		String custno = "CU20150811SFJ6WSWT660BS1";
//		String mobile = "18817497875";
		String content = "亲爱的用户，欢饮来的幼富通平台测试！";
		
		for(int i=0; i < 1; i++){
			String serialno = mobileMsgManager.sendMessage(custno, content);
			log.debug("serialno="+serialno);
		}
	}
}
