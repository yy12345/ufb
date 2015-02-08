package com.ufufund.manager.validator;



import org.springframework.beans.factory.annotation.Autowired;

import com.ufufund.action.LoginIn;
import com.ufufund.common.DictManager;
import com.ufufund.common.RegexUtil;
import com.ufufund.dataobject.ParameterDO;
import com.ufufund.exception.BizException;


public class CustManagerValidator {
	
	@Autowired
	private DictManager dictManager;
	
	public CustManagerValidator(){
		
	}
	public String[] getErrorInfo(String errorCode){
		String[] s = new String[2];
		ParameterDO parameterDO  = dictManager.getErrorInfo(errorCode);
		s[0] = parameterDO.getPmco();
		s[1] = parameterDO.getPmnm();
		return s;
	}
	
	public void validator(LoginIn loginIn){
		if(RegexUtil.isNull(loginIn.getLoginType())){
			throw new BizException(this.getErrorInfo(""));
		}
		if(RegexUtil.isNull(loginIn.getLoginCode())){
			throw new BizException(this.getErrorInfo(""));		
		}
		if(RegexUtil.isNull(loginIn.getLoginPassword())){
			throw new BizException(this.getErrorInfo(""));
		}
	}
	
}
