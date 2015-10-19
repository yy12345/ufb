package com.ufufund.ufb.biz.manager.impl.helper;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.TableName;
import com.ufufund.ufb.model.hftfund.BankAuthRequest;
import com.ufufund.ufb.model.hftfund.BankVeriRequest;
import com.ufufund.ufb.model.hftfund.OpenAccountOrgRequest;
import com.ufufund.ufb.model.hftfund.OpenAccountRequest;

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
		bankcardinfo.setBankcitynm(openAccountAction.getBankcitynm());
		bankcardinfo.setBankprovincenm(openAccountAction.getBankprovincenm());
		bankcardinfo.setBankadd(openAccountAction.getBankadd());
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
		req.setClearingAgencyCode(openAccountAction.getBankno());
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());
		req.setCertificateType(openAccountAction.getBankidtp());
		req.setCertificateNo(openAccountAction.getBankidno());
		req.setMobileTelNo(openAccountAction.getBankmobile());
		req.setAccoreqSerial(openAccountAction.getAccoreqserial());
		return req;
	}

	public BankVeriRequest toBankVeriRequest(OpenAccountAction openAccountAction) {
		BankVeriRequest req = new BankVeriRequest();
		req.setVersion(Constant.HftSysConfig.Version);
		req.setMerchantId(Constant.HftSysConfig.MerchantId);
		req.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		req.setBusinType(Constant.HftBusiType.BankVeri);
		req.setApplicationNo(openAccountAction.getSerialno());
		req.setClearingAgencyCode(openAccountAction.getBankno());
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());
		req.setCertificateType(openAccountAction.getBankidtp());
		req.setCertificateNo(openAccountAction.getBankidno());
		req.setMobileTelNo(openAccountAction.getBankmobile());
		req.setMobileAuthCode(openAccountAction.getMobileautocode());
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
		req.setClearingAgencyCode(openAccountAction.getBankno());
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
	
	public OpenAccountOrgRequest toOpenAccountOrgRequest(OpenAccountAction openAccountAction) {
		
		OpenAccountOrgRequest request = new OpenAccountOrgRequest();
		
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.OpenAccountOrg);
		request.setApplicationNo(openAccountAction.getSerialno());
		request.setClearingAgencyCode(openAccountAction.getBankno());
		request.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());
		request.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());
		request.setProvince("320");
		request.setCity("581");
		request.setInvestorName(openAccountAction.getBankacnm());//孙桥小学
		request.setCertificateType(openAccountAction.getBankidtp());//0
		request.setCertificateNo(openAccountAction.getBankidno());//66251638X
		request.setCertValidDate("20181022");
		request.setEmailAddress("15211827361@163.com");
		request.setMobileTelNo(openAccountAction.getBankmobile());//15211827361
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
		return request;
	}
}
