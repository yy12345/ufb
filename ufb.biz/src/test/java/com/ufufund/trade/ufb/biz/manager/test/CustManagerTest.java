package com.ufufund.trade.ufb.biz.manager.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.model.db.Custinfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-biz.xml" })
@TransactionConfiguration(defaultRollback = false)
public class CustManagerTest {
	
	@Autowired
	private CustManager areaManager;
	
	@Autowired
	private CustinfoMapper custinfoMapper;
	
	@Test
	public void testgetCustinfo(){
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno("13611686341");
		custinfo = custinfoMapper.getCustinfo(custinfo);
		boolean s =areaManager.isMobileRegister("13611686341");
		System.out.println("----------1"+custinfo.toString());
		System.out.println("----------2"+ s);
	}
	
	//@Test
	public void testgetInsterCustinfo(){
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno("2");
		custinfo.setInvtp("3");
		custinfo.setInvnm("4");
		custinfo.setSex("5");
		custinfo.setIdtp("6");
		custinfo.setIdno("7");
		custinfo.setMobileno("8");
		custinfo.setEmail("9");
		custinfo.setCustst("a");
		custinfo.setPasswd("b");
		custinfo.setLastlogintime("9");
		custinfo.setPasswderr(2);
		custinfo.setOpendt("c");
		custinfo.setTradepwd("d");
		custinfoMapper.insertCustinfo(custinfo);
		
	}
	
	//@Test
	public void testgetUpdateCustinfo(){
		Custinfo custinfo = new Custinfo();
		
		custinfo.setCustno("2");
		custinfo.setInvtp("4");
		custinfo.setInvnm("5");

		custinfo.setSex("6");
		custinfo.setIdtp("7");
		custinfo.setIdno("8");

		custinfo.setMobileno("9");
		custinfo.setEmail("10");
		custinfo.setCustst("A");


		custinfo.setPasswd("12");
		custinfo.setLastlogintime("20150315012253");
		custinfo.setPasswderr(3);
	
		custinfo.setOpendt("B");
		custinfo.setTradepwd("15");

		custinfoMapper.updateCustinfo(custinfo);
		
	}
	
	@Test
	public void testgetCustinfoSequence(){	
		String s =custinfoMapper.getCustinfoSequence();
		System.out.println("----------------------aaaa---: "+ s);
	}


}
