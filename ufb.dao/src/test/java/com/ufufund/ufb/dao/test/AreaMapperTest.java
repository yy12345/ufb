//package com.ufufund.ufb.dao.test;
//
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
//import com.ufufund.ufb.dao.AreaMapper;
//import com.ufufund.ufb.model.Area;
//
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(locations = { "classpath:spring/spring-ufb-dao.xml" })
////@TransactionConfiguration(defaultRollback = false)
//public class AreaMapperTest {
//	
//	@Autowired
//	private AreaMapper areaMapper;
//
////	@Test
////	@Transactional
//	public void testAddArea(){
//		
//		Area area = new Area();
//		area.setId("a_02");
//		area.setProvince("Hunan");
//		area.setLocation("Tianma Apartment");
//		
//		int n = areaMapper.addArea(area);
//		Assert.assertEquals(1, n);
//	}
//	
////	@Test
////	@Transactional
//	public void testUpdateLocationById(){
//		
//		int n = areaMapper.updateLocationById("a_01", "East Huaxia Road");
//		Assert.assertEquals(1, n);
//	}
//	
////	@Test
//	public void testGetById(){
//		
//		Area area = areaMapper.getById("a_01");
//		Assert.assertEquals("a_01", area.getId());
//	}
//	
//	
//}
