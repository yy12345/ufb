package com.ufufund.trade.ufb.http.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.trade.ufb.http.AreaHttpService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
public class AreaHttpServiceTest {
	
	@Autowired
	private AreaHttpService areaHttpService;
	
	@Test
	public void testGetAreaTime(){
		
		String time = areaHttpService.getAreaTime("xxxx");
		Assert.assertNotNull(time);
		
	}

}
