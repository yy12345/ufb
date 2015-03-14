package com.ufufund.ufb.biz.validator;

import com.ufufund.ufb.action.LoginAction;
import com.ufufund.ufb.action.OpenAccountAction;
import com.ufufund.ufb.biz.common.ValidatorCommon;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.enums.Apkind;
import com.ufufund.ufb.model.model.Custinfo;

public class CustManagerValidator extends ValidatorCommon {

	public void necessaryLoginAction(LoginAction action) throws Exception {
		// TODO Auto-generated method stub
		if (RegexUtil.isNull(action.getLoginCode())) {
			// String[] msg = this.getErrorInfo("");
			// throw new BizException(msg[0], msg[1]);
			throw new Exception();
		}
		if (RegexUtil.isNull(action.getLoginPassword())) {
			// String[] msg = this.getErrorInfo("");
			// throw new BizException(msg[0], msg[1]);
			throw new Exception();
		}
	}

	public void necessaryRegister(LoginAction action) throws Exception {
		if (RegexUtil.isNull(action.getLoginPassword2())) {
			// String[] msg = this.getErrorInfo("");
			// throw new BizException(msg[0], msg[1]);
			throw new Exception();
		}
	}

	@Override
	public void validator(Object obj) throws Exception {
		// TODO Auto-generated method stub
		if (obj instanceof LoginAction) {
			LoginAction action = (LoginAction) obj;
			if (action.getLoginType() == Apkind.LOGININ) {
				this.necessaryLoginAction(action);
				//ValidatorCommon.checkIdentifyCode(action.getIdentifyCode(), action.getSessionidentifyCode());
			} else if (action.getLoginType() == Apkind.REGISTER) {
				this.necessaryLoginAction(action);
				this.necessaryRegister(action);
				if (!action.getLoginPassword().equals(action.getLoginPassword2())) {
					throw new Exception();
				}
				if (!RegexUtil.isMobile(action.getLoginCode())) {
					throw new Exception();
				}
				//ValidatorCommon.checkIdentifyCode(action.getIdentifyCode(), action.getSessionidentifyCode());
				//ValidatorCommon.checkmobileCode(action.getMobileCode(), action.getSessionmobileCode());
			}
		}else if(obj instanceof Custinfo){
			Custinfo action = (Custinfo) obj;
			this.necessaryUpdateCustinfo(action);
		}else if(obj instanceof OpenAccountAction){
			OpenAccountAction action = (OpenAccountAction) obj;
			this.necessaryOpenAccount(action);
		}

	}
	public void necessaryUpdateCustinfo(Custinfo action) throws Exception {
		// TODO Auto-generated method stub
		if (RegexUtil.isNull(action.getCustno())) {
			throw new Exception();
		}
		if (RegexUtil.isNull(action.getInvtp())) {
			throw new Exception();
		}
		if (RegexUtil.isNull(action.getInvnm())) {
			throw new Exception();
		}
		if (RegexUtil.isNull(action.getIdtp())) {
			throw new Exception();
		}
		if (RegexUtil.isNull(action.getIdno())) {
			throw new Exception();
		}
		if (RegexUtil.isNull(action.getTradepwd())) {
			throw new Exception();
		}
		/*
		 * WEB层判断
		 */
//		if (RegexUtil.isNull(action.getTradepwd2())) {
//			throw new Exception();
//		}	
//		if (!action.getTradepwd().equals(action.getTradepwd2())) {
//			throw new Exception();
//		}
	}
	
	
	
	public void necessaryOpenAccount(OpenAccountAction action) throws Exception {
		// TODO Auto-generated method stub
		if (RegexUtil.isNull(action.getBankno())) {
			throw new Exception();
		}
		if (RegexUtil.isNull(action.getBankacco())) {
			throw new Exception();
		}
		if (RegexUtil.isNull(action.getBankidtp())) {
			throw new Exception();
		}	
		if (RegexUtil.isNull(action.getBankidno())) {
			throw new Exception();
		}
		
		if (RegexUtil.isNull(action.getBankacnm())) {
			throw new Exception();
		}
		/*
		 * 校验身份证
		 */
	}

}
