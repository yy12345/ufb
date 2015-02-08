package com.ufufund.trade.ufb.biz.manager.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.trade.ufb.biz.manager.CustManager;
import com.ufufund.trade.ufb.model.db.Area;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring/spring-ufb-biz.xml" })
//@TransactionConfiguration(defaultRollback = false)
public class CustManagerTest {
	
	@Autowired
	private CustManager areaManager;
	
//	@Test
	public void testGetAreaByCustNo(){
		
		Area area = areaManager.getAreaByCustNo("c_01");
		Assert.assertEquals("a_02", area.getId());
	}
	
//	@Test
//	@Transactional
	public void testUpdateLocationByCustNo(){
		int n = areaManager.updateLocationByCustNo("c_01", "Dezhi Apartment");
		Assert.assertEquals(1, n);
	}

}
