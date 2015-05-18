package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.enums.Level;

@Service
public class CustManagerValidator {

	/**
	 * 登录的validator
	 * @param action
	 * @throws BizException
	 */
	public void validator(LoginAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getLoginCode())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINCODE);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPASSWORD);
		}
	}
	
	/**
	 * 注册的validator
	 * @param action
	 * @throws BizException
	 */
	public void validator(RegisterAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getLoginCode())) {
			// 账号
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.MOBILE);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			// 密码
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPASSWORD);
		}
		if (!RegexUtil.isPwd(action.getLoginPassword())) {
			// 密码 以字母，数字开头，长度在6-12之间，只能包含字符、数字和下划线。
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,BisConst.Register.LOGINPASSWORD);
		}
		if (RegexUtil.isNull(action.getLoginPassword2())) {
			// 确认密码
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPASSWORD2);
		}
		if (!action.getLoginPassword().equals(action.getLoginPassword2())) {
			// 密码 = 确认密码
			throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, BisConst.Register.LOGINPASSWORD);
		}
		if (!RegexUtil.isMobile(action.getLoginCode())) {
			// 手机号
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.MOBILE);
		}
		if(Level.OPERATOR.equals(action.getLevel())){
			// Level.PERSONAL
			// PERSONAL("0"), //个人
			// OPERATOR("1");//经办人
			if (RegexUtil.isNull(action.getOrganization())) {
				// 机构名称
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.ORGANIZATION);
			}
			if (RegexUtil.isNull(action.getBusiness())) {
				// 营业执照
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.BUSINESS);
			}
		}
	}
	@Autowired
	private CustManager custManager;
	
	/**
	 *  用户注册、冻结、已开户验证
	 * @param openAccountAction
	 */
	public void validator(OpenAccountAction action, String actionName) throws BizException {
		
		// Custno验证
		String custNo = action.getCustno();
		if(null == custNo || "".equals(custNo)){
			throw new BizException(action.getProcessId(), ErrorInfo.NO_IDCARDNO, BisConst.Register.CUSTNO);
		}
		// 用户是否注册验证 
		Custinfo custinfo = custManager.getCustinfo(custNo);		
		if(null == custinfo){
			throw new BizException(action.getProcessId(), ErrorInfo.NO_IDCARDNO, BisConst.Register.CUSTNO);
		}
		// 用户是否冻结验证
		if(Constant.Custinfo.CUSTST$P.equals(custinfo.getCustst())){
			throw new BizException(action.getProcessId(), ErrorInfo.FREEZE_USER, BisConst.Register.CUSTNO);
		}
		// Custst 用户是否开户验证
		if(!Constant.Custinfo.OPENACCOUNT$Y.equals(custinfo.getOpenaccount())){
			if (custManager.isIdCardNoRegister(action.getIdno())) {
				throw new BizException(action.getProcessId(), ErrorInfo.ALREADY_REGISTER, BisConst.Register.IDNO);
			}
		}
	}
	
	/**
	 * 修改密码
	 * @param action
	 * @throws BizException
	 */
	public void validator(ChangePasswordAction action) throws BizException {
		String processId = action.getProcessId();
		if("TRADE".equals(action.getActionType())){
			if (RegexUtil.isNull(action.getPassword0())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.TRADEPWD0);
			}
			if (RegexUtil.isNull(action.getPassword1())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.TRADEPWD);
			}
			if (RegexUtil.isNull(action.getPassword2())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.TRADEPWD2);
			}
			if (!action.getPassword1().equals(action.getPassword2())) {
				throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, BisConst.Register.TRADEPWD);
			}
			if (!RegexUtil.isPwd(action.getPassword1())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,BisConst.Register.TRADEPWD);
			}
		}else if("LOGIN".equals(action.getActionType())){
			if (RegexUtil.isNull(action.getPassword0())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPASSWORD0);
			}
			if (RegexUtil.isNull(action.getPassword1())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPASSWORD);
			}
			if (RegexUtil.isNull(action.getPassword2())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPASSWORD2);
			}
			if (!action.getPassword1().equals(action.getPassword2())) {
				throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, BisConst.Register.LOGINPASSWORD);
			}
			if (!RegexUtil.isPwd(action.getPassword1())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,BisConst.Register.LOGINPASSWORD);
			}
		}else{
			if (RegexUtil.isNull(action.getPassword1())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.TRADEPWD);
			}
			if (RegexUtil.isNull(action.getPassword2())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.TRADEPWD2);
			}
			if (!action.getPassword1().equals(action.getPassword2())) {
				throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, BisConst.Register.TRADEPWD);
			}
			if (!RegexUtil.isPwd(action.getPassword1())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,BisConst.Register.TRADEPWD);
			}
		}
		//if (RegexUtil.isNull(action.getMobile())) {
		//	throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.MOBILE);
		//}
		//if (!RegexUtil.isMobile(action.getMobile())) {
		//	throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.MOBILE);
		//}
	}
}
