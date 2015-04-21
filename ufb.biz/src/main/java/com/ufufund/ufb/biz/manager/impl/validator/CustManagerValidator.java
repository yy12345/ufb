package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.common.ValidatorCommon;
import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.enums.Level;

@Service
public class CustManagerValidator extends ValidatorCommon {


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
	private final static String IDCARDNO = "身份证";
	private final static String MERCHANT = "开户机构";

	public void validator(LoginAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getLoginCode())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINCODE);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD);
		}
	}
	
	/*
	 * 注册验证
	 */
	public void validator(RegisterAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getLoginCode())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINCODE);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD);
		}
		if (RegexUtil.isNull(action.getLoginPassword2())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD2);
		}
		if (!action.getLoginPassword().equals(action.getLoginPassword2())) {
			throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, LOGINPASSWORD);
		}
		if (!RegexUtil.isPwd(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,LOGINPASSWORD);
		}
		if (!RegexUtil.isMobile(action.getLoginCode())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, MOBILE);
		}
		if(action.getLevel().equals(Level.OPERATOR)){
			if (RegexUtil.isNull(action.getOrganization())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, ORGANIZATION);
			}
			if (RegexUtil.isNull(action.getBusiness())) {
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
	
	public void validatorOpenAccount1(OpenAccountAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getCustno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, CUSTNO);
		}
		if (RegexUtil.isNull(action.getInvnm())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, INVNM);
		}
		if (RegexUtil.isNull(action.getIdno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, IDNO);
		}
		if (!RegexUtil.isIdCardNo(action.getIdno())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, IDCARDNO);
		}
		if (RegexUtil.isNull(action.getTradepwd())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, TRADEPWD);
		}
		if (!RegexUtil.isPwd(action.getTradepwd())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, TRADEPWD);
		}
		if (RegexUtil.isNull(action.getTradepwd2())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD2);
		}
		if (!action.getTradepwd().equals(action.getTradepwd2())) {
			throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, TRADEPWD);
		}
		if (action.getMerchant()==null||RegexUtil.isNull(action.getMerchant().Value())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, MERCHANT);
		}
	}

	public void validator(OpenAccountAction action) throws BizException {
		// TODO Auto-generated method stub
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getBankno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKNO);
		}
		if (RegexUtil.isNull(action.getBankacnm())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKACNM);
		}
		if (RegexUtil.isNull(action.getBankidtp())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKIDTP);
		}
		if (RegexUtil.isNull(action.getBankidno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKIDNO);
		}
		if (RegexUtil.isNull(action.getBankacco())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKACCO);
		}
		if (RegexUtil.isNull(action.getBankmobile())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKMOBILE);
		}
	}
}
