package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;

@Service
public class BankCardManagerValidator {

	@Autowired
	private CustManager custManager;
	
	/**
	 * 用户基本信息验证（用户名、身份证、交易密码、开户机构）
	 * 银行基本信息验证（鉴权、验证、开户）
	 * @param action
	 */
	public void validator(OpenAccountAction action, String actionName){
		
		
		//基本信息验证（用户名、身份证、交易密码、开户机构）
		if("UserBase".equals(actionName)){
			// CustNo 用户名 证件号码
			if (RegexUtil.isNull(action.getCustno())||RegexUtil.isNull(action.getName())||RegexUtil.isNull(action.getIdno())) {
				throw new UserException("系统异常！");
			}
		}
		
		//银行基本信息验证
		if("UserBankBase".equals(actionName)){
			if (RegexUtil.isNull(action.getBankno())||RegexUtil.isNull(action.getCertno())||RegexUtil.isNull(action.getBankacco())
					||RegexUtil.isNull(action.getMobile())||!RegexUtil.isMobile(action.getMobile())) {
				throw new UserException("系统异常！");
			}
		}
		
		//银行基本信息验证
		if("OrgBankBase".equals(actionName)){
			if (RegexUtil.isNull(action.getBanknm())||RegexUtil.isNull(action.getBankno())
					||RegexUtil.isNull(action.getSubbank())||RegexUtil.isNull(action.getBankacco())) {
				throw new UserException("系统异常！");
			}
		}
	}
}
