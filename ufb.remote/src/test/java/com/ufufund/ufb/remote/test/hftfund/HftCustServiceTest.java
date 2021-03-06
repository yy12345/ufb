package com.ufufund.ufb.remote.test.hftfund;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.hftfund.BankAuthRequest;
import com.ufufund.ufb.model.hftfund.BankAuthResponse;
import com.ufufund.ufb.model.hftfund.BankVeriRequest;
import com.ufufund.ufb.model.hftfund.BankVeriResponse;
import com.ufufund.ufb.model.hftfund.OpenAccountOrgRequest;
import com.ufufund.ufb.model.hftfund.OpenAccountOrgResponse;
import com.ufufund.ufb.model.hftfund.OpenAccountRequest;
import com.ufufund.ufb.model.hftfund.OpenAccountResponse;
import com.ufufund.ufb.remote.hftfund.HftCustService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
public class HftCustServiceTest {
	private static Logger LOG = LoggerFactory.getLogger(HftCustServiceTest.class);
	
	@Autowired
	private HftCustService hftCustService;
	
//	@Test
	public void testBankAuth() throws UnsupportedEncodingException{
	
		BankAuthRequest request = new BankAuthRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BankAuth);
		request.setApplicationNo("20150411CC0001");
		request.setClearingAgencyCode("012");
		request.setAcctNameOfInvestorInClearingAgency("张帆女");
		request.setAcctNoOfInvestorInClearingAgency("6230201111200000");
		request.setCertificateType("0");
		request.setCertificateNo("210304198503040045");
		request.setMobileTelNo("15211827360");
		request.setAccoreqSerial("20150410CC0002");
		
		BankAuthResponse response = hftCustService.bankAuth(request);
		LOG.debug("返回对象:"+response.toString());
	}
	
//	@Test
	public void testBankVeri() throws UnsupportedEncodingException{
		
		BankVeriRequest request = new BankVeriRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BankVeri);
		request.setApplicationNo("20150410CC0002");
		request.setClearingAgencyCode("012");
		request.setAcctNameOfInvestorInClearingAgency("张帆女");
		request.setAcctNoOfInvestorInClearingAgency("6230201111200000");
		request.setCertificateType("0");
		request.setCertificateNo("210304198503040045");
		request.setMobileTelNo("15211827360");
		request.setMobileAuthCode("11110000");
		request.setOtherSerial("20150410CC0002");
		
		BankVeriResponse response = hftCustService.bankVeri(request);
		LOG.debug("返回对象:"+response.toString());
	}
	
	
	@Test
	public void testOpenAccount(){
		OpenAccountRequest request = new OpenAccountRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.OpenAccount);
		request.setApplicationNo("20150410CC0006");
		request.setClearingAgencyCode("012");
		request.setAcctNameOfInvestorInClearingAgency("张帆女");
		request.setAcctNoOfInvestorInClearingAgency("6230201111200002");
		request.setInvestorName("平心香");
		request.setCertificateType("0");
		request.setCertificateNo("632522198706178128");
		request.setCertValidDate("20181022");
		request.setEmailAddress("15211827360@163.com");
		request.setMobileTelNo("15211827360");
		request.setOfficeTelNo("02188592231");
		request.setFaxNo("02188592231");
		request.setAddress("东方路");
		request.setPostCode("200000");
		request.setProtocolNo("201207161634223760");
		
		OpenAccountResponse response = hftCustService.openAccount(request);
		LOG.debug("返回对象:"+response.toString());
	}
	
//	@Test
	public void testOpenAccountOrg(){
		OpenAccountOrgRequest request = new OpenAccountOrgRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.OpenAccountOrg);
		request.setApplicationNo("20150806CC0003");
		request.setClearingAgencyCode("012");
		request.setAcctNameOfInvestorInClearingAgency("孙桥小学");
		request.setAcctNoOfInvestorInClearingAgency("6230201120021011");
//		request.setProvince("32");
//		request.setCity("0581");
		request.setInvestorName("孙桥小学");
		request.setCertificateType("0");
		request.setCertificateNo("66251619X");
		request.setCertValidDate("20181022");
		request.setEmailAddress("15211827361@163.com");
		request.setMobileTelNo("15211827361");
		request.setOfficeTelNo("02188592231");
		request.setFaxNo("02188592231");
		request.setAddress("孙桥路");
		request.setPostCode("200000");
		request.setInstReprIDType("0");
		request.setInstReprIDCode("441622197811056389");
		request.setInstReprName("张薇");
		request.setControlHolder("张江市政府");
		request.setTransactorCertType("0");
		request.setTransactorCertNo("530923198408078324");
		request.setTransactorName("刘为");
		request.setInstTranCertValidDate("20180512");
		
		OpenAccountOrgResponse response = hftCustService.openAccountOrg(request);
	}
	
}
