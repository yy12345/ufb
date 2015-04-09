package com.ufufund.ufb.remote.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.remote.service.HftCustService;
import com.ufufund.ufb.remote.xml.pojo.BankAuthRequest;

public class HftCustServiceTest {

	@Autowired
	private HftCustService hftCustService;
	
	@Test
	public void testBankAuth(){
		
		BankAuthRequest request = new BankAuthRequest();
		
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		
		
	}
	
	@Test
	public void testBankVeri(){
		
	}
	
	
	@Test
	public void testOpenAccount(){
		
	}
}
