package com.ufufund.trade.ufb.file.quatz.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-ufb-file.xml"})
public class QuartzTest {
	
	@Test
	public void testQuartzJob(){
		
		System.out.println("started......................");
	}
}
