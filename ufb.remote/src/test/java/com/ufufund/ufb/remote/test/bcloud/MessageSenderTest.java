package com.ufufund.ufb.remote.test.bcloud;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.remote.bcloud.MessageSender;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
@Slf4j
public class MessageSenderTest {
	
	@Autowired
	private MessageSender messageSender;

	@Test
	public void send(){
		
//		String[] mobiles = {"18817497875","18923777349","18602187732","18616502181","13611686341"};
//		String[] mobiles = {"18964714515,13875699439,13575298857"};
		String[] mobiles = {"18817497875"};
		
		String content = "亲爱的家长，您的验证码是123456，5分钟内有效。time:"
				+ DateUtil.format(new Date(), DateUtil.FULL_PATTERN_2);
		for(String m : mobiles){
			String msgid = messageSender.sendMessage(m, content);
			log.info("msgid="+msgid);
		}
	}
}
