package com.ufufund.ufb.biz.convert;

import com.ufufund.ufb.model.action.OpenAccountAction;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.TableName;

public class BankConvert {
  
	public static Bankcardinfo converBankcardinfo(OpenAccountAction openAccountAction){
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo.setCustno(openAccountAction.getCustno());
		bankcardinfo.setBankno(openAccountAction.getBankno());
		bankcardinfo.setBankacco(openAccountAction.getBankacco());
		bankcardinfo.setBankidtp(openAccountAction.getBankidtp());// char(1) default null comment '银行证件类型',
		bankcardinfo.setBankidno(openAccountAction.getBankidno());// varchar(30) default null comment '银行证件号码'
		bankcardinfo.setBankacnm(openAccountAction.getBankacnm());// varchar(60) default null comment '银行开户户名',
		
		
		return bankcardinfo;
	}
	
	public static Changerecordinfo convertBankcardinfo(Bankcardinfo bankcardinfo){
		Changerecordinfo changerecordinfo = new Changerecordinfo();
		changerecordinfo.setCustno(bankcardinfo.getCustno());
		changerecordinfo.setRecordafter(bankcardinfo.toString());
		changerecordinfo.setTablename(TableName.BANKCARDINFO.value());
		return changerecordinfo;
	}
	
	
	public static Changerecordinfo convertTradeaccoinfo(Tradeaccoinfo tradeaccoinfo ){
		Changerecordinfo changerecordinfo = new Changerecordinfo();
		changerecordinfo.setCustno(tradeaccoinfo.getCustno());
		changerecordinfo.setRecordafter(tradeaccoinfo.toString());
		changerecordinfo.setTablename(TableName.TRADEACCOINFO.value());
		return changerecordinfo;
	}
//	public static Tradeaccoinfo converTradeaccoinfo(OpenAccountAction openAccountAction){
//		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
//		tradeaccoinfo.setCustno();// char(10) not null comment '客户编号',
//		tradeaccoinfo.setFundcorpno();// char(2) not null default '' comment '交易账号类型：归属基金公司',
//		tradeaccoinfo.setBankserialid();// varchar(24) not null comment '银行账号serialid(银行账号表pk)',
//		tradeaccoinfo.setTradeacco();// varchar(17) not null comment '交易账号(基金公司返回的交易账号)',
//		return tradeaccoinfo;
//	}
	
}
