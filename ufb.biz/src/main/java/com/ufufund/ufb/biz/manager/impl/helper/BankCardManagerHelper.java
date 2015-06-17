package com.ufufund.ufb.biz.manager.impl.helper;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.TableName;
import com.ufufund.ufb.model.hft.BankAuthRequest;
import com.ufufund.ufb.model.hft.BankVeriRequest;
import com.ufufund.ufb.model.hft.OpenAccountRequest;

@Service
public class BankCardManagerHelper {
	
	public Bankcardinfo toBankcardinfo(OpenAccountAction openAccountAction){
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo.setCustno(openAccountAction.getCustno());
		bankcardinfo.setBankno(openAccountAction.getBankno());
		bankcardinfo.setBankacco(openAccountAction.getBankacco());
		bankcardinfo.setBankidtp(openAccountAction.getBankidtp());
		bankcardinfo.setBankidno(openAccountAction.getBankidno());
		bankcardinfo.setBankacnm(openAccountAction.getBankacnm());
		bankcardinfo.setBankmobile(openAccountAction.getBankmobile());
		return bankcardinfo;
	}
	
	public Changerecordinfo toBankcardinfo(Bankcardinfo bankcardinfo){
		Changerecordinfo changerecordinfo = new Changerecordinfo();
		changerecordinfo.setCustno(bankcardinfo.getCustno());
		changerecordinfo.setRecordafter(bankcardinfo.toString());
		changerecordinfo.setTablename(TableName.BANKCARDINFO.value());
		return changerecordinfo;
	}
	
	
	public Changerecordinfo toTradeaccoinfo(Tradeaccoinfo tradeaccoinfo ){
		Changerecordinfo changerecordinfo = new Changerecordinfo();
		changerecordinfo.setCustno(tradeaccoinfo.getCustno());
		changerecordinfo.setRecordafter(tradeaccoinfo.toString());
		changerecordinfo.setTablename(TableName.TRADEACCOINFO.value());
		return changerecordinfo;
	}
	
	public BankAuthRequest toBankAuthRequest(OpenAccountAction openAccountAction) {
		BankAuthRequest req = new BankAuthRequest();
		req.setVersion(Constant.HftSysConfig.Version);
		req.setMerchantId(Constant.HftSysConfig.MerchantId);
		req.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		req.setBusinType(Constant.HftBusiType.BankAuth);
		req.setApplicationNo(openAccountAction.getSerialno());
		req.setClearingAgencyCode("012");
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());
		req.setCertificateType(openAccountAction.getBankidtp());
		req.setCertificateNo(openAccountAction.getBankidno());
		req.setMobileTelNo(openAccountAction.getBankmobile());
		req.setAccoreqSerial(openAccountAction.getAccoreqSerial());
		return req;
	}

	public BankVeriRequest toBankVeriRequest(OpenAccountAction openAccountAction) {
		BankVeriRequest req = new BankVeriRequest();
		req.setVersion(Constant.HftSysConfig.Version);
		req.setMerchantId(Constant.HftSysConfig.MerchantId);
		req.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		req.setBusinType(Constant.HftBusiType.BankVeri);
		req.setApplicationNo(openAccountAction.getSerialno());
		req.setClearingAgencyCode("012");
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());
		req.setCertificateType(openAccountAction.getBankidtp());
		req.setCertificateNo(openAccountAction.getBankidno());
		req.setMobileTelNo(openAccountAction.getBankmobile());
		req.setMobileAuthCode(openAccountAction.getMobileAutoCode());
		req.setOtherSerial(openAccountAction.getOtherserial());
		return req;
	}

	public OpenAccountRequest toOpenAccountRequest(OpenAccountAction openAccountAction) {
		OpenAccountRequest req = new OpenAccountRequest();
		req.setVersion(Constant.HftSysConfig.Version);
		req.setMerchantId(Constant.HftSysConfig.MerchantId);
		req.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		req.setBusinType(Constant.HftBusiType.OpenAccount);
		req.setApplicationNo(openAccountAction.getSerialno());
		req.setClearingAgencyCode("012");
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());
		req.setInvestorName(openAccountAction.getBankacnm());
		req.setCertificateType(openAccountAction.getBankidtp());
		req.setCertificateNo(openAccountAction.getBankidno());
		req.setMobileTelNo(openAccountAction.getBankmobile());
//		req.setCertValidDate("20181022");
//		req.setEmailAddress("15211827360@163.com");
//		req.setOfficeTelNo("02188592231");
//		req.setFaxNo("02188592231");
//		req.setAddress("东方路");
//		req.setPostCode("200000");
		req.setProtocolNo(openAccountAction.getProtocolno());
		return req;
	}
}
