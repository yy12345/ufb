package com.ufufund.ufb.biz.convert;

import com.ufufund.ufb.action.LoginAction;
import com.ufufund.ufb.action.OpenAccountAction;
import com.ufufund.ufb.enums.Idtp;
import com.ufufund.ufb.enums.Invtp;
import com.ufufund.ufb.model.model.Bankcardinfo;
import com.ufufund.ufb.model.model.Custinfo;
import com.ufufund.ufb.model.model.Tradeaccoinfo;

public class BankConvert {
  
	public static Bankcardinfo converBankcardinfo(OpenAccountAction openAccountAction){
		Bankcardinfo bankcardinfo = new Bankcardinfo();
//		private Invtp invtp;  //用户类型
//		private String intnm;  //用户姓名
//		private Idtp idtp;   //证件类型
//		private String idno;   //证件号码
//		private String tradepwd;
		return bankcardinfo;
	}
	
	public static Tradeaccoinfo converTradeaccoinfo(OpenAccountAction openAccountAction){
		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
//		private Invtp invtp;  //用户类型
//		private String intnm;  //用户姓名
//		private Idtp idtp;   //证件类型
//		private String idno;   //证件号码
//		private String tradepwd;
		return tradeaccoinfo;
	}
	
}
