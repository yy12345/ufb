package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
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
	
	@Autowired
	private CustManager custManager;

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
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPWD);
		}
	}
	
	/**
	 * 注册的validator
	 * @param action
	 * @throws BizException
	 */
	public void validator(RegisterAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getLogincode())) {
			// 手机号为空
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.MOBILE);
		}
		if (RegexUtil.isNull(action.getLoginpwd())) {
			// 登录密码为空
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPWD);
		}
		if (!RegexUtil.isPwd(action.getLoginpwd())) {
			// 密码 以字母，数字开头，长度在6-12之间，只能包含字符、数字和下划线。
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,BisConst.Register.LOGINPWD);
		}
		if (RegexUtil.isNull(action.getLoginpwd2())) {
			// 确认密码为空
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPWD2);
		}
		if (!action.getLoginpwd().equals(action.getLoginpwd2())) {
			// 两次密码确认不一致
			throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, BisConst.Register.LOGINPWD);
		}
		if (!RegexUtil.isMobile(action.getLogincode())) {
			// 手机号格式错误
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.MOBILE);
		}
		if(Level.ORGANIZATION.equals(action.getInvtp())){
			// 机构注册
			if (RegexUtil.isNull(action.getOrgnm())) {
				// 机构名称为空
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.ORGNM);
			}
			if (RegexUtil.isNull(action.getOrgbusiness())) {
				// 营业执照为空
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.ORGBUSINESS);
			}
		}
	}

	
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
		
		if(!action.isOpenaccoflag()){
			if (custManager.isIdnoRegister(action.getIdno())) {
				// 经办人idno
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
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPWD0);
			}
			if (RegexUtil.isNull(action.getPassword1())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPWD);
			}
			if (RegexUtil.isNull(action.getPassword2())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.LOGINPWD2);
			}
			if (!action.getPassword1().equals(action.getPassword2())) {
				throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, BisConst.Register.LOGINPWD);
			}
			if (!RegexUtil.isPwd(action.getPassword1())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,BisConst.Register.LOGINPWD);
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
