package com.ufufund.ufb.biz.convert;

import com.ufufund.ufb.action.LoginAction;
import com.ufufund.ufb.enums.Apkind;
import com.ufufund.ufb.enums.TableName;
import com.ufufund.ufb.model.model.Changerecordinfo;
import com.ufufund.ufb.model.model.Custinfo;
import com.ufufund.ufb.model.model.Fdacfinalresult;

public class CustConvert {
  

	public static Custinfo convertCustinfo(LoginAction loginAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(loginAction.getLoginCode());
		custinfo.setPasswd(loginAction.getLoginPassword());
		
		return custinfo;
	}
//	public static Custinfo convertCustinfo(CustinfoAction custinfoAction){
//		Custinfo custinfo = new Custinfo();
////		private Invtp invtp;  //用户类型
////		private String intnm;  //用户姓名
////		private Idtp idtp;   //证件类型
////		private String idno;   //证件号码
////		private String tradepwd;
//		return custinfo;
//	}
	
	public static Changerecordinfo convertChangerecordinfo(Custinfo custinfo){
		Changerecordinfo changerecordinfo = new Changerecordinfo();
		changerecordinfo.setCustno(custinfo.getCustno());
		changerecordinfo.setRecordafter(custinfo.toString());
		changerecordinfo.setTablename(TableName.CUSTINFO.value());
		return changerecordinfo;
		
	}
//	public static Fdacfinalresult convertFdacfinalresult(Custinfo custinfo){
//		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
//		fdacfinalresult.setCustno(custinfo.getCustno());//char(10) not null comment '客户编号',
//		return fdacfinalresult;
//		
//	}
}
