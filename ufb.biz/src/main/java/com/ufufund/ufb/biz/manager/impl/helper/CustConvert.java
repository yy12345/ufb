package com.ufufund.ufb.biz.manager.impl.helper;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.enums.Invtp;


public class CustConvert {
  

	public static Custinfo convertCustinfo(RegisterAction registerAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(registerAction.getLoginCode());
		custinfo.setPasswd(registerAction.getLoginPassword());
		custinfo.setInvtp(registerAction.getInvtp().getValue());
		custinfo.setLevel(registerAction.getLevel().getValue());
		custinfo.setOrganization(registerAction.getOrganization());
		custinfo.setBusiness(registerAction.getBusiness());
		return custinfo;
	}

	
//	public static Changerecordinfo convertChangerecordinfo(Custinfo custinfo){
//		Changerecordinfo changerecordinfo = new Changerecordinfo();
//		changerecordinfo.setCustno(custinfo.getCustno());
//		changerecordinfo.setRecordafter(custinfo.toString());
//		changerecordinfo.setTablename(TableName.CUSTINFO.value());
//		return changerecordinfo;
//	}
	
	

	public static Custinfo convertOpenAccountAction(OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(openAccountAction.getCustno());
		custinfo.setInvnm(openAccountAction.getInvnm());
		custinfo.setIdno(openAccountAction.getIdno());
		custinfo.setTradepwd(openAccountAction.getTradepwd());
		custinfo.setInvtp(Invtp.PERSONAL.getValue());
		custinfo.setIdtp(Constant.IDTP$0);
		custinfo.setOpenaccount("Y");
		return custinfo;
	}
	
	
	
	
	
	
	

	
}
