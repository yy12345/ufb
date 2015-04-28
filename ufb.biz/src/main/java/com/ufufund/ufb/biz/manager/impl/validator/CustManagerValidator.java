package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.enums.Level;

@Service
public class CustManagerValidator {


	private final static String CUSTNO = "用户id";
	private final static String LOGINCODE = "账号";
	private final static String LOGINPASSWORD = "密码";
	private final static String LOGINPASSWORD2 = "确认密码";
	private final static String ORGANIZATION = "机构名称";
	private final static String BUSINESS = "营业执照";
	private final static String BANKNO = "银行编码";
	private final static String BANKACCO = "银行卡号";
	private final static String BANKIDTP = "银行证件类型";
	private final static String BANKIDNO = "银行证件号码";
	private final static String BANKACNM = "银行开户户名";
	private final static String BANKMOBILE = "银行开户手机号";	
	private final static String INVNM = "用户姓名";
	private final static String IDNO = "证件号码";
	private final static String TRADEPWD = "交易密码";
	private final static String MOBILE = "手机号";
	private final static String MOBILEAUTOCODE = "验证码";
	private final static String IDCARDNO = "身份证";
	private final static String MERCHANT = "开户机构";

	/**
	 * 登录的validator
	 * @param action
	 * @throws BizException
	 */
	public void validator(LoginAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getLoginCode())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINCODE);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD);
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
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, MOBILE);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			// 密码
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD);
		}
		if (!RegexUtil.isPwd(action.getLoginPassword())) {
			// 密码 以字母，数字开头，长度在6-12之间，只能包含字符、数字和下划线。
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,LOGINPASSWORD);
		}
		if (RegexUtil.isNull(action.getLoginPassword2())) {
			// 确认密码
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD2);
		}
		if (!action.getLoginPassword().equals(action.getLoginPassword2())) {
			// 密码 = 确认密码
			throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, LOGINPASSWORD);
		}
		if (!RegexUtil.isMobile(action.getLoginCode())) {
			// 手机号
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, MOBILE);
		}
		if(action.getLevel().equals(Level.OPERATOR)){
			// Level.PERSONAL
			// PERSONAL("0"), //个人
			// OPERATOR("1");//经办人
			if (RegexUtil.isNull(action.getOrganization())) {
				// 机构名称
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, ORGANIZATION);
			}
			if (RegexUtil.isNull(action.getBusiness())) {
				// 营业执照
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BUSINESS);
			}
		}
	}
	
	public void validator(ChangePasswordAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getMobile())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, MOBILE);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD);
		}
		if (RegexUtil.isNull(action.getLoginPassword2())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD2);
		}
		if (!action.getLoginPassword().equals(action.getLoginPassword2())) {
			throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD);
		}
		if (!RegexUtil.isPwd(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,LOGINPASSWORD);
		}
		if (!RegexUtil.isMobile(action.getMobile())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, MOBILE);
		}
	}
}
