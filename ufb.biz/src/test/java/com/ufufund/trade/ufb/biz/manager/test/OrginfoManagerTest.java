package com.ufufund.trade.ufb.biz.manager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ufufund.ufb.biz.manager.OrginfoManager;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.model.db.Orginfo;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-biz.xml" })
@Slf4j
public class OrginfoManagerTest {
	
	@Autowired
	private OrginfoManager orginfoManager;
	
	@Test
	public void addOrginfo(){
		Orginfo org = new Orginfo();
		org.setOrgid(SequenceUtil.getSerial());
		org.setOrgname("孙桥幼儿园");
		org.setType("1");
		org.setLicense("3414141414");
		org.setProvince("上海");
		org.setCity("上海");
		org.setAddress("上海孙桥");
		org.setOperator_name("张丽");
		org.setOperator_certno("333333333333333333");
		org.setOperator_mobile("18744013149");
		org.setOperator_tel("333141412");
		org.setLegal_name("张丽");
		org.setLegal_certno("223242441414");
		org.setState("N");
		org.setPasswd(EncryptUtil.md5("123321"));
		org.setTradepwd(EncryptUtil.md5("123321"));
		log.info("org=="+org);
		orginfoManager.addOrginfo(org);
	}

}
