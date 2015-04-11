package com.ufufund.ufb.remote.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.remote.service.HftCustService;
import com.ufufund.ufb.remote.xml.pojo.BankAuthRequest;
import com.ufufund.ufb.remote.xml.pojo.BankAuthResponse;
import com.ufufund.ufb.remote.xml.pojo.BankVeriRequest;
import com.ufufund.ufb.remote.xml.pojo.BankVeriResponse;
import com.ufufund.ufb.remote.xml.pojo.OpenAccountRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
public class HftCustServiceTest {
	private static Logger LOG = LoggerFactory.getLogger(HftCustServiceTest.class);
	
	@Autowired
	private HftCustService hftCustService;
	
	@Test
	public void testBankAuth() throws UnsupportedEncodingException{
	
		BankAuthRequest request = new BankAuthRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BankAuth);
		request.setApplicationNo("20150410CC0001");
		request.setClearingAgencyCode("012");
		request.setAcctNameOfInvestorInClearingAgency("平心香");
		request.setAcctNoOfInvestorInClearingAgency("6230201111100000");
		request.setCertificateType("0");
		request.setCertificateNo("131003197904181707");
		request.setMobileTelNo("15211827360");
		request.setAccoreqSerial("0001");
		
		BankAuthResponse response = hftCustService.bankAuth(request);
		LOG.debug("返回对象:"+response.toString());
	}
	
	@Test
	public void testBankVeri() throws UnsupportedEncodingException{
		
		BankVeriRequest request = new BankVeriRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BankVeri);
		request.setApplicationNo("20150410CC0002");
		request.setClearingAgencyCode("012");
		request.setAcctNameOfInvestorInClearingAgency("平心香");
		request.setAcctNoOfInvestorInClearingAgency("6230201111100000");
		request.setCertificateType("0");
		request.setCertificateNo("131003197904181707");
		request.setMobileTelNo("15211827360");
		request.setMobileAuthCode("11110000");
		request.setOtherSerial("0001");
		
		BankVeriResponse response = hftCustService.bankVeri(request);
		LOG.debug("返回对象:"+response.toString());
	}
	
	
//	@Test
	public void testOpenAccount(){
		OpenAccountRequest request = new OpenAccountRequest();
	}
}
