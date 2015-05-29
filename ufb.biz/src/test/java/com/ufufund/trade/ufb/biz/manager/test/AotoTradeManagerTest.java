package com.ufufund.trade.ufb.biz.manager.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.dao.AutotradeMapper;
import com.ufufund.ufb.dao.BankMapper;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.Level;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-biz.xml" })
@TransactionConfiguration(defaultRollback = false)
public class AotoTradeManagerTest {
	
	

	@Autowired
	private AutotradeManager autotradeManager;
	
	@Autowired
	private AutotradeMapper autotradeMapper;
	
	@Autowired
	private BankMapper bankMapper;
	
	
	@Autowired
	private TradeNotesMapper tradeNotesMapper;
//	@Autowired
//	private CustinfoMapper custinfoMapper;
//	
//	
	
	/*
	 * 
	 * 测试getAutotradeSequence
	 */
	//Test
	public void testrisMobileRegister(){
		System.out.println("autotradeMapper.getAutotradeSequence :" + autotradeMapper.getAutotradeSequence());
	}
	
	
	/*
	 * 
	 * 测试BankMapper
	 */
	//@Test
	public void testBankMapper(){
		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
		tradeaccoinfo.setBankserialid("BA150521170501RGNLSXWJP4");
		tradeaccoinfo.setFundcorpno("01");
		tradeaccoinfo = bankMapper.getTradeaccoinfo(tradeaccoinfo);
		System.out.println(tradeaccoinfo.toString());	
		//Tradeaccoinfo[accoid=45,custno=CU1505211705370DKHD5EA15,fundcorpno=01,bankserialid=BA150521170501RGNLSXWJP4,tradeacco=Tan20150521171201,tradeaccost=N,opendt=<null>,closedt=<null>]

	}
	
	
	//@Test
	public void insterTradeaccoinfo(){
			Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
			tradeaccoinfo.setCustno("1");// char(10) not null comment '客户编号',
			tradeaccoinfo.setFundcorpno("2");// char(2) not null default '' comment '交易账号类型：归属基金公司',
			tradeaccoinfo.setBankserialid("3");// varchar(24) not null comment '银行账号serialid(银行账号表pk)',
			tradeaccoinfo.setTradeacco("4");// varchar(17) not null comment '交易账号(基金公司返回的交易账号)',
			tradeaccoinfo.setTradeaccost("F");// char(1) not null comment '交易账号状态：n-正常 c-撤销',
			tradeaccoinfo.setOpendt("6");// char(8) default null comment '开户日期',
			tradeaccoinfo.setClosedt("7");// char(8) default null comment '销户日期',
			bankMapper.insterTradeaccoinfo(tradeaccoinfo);
			System.out.println(tradeaccoinfo.toString());	
			//Tradeaccoinfo[accoid=45,custno=CU1505211705370DKHD5EA15,fundcorpno=01,bankserialid=BA150521170501RGNLSXWJP4,tradeacco=Tan20150521171201,tradeaccost=N,opendt=<null>,closedt=<null>]

	}
	
	
	/*
	 * 
	 * 测试Fdacfinalresult
	 */
	//@Test
	public void testFdacfinalresult(){
		Fdacfinalresult action = new Fdacfinalresult();
		action.setSerialno("1");//varchar(24) not null comment '流水号',
		action.setCustno("2");//char(10) not null comment '客户编号',
		action.setApkind("3");//char(3) not null comment '业务代码',
		action.setWorkdate("4");// char(8) not null comment '工作日',
		action.setApdt("5");//char(8) not null comment '申请日期',
		
		action.setAptm("6");// char(6) not null comment '申请时间',
		action.setStatus("7");// char(1) not null comment '状态',	
		action.setFrombankserialid("8");// varchar(24) default null comment '银行卡id',
		action.setFromaccoid("9");// int(11) default null comment '交易账号编号',
		action.setFromtradeacco("A");// varchar(17) default null comment '交易账号',
		
		action.setFromfundcode("B");// varchar(6) default null comment '基金代码',
		action.setFromfundcorpno("C");// varchar(24) default null comment '归属基金公司',
		action.setFromchargetype("D");// varchar(1) default null comment 'A：前收费 B：后收费',
		action.setTobankserialid("E");// varchar(24) default null,
		action.setToaccoid("F");// int(11) default null,
		
		action.setTotradeacco("G");// varchar(17) default null,
		action.setTofundcode("H");// varchar(6) default null comment '基金代码',
		action.setTofundcorpno("I");// varchar(24) default null,
		action.setTochargetype("J");// varchar(1) default null,
		action.setAppamt(new BigDecimal(1));//` decimal(16,2) default null comment '申请金额',
		
		action.setAppvol(new BigDecimal(2));//` decimal(16,2) default null comment '申请份额',
		action.setAckamt(new BigDecimal(3));//` decimal(16,2) default null comment '确认金额',
		action.setAckvol(new BigDecimal(4));//` decimal(16,2) default null comment '确认份额',
		
		action.setAutoid("K");
		action.setOrserialno("N");
		tradeNotesMapper.insterFdacfinalresult(action);
//		System.out.println(tradeaccoinfo.toString());	
	}
	
	/*
	 * 
	 * 测试addAutotrade
	 */
	//@Test
	public void testaddAutotrade(){
		AddAutotradeAction action = new AddAutotradeAction();
		action.setAutoname("Autoname");// varchar(50) default '' comment '自动交易名称',
		action.setCustno("CU150525200833FWWIJWN8W1");// char(24) default '' comment '客户编号',
		action.setApkind(Apkind.AUTORECHARGE);// char(3) default null comment '业务类型', AUTO开头业务类型
		action.setType("S");// char(1) default null comment '类型 S单次，E多次',
		action.setCycle("MM");// char(2) default null comment 'MM=每月；WW=每周;DD 每隔多少天； 如果当天非工作日，自动推迟到下个工作日 ',
		action.setDat("30");// char(2) default null comment '扣款日',
		action.setFrombankserialid("BA150525200833N39QNSNNZ1");// char(24) default null comment '源银行卡id',
//		action.setFromfundcode("000002");// varchar(6) default null comment '源基金代码',
//		action.setFromfundcorpno("02");// char(24) default null comment '源归属基金公司',
//		action.setFromchargetype("B");// char(1) default null comment '源A：前收费 B：后收费',
//		action.setTobankserialid("XXX");// char(24) default null comment '目标银行卡id',
		action.setTofundcode("000001");// varchar(6) default null comment '目标基金代码',
		action.setTofundcorpno("01");// char(24) default null comment '目标归属基金公司',
		action.setTochargetype("A");// char(1) default null comment '目标A：前收费 B：后收费',
		action.setAutoamt(new BigDecimal(1000));// decimal(16,2) default null comment '金额',
//		action.setAutovol(new BigDecimal(2000));// decimal(16,2) default null comment '份额',
		action.setSummary("自动充值测试");// varchar(100) default null comment '备注',
		autotradeManager.addAutotrade(action);
//		System.out.println(tradeaccoinfo.toString());	
	}
	
	
	
	
//
//	
//	/*
//	 * 
//	 * 测试个人注册
//	 */
//	//@Test
//	public void testregister(){
//		RegisterAction loginAction = new RegisterAction();
//		loginAction.setLoginCode("13611686341");
//		loginAction.setLoginPassword("123257");
//		loginAction.setLoginPassword2("123257");
//		custManager.register(loginAction);
//	}
//	
//	
//	/*
//	 * 
//	 * 测试机构注册
//	 */
//	//@Test
//	public void testOrgregister(){
//		RegisterAction loginAction = new RegisterAction();
//		loginAction.setLoginCode("13611686342");
//		loginAction.setLoginPassword("123257");
//		loginAction.setLoginPassword2("123257");
//		loginAction.setLevel(Level.OPERATOR);
//		loginAction.setOrganization("Organization");
//		loginAction.setBusiness("business");
//		custManager.register(loginAction);
//	}
//	
//	/*
//	 *测试修改密码
//	 */
//	//@Test
//	public void testupdateCustinfo() {
//		ChangePasswordAction custinfo =new ChangePasswordAction();
//		custinfo.setMobile("13611686341");
//		//custinfo.setLoginPassword("123256789");
//		//custinfo.setLoginPassword2("123256789");
//		//areaManager.changePassword(custinfo);
//	}
//	
//	/*
//	 *登录测试
//	 */
//	//@Test
//	public void testloginIn() {
//		LoginAction loginAction  = new LoginAction();
//		loginAction.setLoginCode("13611686341");
//		loginAction.setLoginPassword("123257");
//		custManager.loginIn(loginAction);
//	}
//	
//	
//	
//	/*
//	 *开户测试
//	 */
//	//@Test
//	public void testOpenAccountAction() {
//		
//		OpenAccountAction openAccountAction = new OpenAccountAction();
//		openAccountAction.setCustno("CU2015040611112600000001");
//		openAccountAction.setIdno("430726198602261338");
//		openAccountAction.setInvnm("测试");
//		openAccountAction.setTradepwd("1234567");
//		openAccountAction.setTradepwd2("1234567");
//		openAccountAction = bankCardManager.openAccount1(openAccountAction);
//		openAccountAction.setBankno("002");
//		openAccountAction.setBankacnm("尼玛");
//		openAccountAction.setBankidtp("0");
//		openAccountAction.setBankidno("123456789");
//		openAccountAction.setBankacco("1234567890");
//		openAccountAction.setBankmobile("13611686341");
//		openAccountAction = bankCardManager.openAccount2(openAccountAction);
//		openAccountAction.setMobileAutoCode("12345678");	
//		openAccountAction = bankCardManager.openAccount3(openAccountAction);
//		bankCardManager.openAccount4(openAccountAction);
//		//areaManager.loginIn(loginAction);
//	}
//	
//	
//	
//	
////	//@Test
////	public void testgetCustinfo(){
////		Custinfo custinfo = new Custinfo();
////		custinfo.setMobileno("13611686341");
////		custinfo = custinfoMapper.getCustinfo(custinfo);
////		boolean s =areaManager.isMobileRegister("13611686341");
////		System.out.println("----------1"+custinfo.toString());
////		System.out.println("----------2"+ s);
////	}
//	
////	//@Test
////	public void testgetInsterCustinfo(){
////		Custinfo custinfo = new Custinfo();
////		custinfo.setCustno("2");
//////		custinfo.setInvtp("3");
//////		custinfo.setInvnm("4");
//////		custinfo.setSex("5");
//////		custinfo.setIdtp("6");
//////		custinfo.setIdno("7");
//////		custinfo.setMobileno("13611686341");
//////		custinfo.setEmail("9");
//////		custinfo.setCustst("a");
//////		custinfo.setPasswd("b");
//////		custinfo.setLastlogintime("9");
//////		custinfo.setPasswderr(2);
//////		//custinfo.setOpendt("c");
//////		custinfo.setTradepwd("d");
////		custinfo.setMobileno("13611686341");
////		custinfo.setPasswd("1234335");
////		custinfo.setInvtp("0");
//////		
////		custinfoMapper.insertCustinfo(custinfo);
////		
////	}
//	
//	
//
//	
//	
//	
////	@Test
////	public void testDictManager(){
////		//LoginAction loginAction = new LoginAction();
////		
////		Dictionary dictionary = DictManager.getDict("111", "112");
////		System.out.println(dictionary.toString());
////		
////	}
//	
////	//@Test
////	public void testgetUpdateCustinfo(){
////		Custinfo custinfo = new Custinfo();
////		
////		custinfo.setCustno("2");
////		custinfo.setInvtp("4");
////		custinfo.setInvnm("5");
////
////		custinfo.setSex("6");
////		custinfo.setIdtp("7");
////		custinfo.setIdno("8");
////
////		custinfo.setMobileno("9");
////		custinfo.setEmail("10");
////		custinfo.setCustst("A");
////
////
////		custinfo.setPasswd("12");
////		custinfo.setLastlogintime("20150315012253");
////		custinfo.setPasswderr(3);
////	
////		custinfo.setOpendt("B");
////		custinfo.setTradepwd("15");
////
////		custinfoMapper.updateCustinfo(custinfo);
////		
////	}
//	
////	@Test
////	public void testgetCustinfoSequence(){	
////		String s =custinfoMapper.getCustinfoSequence();
////		System.out.println("----------------------aaaa---: "+ s);
////	}


}
