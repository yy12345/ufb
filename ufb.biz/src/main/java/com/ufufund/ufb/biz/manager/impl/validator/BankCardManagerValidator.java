package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.ThreadLocalUtil;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.enums.ErrorInfo;

@Service
public class BankCardManagerValidator {

	/**
	 * 用户基本信息验证（用户名、身份证、交易密码、开户机构）
	 * 银行基本信息验证（鉴权、验证、开户）
	 * @param action
	 * @throws BizException
	 */
	public void validator(OpenAccountAction action, String actionName) throws BizException {
		
		String processId = action.getProcessId();
		
		//基本信息验证（用户名、身份证、交易密码、开户机构）
		if("UserBase".equals(actionName)){
			// CustNo
			if (RegexUtil.isNull(action.getCustno())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.CUSTNO);
			}
			// 用户名
			if (RegexUtil.isNull(action.getInvnm())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.INVNM);
			}
			// 证件号码
			if (RegexUtil.isNull(action.getIdno())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.IDNO);
			}
			// 身份证号码
			if (!RegexUtil.isIdCardNo(action.getIdno())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.IDCARDNO);
			}
			
			if(("0".equals(action.getInvtp()) && action.getHftfamilytradeaccoct() == 0) || 
					("1".equals(action.getInvtp()) && action.getHftoperatortradeaccoct() == 0)	){
				// 已经绑卡用户不需要再次设置交易密码
				// 交易密码
				if (RegexUtil.isNull(action.getTradepwd())) {
					throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.TRADEPWD);
				}
				// 交易密码
				if (!RegexUtil.isPwd(action.getTradepwd())) {
					throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.TRADEPWD);
				}
				// 交易确认密码
				if (RegexUtil.isNull(action.getTradepwd2())) {
					throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.TRADEPWD2);
				}
				// 交易确认密码
				if (!action.getTradepwd().equals(action.getTradepwd2())) {
					throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, BisConst.Register.TRADEPWD2);
				}
			}
			if("1".equals(action.getInvtp())){
				//幼教机构
				if(RegexUtil.isNull(action.getOrgnm())){
					throw new BizException(ThreadLocalUtil.getProccessId(), ErrorInfo.NECESSARY_EMPTY, BisConst.Register.ORGANIZATION);
				}
				//营业执照
				if(RegexUtil.isNull(action.getOrgbusiness())){
					throw new BizException(ThreadLocalUtil.getProccessId(), ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BUSINESS);
				}
			}
		}
		
		//银行基本信息验证
		if("BankBase".equals(actionName)){
			if (RegexUtil.isNull(action.getBankno())) {
				//银行编码
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKNO);
			}
			if (RegexUtil.isNull(action.getBankacnm())) {
				//银行开户户名
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKACNM);
			}
			if (RegexUtil.isNull(action.getBankidtp())) {
				//银行证件类型
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKIDTP);
			}
			if (RegexUtil.isNull(action.getBankidno())) {
				//银行证件号码
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKIDNO);
			}
			if (!RegexUtil.isIdCardNo(action.getBankidno())) {
				//银行证件号码 isIdCardNo
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKIDNO);
			}
			if (RegexUtil.isNull(action.getBankacco())) {
				//银行卡号
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKACCO);
			}
			if (RegexUtil.isNull(action.getBankmobile())) {
				//银行开户手机号
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKMOBILE);
			}
			if(!RegexUtil.isMobile(action.getBankmobile())){
				//银行开户手机号 isMobile
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.BANKMOBILE);
			}
			
			// 绑卡验证需要验证
			if("3".equals(action.getReqseq())){
				if(RegexUtil.isNull(action.getMobileautocode())){
					//银行开户手机验证吗 isNull
					throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKMOBILEMSGCODE);
				}
			}
		}
		
		//基本信息验证（用户名、身份证、交易密码、开户机构）
		if("OrgBase".equals(actionName)){
			// 幼教机构
			if(RegexUtil.isNull(action.getOrgnm())){
				throw new BizException(ThreadLocalUtil.getProccessId(), ErrorInfo.NECESSARY_EMPTY, BisConst.Register.ORGANIZATION);
			}
			// 营业执照
			if(RegexUtil.isNull(action.getOrgbusiness())){
				throw new BizException(ThreadLocalUtil.getProccessId(), ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BUSINESS);
			}
			// CustNo
			if (RegexUtil.isNull(action.getCustno())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.CUSTNO);
			}
			// 用户名
			if (RegexUtil.isNull(action.getInvnm())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKACNM);
			}
			// 证件号码
			if (RegexUtil.isNull(action.getIdno())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKIDNO);
			}
			// 身份证号码
			if (!RegexUtil.isIdCardNo(action.getIdno())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.BANKIDNO);
			}
			// 法人证件号码
			if (RegexUtil.isNull(action.getRerpidno())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKIDNO);
			}
			// 法人证件号码
			if (!RegexUtil.isIdCardNo(action.getRerpidno())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.BANKIDNO);
			}
		}
		
		//银行基本信息验证
		if("OrgBankBase".equals(actionName)){
			if (RegexUtil.isNull(action.getBankacnm())) {
				// 银行开户户名
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKACNM);
			}
			if (RegexUtil.isNull(action.getBankno())) {
				// 银行编码
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKNO);
			}
			if (RegexUtil.isNull(action.getBankadd())) {
				// 支行网点
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKADD);
			}
			if (RegexUtil.isNull(action.getBankacco())) {
				// 银行卡号
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BANKACCO);
			}
			// 银行卡号2
			if (!action.getBankacco().equals(action.getBankacco2())) {
				throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, BisConst.Register.BANKACCO2);
			}
			if(action.getHftorganizationtradeaccoct() == 0){
				// 已经绑卡用户不需要再次设置交易密码
				// 交易密码
				if (RegexUtil.isNull(action.getTradepwd())) {
					throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.TRADEPWD);
				}
				// 交易密码
				if (!RegexUtil.isPwd(action.getTradepwd())) {
					throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.TRADEPWD);
				}
				// 交易确认密码
				if (RegexUtil.isNull(action.getTradepwd2())) {
					throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.TRADEPWD2);
				}
				// 交易确认密码
				if (!action.getTradepwd().equals(action.getTradepwd2())) {
					throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, BisConst.Register.TRADEPWD2);
				}
			}
		}
	}
}
