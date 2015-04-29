package com.ufufund.trade.ufb.biz.manager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.enums.Level;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-biz.xml" })
@TransactionConfiguration(defaultRollback = false)
public class CustManagerTest {
	
	@Autowired
	private CustManager custManager;
	
	@Autowired
	private BankCardManager bankCardManager;
	
	@Autowired
	private CustinfoMapper custinfoMapper;
	
	
	/*
	 * 
	 * 测试testrisMobileRegister
	 */
	//@Test
	public void testrisMobileRegister(){
		
		custManager.isMobileRegister("1111");
	}

	
	/*
	 * 
	 * 测试个人注册
	 */
	//@Test
	public void testregister(){
		RegisterAction loginAction = new RegisterAction();
		loginAction.setLoginCode("13611686341");
		loginAction.setLoginPassword("123257");
		loginAction.setLoginPassword2("123257");
		custManager.register(loginAction);
	}
	
	
	/*
	 * 
	 * 测试机构注册
	 */
	//@Test
	public void testOrgregister(){
		RegisterAction loginAction = new RegisterAction();
		loginAction.setLoginCode("13611686342");
		loginAction.setLoginPassword("123257");
		loginAction.setLoginPassword2("123257");
		loginAction.setLevel(Level.OPERATOR);
		loginAction.setOrganization("Organization");
		loginAction.setBusiness("business");
		custManager.register(loginAction);
	}
	
	/*
	 *测试修改密码
	 */
	//@Test
	public void testupdateCustinfo() {
		ChangePasswordAction custinfo =new ChangePasswordAction();
		custinfo.setMobile("13611686341");
		custinfo.setLoginPassword("123256789");
		custinfo.setLoginPassword2("123256789");
		//areaManager.changePassword(custinfo);
	}
	
	/*
	 *登录测试
	 */
	//@Test
	public void testloginIn() {
		LoginAction loginAction  = new LoginAction();
		loginAction.setLoginCode("13611686341");
		loginAction.setLoginPassword("123257");
		//areaManager.loginIn(loginAction);
	}
	
	
	
	/*
	 *开户测试
	 */
	@Test
	public void testOpenAccountAction() {
		
		OpenAccountAction openAccountAction = new OpenAccountAction();
		openAccountAction.setCustno("CU2015040611112600000001");
		openAccountAction.setIdno("430726198602261338");
		openAccountAction.setInvnm("测试");
		openAccountAction.setTradepwd("1234567");
		openAccountAction.setTradepwd2("1234567");
		openAccountAction = bankCardManager.openAccount1(openAccountAction);
		openAccountAction.setBankno("002");
		openAccountAction.setBankacnm("尼玛");
		openAccountAction.setBankidtp("0");
		openAccountAction.setBankidno("123456789");
		openAccountAction.setBankacco("1234567890");
		openAccountAction.setBankmobile("13611686341");
		openAccountAction = bankCardManager.openAccount2(openAccountAction);
		openAccountAction.setMobileAutoCode("12345678");	
		openAccountAction = bankCardManager.openAccount3(openAccountAction);
		bankCardManager.openAccount4(openAccountAction);
		//areaManager.loginIn(loginAction);
	}
	
	
	
	
//	//@Test
//	public void testgetCustinfo(){
//		Custinfo custinfo = new Custinfo();
//		custinfo.setMobileno("13611686341");
//		custinfo = custinfoMapper.getCustinfo(custinfo);
//		boolean s =areaManager.isMobileRegister("13611686341");
//		System.out.println("----------1"+custinfo.toString());
//		System.out.println("----------2"+ s);
//	}
	
//	//@Test
//	public void testgetInsterCustinfo(){
//		Custinfo custinfo = new Custinfo();
//		custinfo.setCustno("2");
////		custinfo.setInvtp("3");
////		custinfo.setInvnm("4");
////		custinfo.setSex("5");
////		custinfo.setIdtp("6");
////		custinfo.setIdno("7");
////		custinfo.setMobileno("13611686341");
////		custinfo.setEmail("9");
////		custinfo.setCustst("a");
////		custinfo.setPasswd("b");
////		custinfo.setLastlogintime("9");
////		custinfo.setPasswderr(2);
////		//custinfo.setOpendt("c");
////		custinfo.setTradepwd("d");
//		custinfo.setMobileno("13611686341");
//		custinfo.setPasswd("1234335");
//		custinfo.setInvtp("0");
////		
//		custinfoMapper.insertCustinfo(custinfo);
//		
//	}
	
	

	
	
	
//	@Test
//	public void testDictManager(){
//		//LoginAction loginAction = new LoginAction();
//		
//		Dictionary dictionary = DictManager.getDict("111", "112");
//		System.out.println(dictionary.toString());
//		
//	}
	
//	//@Test
//	public void testgetUpdateCustinfo(){
//		Custinfo custinfo = new Custinfo();
//		
//		custinfo.setCustno("2");
//		custinfo.setInvtp("4");
//		custinfo.setInvnm("5");
//
//		custinfo.setSex("6");
//		custinfo.setIdtp("7");
//		custinfo.setIdno("8");
//
//		custinfo.setMobileno("9");
//		custinfo.setEmail("10");
//		custinfo.setCustst("A");
//
//
//		custinfo.setPasswd("12");
//		custinfo.setLastlogintime("20150315012253");
//		custinfo.setPasswderr(3);
//	
//		custinfo.setOpendt("B");
//		custinfo.setTradepwd("15");
//
//		custinfoMapper.updateCustinfo(custinfo);
//		
//	}
	
//	@Test
//	public void testgetCustinfoSequence(){	
//		String s =custinfoMapper.getCustinfoSequence();
//		System.out.println("----------------------aaaa---: "+ s);
//	}


}
