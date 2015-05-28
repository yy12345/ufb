package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class Tradeaccoinfo extends PrintableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String accoid;// varchar(24) not null comment '交易账号编号(本地生成的)',
	private String custno;// char(10) not null comment '客户编号',
	private String fundcorpno;// char(2) not null default '' comment '交易账号类型：归属基金公司',
	private String bankserialid;// varchar(24) not null comment '银行账号serialid(银行账号表pk)',
	private String tradeacco;// varchar(17) not null comment '交易账号(基金公司返回的交易账号)',
	private String tradeaccost="N";// char(1) not null comment '交易账号状态：n-正常 c-撤销',
	private String opendt;// char(8) default null comment '开户日期',
	private String closedt;// char(8) default null comment '销户日期',

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getFundcorpno() {
		return fundcorpno;
	}

	public void setFundcorpno(String fundcorpno) {
		this.fundcorpno = fundcorpno;
	}

	public String getBankserialid() {
		return bankserialid;
	}

	public void setBankserialid(String bankserialid) {
		this.bankserialid = bankserialid;
	}

	public String getTradeacco() {
		return tradeacco;
	}

	public void setTradeacco(String tradeacco) {
		this.tradeacco = tradeacco;
	}

	public String getTradeaccost() {
		return tradeaccost;
	}

	public void setTradeaccost(String tradeaccost) {
		this.tradeaccost = tradeaccost;
	}

	public String getOpendt() {
		return opendt;
	}

	public void setOpendt(String opendt) {
		this.opendt = opendt;
	}

	public String getClosedt() {
		return closedt;
	}

	public void setClosedt(String closedt) {
		this.closedt = closedt;
	}

	public String getAccoid() {
		return accoid;
	}

	public void setAccoid(String accoid) {
		this.accoid = accoid;
	}

	
}
