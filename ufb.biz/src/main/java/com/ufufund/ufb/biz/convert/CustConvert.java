package com.ufufund.ufb.biz.convert;

import com.ufufund.ufb.action.LoginAction;
import com.ufufund.ufb.action.OpenAccountAction;
import com.ufufund.ufb.model.model.Custinfo;

public class CustConvert {
  
	public static Custinfo convertCustinfo(LoginAction loginAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(loginAction.getLoginCode());
		custinfo.setPasswd(loginAction.getLoginPassword());
		return custinfo;
	}
	public static Custinfo convertCustinfo(OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		return custinfo;
		
	}
}
