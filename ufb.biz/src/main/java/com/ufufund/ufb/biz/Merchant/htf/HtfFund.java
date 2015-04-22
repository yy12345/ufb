package com.ufufund.ufb.biz.Merchant.htf;

import org.springframework.beans.factory.annotation.Autowired;

import com.ufufund.ufb.biz.Merchant.MerchantFund;
import com.ufufund.ufb.biz.Merchant.OpenAccount;
import com.ufufund.ufb.biz.manager.DictManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.Dictionary;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.remote.hft.BankAuthRequest;
import com.ufufund.ufb.model.remote.hft.BankAuthResponse;
import com.ufufund.ufb.model.remote.hft.BankVeriRequest;
import com.ufufund.ufb.model.remote.hft.BankVeriResponse;
import com.ufufund.ufb.model.remote.hft.OpenAccountRequest;
import com.ufufund.ufb.model.remote.hft.OpenAccountResponse;
import com.ufufund.ufb.remote.HftCustService;

public class HtfFund extends MerchantFund {

	@Autowired
	private HftCustService hftCustService;
	
	@Override
	public OpenAccount bankAuth(Object obj) {
		OpenAccountAction openAccountAction = (OpenAccountAction) obj;
		BankAuthRequest bankAuthRequest = convertBankAuthRequest(openAccountAction);
		BankAuthResponse bankAuthResponse = null;
		if(isTest){
			bankAuthResponse = hftCustService.bankAuth(bankAuthRequest);	
		}else{
			/*
			 * 模拟器
			 */
			bankAuthResponse = new BankAuthResponse();
			bankAuthResponse.setReturnCode("0000");
			bankAuthResponse.setOtherSerial("OtherSerial1");
			bankAuthResponse.setProtocolNo("ProtocolNo1");
			bankAuthResponse.setReturnMsg("ReturnMsg");
		}
		OpenAccount openAccount = new OpenAccount();
		Dictionary dictionary =  DictManager.getDict(Constant.DICTIONARY$HTFERROR, bankAuthResponse.getReturnCode());
		if(dictionary!=null){
			openAccount.setReturncode(dictionary.getPmnm());
		}else{
			openAccount.setReturncode(bankAuthResponse.getReturnCode());
		}		
		openAccount.setReturnMsg(bankAuthResponse.getReturnMsg());
		openAccount.setOtherserial(bankAuthResponse.getOtherSerial());
		openAccount.setProtocolno(bankAuthResponse.getProtocolNo());
		return openAccount;
	}
	
	
	@Override
	public OpenAccount bankVeri(Object obj) {
		OpenAccountAction openAccountAction = (OpenAccountAction) obj;
		BankVeriRequest bankVeriRequest =  convertBankVeriRequest(openAccountAction);
		BankVeriResponse bankVeriResponse = null;
		if(!isTest){
			bankVeriResponse = hftCustService.bankVeri(bankVeriRequest);	
		}else{
			/*
			 * 模拟器
			 */
			bankVeriResponse = new BankVeriResponse();
			bankVeriResponse.setReturnCode("0000");
			bankVeriResponse.setValidateState("1");
		}
		OpenAccount openAccount = new OpenAccount();
		Dictionary dictionary =  DictManager.getDict(Constant.DICTIONARY$HTFERROR, bankVeriResponse.getReturnCode());
		if(dictionary!=null){
			openAccount.setReturncode(dictionary.getPmnm());
		}else{
			openAccount.setReturncode(bankVeriResponse.getReturnCode());
		}		
		openAccount.setReturnMsg(bankVeriResponse.getReturnMsg());
		if(bankVeriResponse.getValidateState()==null||!"1".equals(bankVeriResponse.getValidateState())){
			/*
			 * 鉴权失败返回码
			 */
			openAccount.setReturncode("9999");
		}
		return openAccount;
	}
	
	@Override
	public OpenAccount openAccount(Object obj) {
		OpenAccountAction openAccountAction = (OpenAccountAction) obj;
		OpenAccountRequest openAccountRequest =  convertOpenAccountRequest(openAccountAction);
		OpenAccountResponse openAccountResponse = null;
		if(!isTest){
			openAccountResponse = hftCustService.openAccount(openAccountRequest);		
		}else{
			/*
			 * 模拟器
			 */
			openAccountResponse = new OpenAccountResponse();
			openAccountResponse.setReturnCode("0000");
			openAccountResponse.setTransactionAccountID("AccountID");
		}
		OpenAccount openAccount = new OpenAccount();
		Dictionary dictionary =  DictManager.getDict(Constant.DICTIONARY$HTFERROR, openAccountResponse.getReturnCode());
		if(dictionary!=null){
			openAccount.setReturncode(dictionary.getPmnm());
		}else{
			openAccount.setReturncode(openAccountResponse.getReturnCode());
		}		
		openAccount.setReturnMsg(openAccountResponse.getReturnMsg());
		openAccount.setTransactionAccountID(openAccountResponse.getTransactionAccountID());
		return openAccount;
	}
	
	
	
	
	
	
	private static BankAuthRequest convertBankAuthRequest(OpenAccountAction openAccountAction){
		BankAuthRequest req = new BankAuthRequest();
		/*
		 *  目前写死 后面从字典转换
		 */
		req.setVersion("");//版本号 
		req.setMerchantId("");//机构标识  
		req.setDistributorCode("");// 销售人代码	
		Dictionary dictionary3 =  DictManager.getDict(Constant.DICTIONARY$HFTAPKIND, Apkind.SWIFTAUTH.getValue());
		req.setBusinType(dictionary3.getPmnm());//业务类型  
		req.setApplicationNo(openAccountAction.getSerialno());//合作平台申请单编号
		req.setExtension(null);
		Dictionary dictionary =  DictManager.getDict(Constant.DICTIONARY$HFTBANKNO, openAccountAction.getBankno());
		req.setClearingAgencyCode(dictionary.getPmnm());//A	9	投资人收款银行账户开户行	
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());//	C	60	投资人收款银行账户户名	
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());//	C	28	投资人收款银行账户账号	
		Dictionary dictionary2 =  DictManager.getDict(Constant.DICTIONARY$HFTIDTP, openAccountAction.getBankidtp());
		req.setCertificateType(dictionary2.getPmnm());//	C	1	机构证件类型	
												/*0-身份证，1-护照
												2-军官证，3-士兵证
												4-港澳居民来往内地通行证，5-户口本
												6-外国护照，7-其它
												8-文职证，9-警官证
												A-台胞证*/
		req.setCertificateNo(openAccountAction.getBankidno());//	C	30	投资人证件号码	
		req.setMobileTelNo(openAccountAction.getBankmobile());//C	24	投资人手机号码	
		req.setApplicationNo(openAccountAction.getAccoreqSerial());
		return req;
	}


	

	
	
	
	private static BankVeriRequest convertBankVeriRequest(OpenAccountAction openAccountAction){
		BankVeriRequest req = new BankVeriRequest();
		/*
		 *  目前写死 后面从字典转换
		 */
		req.setVersion("");//版本号 
		req.setMerchantId("");//机构标识  
		req.setDistributorCode("");// 销售人代码	
		Dictionary dictionary3 =  DictManager.getDict(Constant.DICTIONARY$HFTAPKIND, Apkind.SWIFTVERIFY.getValue());
		req.setBusinType(dictionary3.getPmnm());//业务类型  
		req.setApplicationNo(openAccountAction.getSerialno());//合作平台申请单编号
		req.setExtension(null);
		Dictionary dictionary =  DictManager.getDict(Constant.DICTIONARY$HFTBANKNO, openAccountAction.getBankno());
		req.setClearingAgencyCode(dictionary.getPmnm());//A	9	投资人收款银行账户开户行	
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());//	C	60	投资人收款银行账户户名	
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());//	C	28	投资人收款银行账户账号	
		Dictionary dictionary2 =  DictManager.getDict(Constant.DICTIONARY$HFTIDTP, openAccountAction.getBankidtp());
		req.setCertificateType(dictionary2.getPmnm());//	C	1	机构证件类型	
												/*0-身份证，1-护照
												2-军官证，3-士兵证
												4-港澳居民来往内地通行证，5-户口本
												6-外国护照，7-其它
												8-文职证，9-警官证
												A-台胞证*/
		req.setCertificateNo(openAccountAction.getBankidno());//	C	30	投资人证件号码	
		req.setMobileTelNo(openAccountAction.getBankidno());//C	24	投资人手机号码	
		req.setMobileAuthCode(openAccountAction.getMobileAutoCode());
		req.setOtherSerial(openAccountAction.getOtherserial());
		req.setAccoreqSerial(openAccountAction.getAccoreqSerial());
		return req;
	}

	public static OpenAccountRequest convertOpenAccountRequest(OpenAccountAction openAccountAction){
		OpenAccountRequest req = new OpenAccountRequest();
		/*
		 *  目前写死 后面从字典转换
		 */
		req.setVersion("");//版本号 
		req.setMerchantId("");//机构标识  
		req.setDistributorCode("");// 销售人代码	
		Dictionary dictionary3 =  DictManager.getDict(Constant.DICTIONARY$HFTAPKIND, Apkind.OPEN_ACCOUNT.getValue());
		req.setBusinType(dictionary3.getPmnm());//业务类型  
		req.setApplicationNo(openAccountAction.getSerialno());//合作平台申请单编号
		req.setExtension(null);
		Dictionary dictionary =  DictManager.getDict(Constant.DICTIONARY$HFTBANKNO, openAccountAction.getBankno());
		req.setClearingAgencyCode(dictionary.getPmnm());//A	9	投资人收款银行账户开户行	
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());//	C	60	投资人收款银行账户户名	
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());//	C	28	投资人收款银行账户账号	
		Dictionary dictionary2 =  DictManager.getDict(Constant.DICTIONARY$HFTIDTP, openAccountAction.getBankidtp());
		req.setCertificateType(dictionary2.getPmnm());//	C	1	机构证件类型	
												/*0-身份证，1-护照
												2-军官证，3-士兵证
												4-港澳居民来往内地通行证，5-户口本
												6-外国护照，7-其它
												8-文职证，9-警官证
												A-台胞证*/
		req.setCertificateNo(openAccountAction.getBankidno());//	C	30	投资人证件号码	
		req.setMobileTelNo(openAccountAction.getBankidno());//C	24	投资人手机号码	
		
		
		/*
		 * 
			286	CertValidDate	A	8	证件有效期
			49	EmailAddress	C	40	投资人E-MAIL地址
			88	OfficeTelNo	C	22	投资人单位电话号码
			51	FaxNo	C	24	投资人传真号码
			4	Address	C	120	通讯地址
			101	PostCode	A	6	投资人邮政编码
			5027	ProtocolNo	A	32	银行协议编号

		 */
		req.setProtocolNo(openAccountAction.getProtocolno());//	A	32	银行协议编号
		return req;
	}
	
}
