package com.ufufund.ufb.dao.test;


import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ufufund.ufb.dao.TradeRequestMapper;
import com.ufufund.ufb.model.db.TradeRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-dao.xml" })
@TransactionConfiguration(defaultRollback = false)
public class TradeRequestMapperTest {

//	@Autowired
	private TradeRequestMapper tradeRequestMapper;
	
//	@Test
	public void testGet(){
		String serialno = "SSSS00001111";
		TradeRequest request = tradeRequestMapper.getBySerialno("", serialno);
		System.out.println(request);
	}
	
//	@Test
	public void testAdd(){
		
		TradeRequest request = new TradeRequest();
		request.setSerialno("SSSS00001111");
		request.setCustno("CCCC00001111");
		request.setFundcorpno("01");
		request.setTradeacco("TTTT00001111");
		request.setAppdate("20150411");
		request.setApptime("223905");
		request.setWorkday("20150412");
		request.setApkind("021");
		request.setFundcode("001002");
		request.setAppamt(new BigDecimal("100.15"));
		request.setAppvol(new BigDecimal("100.15"));
		request.setState("N");
		request.setTranst("N");
		request.setPayst("N");
		request.setShareclass("0");
		request.setDividmethod("0");
		request.setFee(new BigDecimal("1.15"));
		request.setReferno("RRRR00001111");
		
		int n = tradeRequestMapper.add(request);
		Assert.assertEquals(1, n);
		
	}
	
//	@Test
	public void testUpdate(){
		
		TradeRequest request = new TradeRequest();
		request.setSerialno("SSSS00001111");
		request.setSheetserialno("SHEE00001111");
		request.setAckdate("20150413");
		request.setAckamt(new BigDecimal("100.15"));
		request.setAckvol(new BigDecimal("100.15"));
		request.setState("Y");
		request.setTranst("Y");
		request.setPayst("Y");
		
		int n = tradeRequestMapper.update(request);
		Assert.assertEquals(1, n);
	}
}
