package com.ufufund.ufb.biz.manager.impl.helper;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.enums.TableName;
import com.ufufund.ufb.model.remote.hft.BankAuthRequest;
import com.ufufund.ufb.model.remote.hft.BankVeriRequest;
import com.ufufund.ufb.model.remote.hft.OpenAccountRequest;

@Service
public class CustManagerHelper {
	
	public Bankcardinfo toBankcardinfo(OpenAccountAction openAccountAction){
		
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo.setCustno(openAccountAction.getCustno());
		bankcardinfo.setBankno(openAccountAction.getBankno());
		bankcardinfo.setBankacco(openAccountAction.getBankacco());
		bankcardinfo.setBankidtp(openAccountAction.getBankidtp());
		bankcardinfo.setBankidno(openAccountAction.getBankidno());
		bankcardinfo.setBankacnm(openAccountAction.getBankacnm());
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
	
	public Custinfo toCustinfo(RegisterAction registerAction){
		
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(registerAction.getLoginCode());
		custinfo.setPasswd(registerAction.getLoginPassword());
		custinfo.setInvtp(registerAction.getInvtp().getValue());
		custinfo.setLevel(registerAction.getLevel().getValue());
		custinfo.setOrganization(registerAction.getOrganization());
		custinfo.setBusiness(registerAction.getBusiness());
		return custinfo;
	}

	public Custinfo toOpenAccountAction(OpenAccountAction openAccountAction){
		
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(openAccountAction.getCustno());
		custinfo.setInvnm(openAccountAction.getInvnm());
		custinfo.setIdno(openAccountAction.getIdno());
		custinfo.setTradepwd(openAccountAction.getTradepwd());
		custinfo.setInvtp(Invtp.PERSONAL.getValue());
		custinfo.setIdtp(openAccountAction.getBankidtp());
		custinfo.setOpenaccount("Y");
		return custinfo;
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
