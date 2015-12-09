package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;

@Service
public class CustManagerValidator {
	
	@Autowired
	private CustManager custManager;

	/**
	 * 登录的validator
	 * @param action
	 */
	public void validator(Custinfo custinfo){
		if (RegexUtil.isNull(custinfo.getMobileno())||RegexUtil.isNull(custinfo.getPasswd())) {
			throw new UserException("系统异常！");
		}
	}
	
	/**
	 * 注册的validator
	 * @param action
	 */
	public void validator(RegisterAction action){
		if (RegexUtil.isNull(action.getLogincode())||RegexUtil.isNull(action.getPasswd())||!RegexUtil.isPwd(action.getPasswd())
				||RegexUtil.isNull(action.getTradepwd())||!RegexUtil.isTradePwd(action.getTradepwd())) {
			throw new UserException("系统异常！");
		}
	}

	
	/**
	 *  用户注册、冻结、已开户验证
	 * @param openAccountAction
	 */
	public void validator(OpenAccountAction action, String actionName){
		
		String custNo = action.getCustno();
		if(null == custNo || "".equals(custNo)){
			throw new UserException("系统异常！");
		}
		// 用户是否注册验证 
		Custinfo custinfo = custManager.getCustinfo(custNo);		
		if(null == custinfo){
			throw new UserException("系统异常！");
		}
		// 用户是否冻结验证
		if("5".equals(custinfo.getState())){
			throw new UserException("系统异常！");
		}
	}
	
	/**
	 * 修改密码
	 * @param action
	 */
	public void validator(ChangePasswordAction action){
		if("TRADE".equals(action.getActionType())){
			if (RegexUtil.isNull(action.getPassword0())||RegexUtil.isNull(action.getPassword1())||RegexUtil.isNull(action.getPassword2())
					||!action.getPassword1().equals(action.getPassword2())||!RegexUtil.isTradePwd(action.getPassword1())) {
				throw new UserException("系统异常！");
			}
		}else if("LOGIN".equals(action.getActionType())){
			if (RegexUtil.isNull(action.getPassword0())||RegexUtil.isNull(action.getPassword1())||RegexUtil.isNull(action.getPassword2())
					||!action.getPassword1().equals(action.getPassword2())||!RegexUtil.isPwd(action.getPassword1())) {
				throw new UserException("系统异常！");
			}
		}else{
			if (RegexUtil.isNull(action.getPassword1())||RegexUtil.isNull(action.getPassword2())
					||!action.getPassword1().equals(action.getPassword2())||!RegexUtil.isTradePwd(action.getPassword1())) {
				throw new UserException("系统异常！");
			}
		}
		
	}
	/**
	 * 修改家庭版的密码的验证
	 * @param action
	 */
	public void validatorFamily(ChangePasswordAction action){
		if("TRADE".equals(action.getActionType())){
			if (RegexUtil.isNull(action.getPassword0())||RegexUtil.isNull(action.getPassword1())||RegexUtil.isNull(action.getPassword2())
					||!action.getPassword1().equals(action.getPassword2())||!RegexUtil.isPwd(action.getPassword1())) {
				throw new UserException("系统异常！");
			}
		}else if("LOGIN".equals(action.getActionType())){
			if (RegexUtil.isNull(action.getPassword1())) {
				throw new UserException("系统异常！");
			}
		}else{
			if (RegexUtil.isNull(action.getPassword1())||RegexUtil.isNull(action.getPassword2())
					||!action.getPassword1().equals(action.getPassword2())||!RegexUtil.isPwd(action.getPassword1())) {
				throw new UserException("系统异常！");
			}
		}
	}
}
