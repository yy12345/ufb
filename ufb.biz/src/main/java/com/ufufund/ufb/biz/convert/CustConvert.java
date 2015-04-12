package com.ufufund.ufb.biz.convert;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.remote.hft.BankAuthRequest;
import com.ufufund.ufb.model.remote.hft.BankVeriRequest;

public class CustConvert {
  

	public static Custinfo convertCustinfo(RegisterAction registerAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(registerAction.getLoginCode());
		custinfo.setPasswd(registerAction.getLoginPassword());
		custinfo.setInvtp(registerAction.getInvtp().getValue());
		custinfo.setLevel(registerAction.getLevel().getValue());
		custinfo.setOrganization(registerAction.getOrganization());
		custinfo.setBusiness(registerAction.getBusiness());
		return custinfo;
	}

	
//	public static Changerecordinfo convertChangerecordinfo(Custinfo custinfo){
//		Changerecordinfo changerecordinfo = new Changerecordinfo();
//		changerecordinfo.setCustno(custinfo.getCustno());
//		changerecordinfo.setRecordafter(custinfo.toString());
//		changerecordinfo.setTablename(TableName.CUSTINFO.value());
//		return changerecordinfo;
//	}
	
	

	public static Custinfo convertOpenAccountAction(OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(openAccountAction.getCustno());
		custinfo.setInvnm(openAccountAction.getInvnm());
		custinfo.setIdno(openAccountAction.getIdno());
		custinfo.setTradepwd(openAccountAction.getTradepwd());
		custinfo.setInvtp(Invtp.PERSONAL.getValue());
		custinfo.setIdtp(Constant.IDTP$0);
		return custinfo;
	}
	
	public static BankAuthRequest convertBankAuthRequest(OpenAccountAction openAccountAction){
		BankAuthRequest req = new BankAuthRequest();
		/*
		 *  目前写死 后面从字典转换
		 */
		req.setVersion("");//版本号 
		req.setMerchantId("");//机构标识  
		req.setDistributorCode("");// 销售人代码	
		req.setBusinType("bankSwiftAuth");//业务类型  
		req.setApplicationNo("");//合作平台申请单编号
		req.setExtension(null);
		req.setClearingAgencyCode(openAccountAction.getBankno());//A	9	投资人收款银行账户开户行	
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());//	C	60	投资人收款银行账户户名	
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());//	C	28	投资人收款银行账户账号	
		req.setCertificateType(openAccountAction.getBankidtp());//	C	1	机构证件类型	
												/*0-身份证，1-护照
												2-军官证，3-士兵证
												4-港澳居民来往内地通行证，5-户口本
												6-外国护照，7-其它
												8-文职证，9-警官证
												A-台胞证*/
		req.setCertificateNo(openAccountAction.getBankidno());//	C	30	投资人证件号码	
		req.setMobileTelNo(openAccountAction.getBankidno());//C	24	投资人手机号码	
		return req;
	}
	
	
	public static BankVeriRequest convertBankVeriRequest(OpenAccountAction openAccountAction){
		BankVeriRequest req = new BankVeriRequest();
		/*
		 *  目前写死 后面从字典转换
		 */
		req.setVersion("");//版本号 
		req.setMerchantId("");//机构标识  
		req.setDistributorCode("");// 销售人代码	
		req.setBusinType("bankSwiftAuth");//业务类型  
		req.setApplicationNo("");//合作平台申请单编号
		req.setExtension(null);
		req.setClearingAgencyCode(openAccountAction.getBankno());//A	9	投资人收款银行账户开户行	
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());//	C	60	投资人收款银行账户户名	
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());//	C	28	投资人收款银行账户账号	
		req.setCertificateType(openAccountAction.getBankidtp());//	C	1	机构证件类型	
												/*0-身份证，1-护照
												2-军官证，3-士兵证
												4-港澳居民来往内地通行证，5-户口本
												6-外国护照，7-其它
												8-文职证，9-警官证
												A-台胞证*/
		req.setCertificateNo(openAccountAction.getBankidno());//	C	30	投资人证件号码	
		req.setMobileTelNo(openAccountAction.getBankidno());//C	24	投资人手机号码	
		req.setMobileAuthCode(openAccountAction.getMobileAutoCode());
		
		/*
		 * 
		 * 5020	AccoreqSerial	C	20	请求序列号	快钱渠道必须与鉴权的请求序列号一致。
		   5030	OtherSeial	C	20	对方序列号	通联渠道必须与鉴权发送的请求序列号一致。快钱渠道与鉴权返回的对方序列号一致。
		 */
		return req;
	}
	
}
