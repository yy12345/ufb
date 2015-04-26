package com.ufufund.ufb.biz.manager.impl.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.Merchant.MerchantFund;
import com.ufufund.ufb.biz.Merchant.OpenAccount;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.remote.hft.BankAuthRequest;
import com.ufufund.ufb.model.remote.hft.BankAuthResponse;
import com.ufufund.ufb.model.remote.hft.BankVeriRequest;
import com.ufufund.ufb.model.remote.hft.BankVeriResponse;
import com.ufufund.ufb.model.remote.hft.OpenAccountRequest;
import com.ufufund.ufb.model.remote.hft.OpenAccountResponse;
import com.ufufund.ufb.remote.simulator.HftCustServiceSimulator;

@Service
public class HtfFund extends MerchantFund {

	@Autowired
	private HftCustServiceSimulator hftCustService;

	@Override
	public OpenAccount bankAuth(Object obj) {
		OpenAccountAction openAccountAction = (OpenAccountAction) obj;
		// 组装Request
		BankAuthRequest bankAuthRequest = this.convertBankAuthRequest(openAccountAction);
		BankAuthResponse bankAuthResponse = null;
		if (!TestHelp.isTest) {
			// 调用恒生接口
			bankAuthResponse = hftCustService.bankAuth(bankAuthRequest);
		} else {
			// 模拟器
			bankAuthResponse = new BankAuthResponse();
			bankAuthResponse.setReturnCode("0000");
			bankAuthResponse.setOtherSerial("OtherSerial1");
			bankAuthResponse.setProtocolNo("ProtocolNo1");
			bankAuthResponse.setReturnMsg("ReturnMsg");
		}
		
		// 解析Response返回值
		OpenAccount openAccount = new OpenAccount();
		// Dictionary dictionary = DictManager.getDict(
		// Constant.DICTIONARY$HTFERROR, bankVeriResponse.getReturnCode());
		// if (dictionary != null) {
		// openAccount.setReturncode(dictionary.getPmnm());
		// } else {
		// openAccount.setReturncode(bankVeriResponse.getReturnCode());
		// }
		openAccount.setReturncode(bankAuthResponse.getReturnCode());
		openAccount.setReturnMsg(bankAuthResponse.getReturnMsg());
		openAccount.setOtherserial(bankAuthResponse.getOtherSerial());
		openAccount.setProtocolno(bankAuthResponse.getProtocolNo());
		return openAccount;
	}

	@Override
	public OpenAccount bankVeri(Object obj) {
		OpenAccountAction openAccountAction = (OpenAccountAction) obj;
		// 组装Request
		BankVeriRequest bankVeriRequest = this.convertBankVeriRequest(openAccountAction);
		BankVeriResponse bankVeriResponse = null;
		if (!TestHelp.isTest) {
			// 调用恒生接口
			bankVeriResponse = hftCustService.bankVeri(bankVeriRequest);
		} else {
			// 模拟器
			bankVeriResponse = new BankVeriResponse();
			bankVeriResponse.setReturnCode("0000");
			bankVeriResponse.setValidateState("1");
		}
		
		// 解析Response返回值
		OpenAccount openAccount = new OpenAccount();
		openAccount.setReturncode(bankVeriResponse.getReturnCode());
		openAccount.setReturnMsg(bankVeriResponse.getReturnMsg());
		// 银行验证结果 1-成功，0-失败
		if (bankVeriResponse.getValidateState() == null
				|| !"1".equals(bankVeriResponse.getValidateState())) {
			openAccount.setReturncode("9999");
		}
		// 银行协议编号
		openAccount.setProtocolno(bankVeriResponse.getProtocolNo());
		
		return openAccount;
	}

	@Override
	public OpenAccount openAccount(Object obj) {
		OpenAccountAction openAccountAction = (OpenAccountAction) obj;
		OpenAccountRequest openAccountRequest = convertOpenAccountRequest(openAccountAction);
		OpenAccountResponse openAccountResponse = null;
		if (!TestHelp.isTest) {
			openAccountResponse = hftCustService .openAccount(openAccountRequest);
		} else {
			// 模拟器
			openAccountResponse = new OpenAccountResponse();
			openAccountResponse.setReturnCode("0000");
			openAccountResponse.setTransactionAccountID("AccountID");
		}
		
		// 解析Response返回值
		OpenAccount openAccount = new OpenAccount();
		openAccount.setReturncode(openAccountResponse.getReturnCode());
		openAccount.setReturnMsg(openAccountResponse.getReturnMsg());
		openAccount.setTransactionAccountID(openAccountResponse.getTransactionAccountID());
		return openAccount;
	}

	private BankAuthRequest convertBankAuthRequest(
			OpenAccountAction openAccountAction) {
		BankAuthRequest req = new BankAuthRequest();
		// 版本号
		req.setVersion(Constant.HftSysConfig.Version);
		// 机构标识
		req.setMerchantId(Constant.HftSysConfig.MerchantId);
		// 销售人代码
		req.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		
		// TODO
		// 业务类型
//		Dictionary dictionary3 = DictManager.getDict(Constant.DICTIONARY$HFTAPKIND, Apkind.SWIFTAUTH.getValue());
//		req.setBusinType(dictionary3.getPmnm());
		req.setBusinType(Constant.HftBusiType.BankAuth);
		// 合作平台申请单编号
		req.setApplicationNo(openAccountAction.getSerialno());
		// 不要
		//req.setExtension(null);

		// TODO
		// A 9 投资人收款银行账户开户行
//		Dictionary dictionary = DictManager.getDict(Constant.DICTIONARY$HFTBANKNO, openAccountAction.getBankno());
//		req.setClearingAgencyCode(dictionary.getPmnm());
		req.setClearingAgencyCode("012");

		// 投资人银行账户姓名
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());
		// C 28 投资人收款银行账户账号
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());

		// TODO
		// 投资人银行证件类型
//		Dictionary dictionary2 = DictManager.getDict(Constant.DICTIONARY$HFTIDTP, openAccountAction.getBankidtp());
//		req.setCertificateType(dictionary2.getPmnm());
		req.setCertificateType(openAccountAction.getBankidtp());

		// C 30 投资人证件号码
		req.setCertificateNo(openAccountAction.getBankidno());
		// C 24 投资人手机号码
		req.setMobileTelNo(openAccountAction.getBankmobile());
		// 请求序列号 给通联的流水号 UFT生成
		req.setAccoreqSerial(openAccountAction.getAccoreqSerial());
		return req;
	}

	private BankVeriRequest convertBankVeriRequest(OpenAccountAction openAccountAction) {
		BankVeriRequest req = new BankVeriRequest();

		// 版本号
		req.setVersion(Constant.HftSysConfig.Version);
		// 机构标识
		req.setMerchantId(Constant.HftSysConfig.MerchantId);
		// 销售人代码
		req.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		
		//TODO
		// 业务类型
//		Dictionary dictionary3 = DictManager.getDict(Constant.DICTIONARY$HFTAPKIND, Apkind.SWIFTVERIFY.getValue());
//		req.setBusinType(dictionary3.getPmnm());
		req.setBusinType(Constant.HftBusiType.BankVeri);
		
		// 合作平台申请单编号
		req.setApplicationNo(openAccountAction.getSerialno());
		req.setExtension(null);
		// TODO
		// A 9 投资人收款银行账户开户行
//		Dictionary dictionary = DictManager.getDict(Constant.DICTIONARY$HFTBANKNO, openAccountAction.getBankno());
//		req.setClearingAgencyCode(dictionary.getPmnm());
		req.setClearingAgencyCode("012");
		
		// C 60 投资人收款银行账户户名
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());
		// C 28 投资人收款银行账户账号
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());
		
		// TODO
		// 投资人银行证件类型
//		Dictionary dictionary2 = DictManager.getDict(Constant.DICTIONARY$HFTIDTP, openAccountAction.getBankidtp());
//		req.setCertificateType(dictionary2.getPmnm());
		req.setCertificateType(openAccountAction.getBankidtp());

		// C 30 投资人证件号码
		req.setCertificateNo(openAccountAction.getBankidno());
		// C 24 投资人手机号码
		req.setMobileTelNo(openAccountAction.getBankmobile());
		// 手机验证码
		req.setMobileAuthCode(openAccountAction.getMobileAutoCode());
		// 对方序列号 通联渠道必须与鉴权发送的请求序列号一致。快钱渠道与鉴权返回的对方序列号一致
		req.setOtherSerial(openAccountAction.getOtherserial());
		// 快钱渠道必须与鉴权的请求序列号一致。暂时不用 
		//req.setAccoreqSerial(openAccountAction.getAccoreqSerial());
		return req;
	}

	public OpenAccountRequest convertOpenAccountRequest(OpenAccountAction openAccountAction) {
		OpenAccountRequest req = new OpenAccountRequest();
		
		// 版本号
		req.setVersion(Constant.HftSysConfig.Version);
		// 机构标识
		req.setMerchantId(Constant.HftSysConfig.MerchantId);
		// 销售人代码
		req.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		// TODO
		// 业务类型
//		Dictionary dictionary3 = DictManager.getDict(Constant.DICTIONARY$HFTAPKIND, Apkind.OPEN_ACCOUNT.getValue());
//		req.setBusinType(dictionary3.getPmnm());
		req.setBusinType(Constant.HftBusiType.OpenAccount);
		// 合作平台申请单编号
		req.setApplicationNo(openAccountAction.getSerialno());
		req.setExtension(null);
		
		// TODO
		// A 9 投资人收款银行账户开户行
//		Dictionary dictionary = DictManager.getDict(Constant.DICTIONARY$HFTBANKNO, openAccountAction.getBankno());
//		req.setClearingAgencyCode(dictionary.getPmnm());
		req.setClearingAgencyCode("012");
		// C 60 投资人收款银行账户户名
		req.setAcctNameOfInvestorInClearingAgency(openAccountAction.getBankacnm());
		// C 28 投资人收款银行账户账号
		req.setAcctNoOfInvestorInClearingAgency(openAccountAction.getBankacco());
		// 投资人姓名
		req.setInvestorName(openAccountAction.getBankacnm());
		// TODO
		// C 1 机构证件类型
//		Dictionary dictionary2 = DictManager.getDict(Constant.DICTIONARY$HFTIDTP, openAccountAction.getBankidtp());
//		req.setCertificateType(dictionary2.getPmnm());
		req.setCertificateType(openAccountAction.getBankidtp());
		// C 30 投资人证件号码
		req.setCertificateNo(openAccountAction.getBankidno());
		// C 24 投资人手机号码
		req.setMobileTelNo(openAccountAction.getBankmobile());

		/*
		 * 
		 * 286 CertValidDate A 8 证件有效期 49 EmailAddress C 40 投资人E-MAIL地址 88
		 * OfficeTelNo C 22 投资人单位电话号码 51 FaxNo C 24 投资人传真号码 4 Address C 120 通讯地址
		 * 101 PostCode A 6 投资人邮政编码 5027 ProtocolNo A 32 银行协议编号
		 */
		req.setCertValidDate("20181022");
		req.setEmailAddress("15211827360@163.com");
		req.setOfficeTelNo("02188592231");
		req.setFaxNo("02188592231");
		req.setAddress("东方路");
		req.setPostCode("200000");
		
		// TODO
		req.setProtocolNo(openAccountAction.getProtocolno());// A 32 银行协议编号
		
		return req;
	}

}
