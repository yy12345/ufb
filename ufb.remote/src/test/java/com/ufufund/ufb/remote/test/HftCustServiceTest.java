package com.ufufund.ufb.remote.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.remote.service.HftCustService;
import com.ufufund.ufb.remote.xml.pojo.BankAuthRequest;
import com.ufufund.ufb.remote.xml.pojo.BankAuthResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
public class HftCustServiceTest {

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
		request.setAcctNameOfInvestorInClearingAgency("pingxinxiang");
		request.setAcctNoOfInvestorInClearingAgency("6230201111100000");
		request.setCertificateType("0");
		request.setCertificateNo("131003197904181707");
		request.setMobileTelNo("18817497875");
		
		BankAuthResponse response = hftCustService.bankAuth(request);
		
		System.out.println(response);
	}
	
//	@Test
	public void testBankVeri(){
		
	}
	
	
//	@Test
	public void testOpenAccount(){
		
	}
}
