//package com.ufufund.ufb.dao.test;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ufufund.ufb.dao.CustinfoMapper;
//import com.ufufund.ufb.model.Custinfo;
//
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(locations = { "classpath:spring/spring-ufb-dao.xml" })
////@TransactionConfiguration(defaultRollback = false)
//public class CustinfoMapperTest {
//
//	@Autowired
//	private CustinfoMapper custinfoMapper;
//	
//	
////	@Test
////	@Transactional
//	public void testAddCustinfo(){
//		
//		Custinfo custinfo = new Custinfo();
//		custinfo.setCustNo("c_01");
//		custinfo.setCustType("1");
//		custinfo.setAreaId("a_01");
//		
//		int n = custinfoMapper.addCustinfo(custinfo);
//		Assert.assertEquals(1, n);
//	}
//	
////	@Test
////	@Transactional
//	public void testUpdateAreaByCustNo(){
//		
//		int n = custinfoMapper.updateAreaByCustNo("c_01", "a_02");
//		Assert.assertEquals(1, n);
//	}
//	
////	@Test
//	public void testGetByCustNo(){
//		
//		Custinfo custinfo = custinfoMapper.getByCustNo("c_01");
//		Assert.assertEquals("c_01", custinfo.getCustNo());
//	}
//}
