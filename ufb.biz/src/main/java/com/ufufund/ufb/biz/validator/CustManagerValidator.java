package com.ufufund.ufb.biz.validator;

import com.ufufund.ufb.biz.common.ValidatorCommon;
import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.model.action.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.enums.Level;

public class CustManagerValidator extends ValidatorCommon {

	public CustManagerValidator(String processId) {
		super(processId);
		// TODO Auto-generated constructor stub
	}
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

	
	private final static String INVNM = "用户姓名";
	private final static String IDNO = "证件号码";
	private final static String TRADEPWD = "交易密码";
	
	private final static String MOBILE = "手机号";
	private final static String IDCARDNO = "身份证";

	private void necessaryLoginAction(LoginAction action) throws BizException {
		if (RegexUtil.isNull(action.getLoginCode())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), LOGINCODE);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), LOGINPASSWORD);
		}
	}

	private void necessaryRegister(RegisterAction action) throws BizException {
		if (RegexUtil.isNull(action.getLoginCode())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), LOGINCODE);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), LOGINPASSWORD);
		}
		if (RegexUtil.isNull(action.getLoginPassword2())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), LOGINPASSWORD2);
		}
		if (!action.getLoginPassword().equals(action.getLoginPassword2())) {
			throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD.value());
		}
		if (!RegexUtil.isPwd(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(),LOGINPASSWORD);
		}
		if (!RegexUtil.isMobile(action.getLoginCode())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(), MOBILE);
		}
		if(action.getLevel().equals(Level.OPERATOR)){
			if (RegexUtil.isNull(action.getOrganization())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), ORGANIZATION);
			}
			if (RegexUtil.isNull(action.getBusiness())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), BUSINESS);
			}
		}
		
	}

	
	private void necessaryChangePasswordAction(ChangePasswordAction action) throws BizException {
		if (RegexUtil.isNull(action.getCustno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), CUSTNO);
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), LOGINPASSWORD);
		}
		if (RegexUtil.isNull(action.getLoginPassword2())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), LOGINPASSWORD2);
		}
		if (!action.getLoginPassword().equals(action.getLoginPassword2())) {
			throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD.value());
		}
		if (!RegexUtil.isPwd(action.getLoginPassword())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(),LOGINPASSWORD);
		}
	}
	
	
	
	
	@Override
	public void validator(Object obj) throws BizException {
		// TODO Auto-generated method stub
		/*
		 * 注册
		 */
		if (obj instanceof RegisterAction) {
			RegisterAction action = (RegisterAction) obj;
			this.necessaryRegister(action);
		/*
		 * 修改密码	
		 */
		} else if (obj instanceof ChangePasswordAction) {
			ChangePasswordAction action = (ChangePasswordAction) obj;
			this.necessaryChangePasswordAction(action);
		/*
		 * 登录	
		 */
		} else if (obj instanceof LoginAction) {	
			LoginAction action = (LoginAction) obj;
			this.necessaryLoginAction(action);
			
			
//		} else if (obj instanceof Custinfo) {
//			Custinfo action = (Custinfo) obj;
//			this.necessaryUpdateCustinfo(action);
		} else if (obj instanceof OpenAccountAction) {
			OpenAccountAction action = (OpenAccountAction) obj;		
			/*
			 * 判断是否已经绑过卡 身份验证过
			 */
			if(!Constant.CUSTST$Y.equals(action.getCustst())){
				this.necessaryopenAccountFirst(action);
			}
			this.necessaryOpenAccount(action);
		}

	}

	private void necessaryopenAccountFirst(OpenAccountAction action) throws BizException {
		if (RegexUtil.isNull(action.getCustno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), CUSTNO);
		}
		if (RegexUtil.isNull(action.getInvnm())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), INVNM);
		}
		if (RegexUtil.isNull(action.getIdno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), IDNO);
		}
		if (RegexUtil.isNull(action.getTradepwd())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), TRADEPWD);
		}
		if (RegexUtil.isNull(action.getTradepwd2())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), LOGINPASSWORD2);
		}
		if (!action.getTradepwd().equals(action.getTradepwd2())) {
			throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD.value());
		}
		if (!RegexUtil.isPwd(action.getTradepwd())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(),TRADEPWD);
		}
	}

	
	
	private void necessaryOpenAccount(OpenAccountAction action) throws BizException {
		// TODO Auto-generated method stub
		if (RegexUtil.isNull(action.getBankno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), BANKNO);
		}
		if (RegexUtil.isNull(action.getBankacco())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), BANKACCO);
		}
		if (RegexUtil.isNull(action.getBankidtp())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), BANKIDTP);
		}
		if (RegexUtil.isNull(action.getBankidno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), BANKIDNO);
		}
		if (RegexUtil.isNull(action.getBankacnm())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(), BANKACNM);
		}
		/*
		 * 校验身份证
		 */
	}
	
	

	public void isMobile(String mobile) throws BizException {
		if (!RegexUtil.isMobile(mobile)) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(), MOBILE);
		}
	}

	public void isIdCardNo(String idCardNo) throws BizException {
		if (!RegexUtil.isIdCardNo(idCardNo)) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(), IDCARDNO);
		}
	}



}
