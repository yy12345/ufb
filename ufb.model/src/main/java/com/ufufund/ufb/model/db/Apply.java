package com.ufufund.ufb.model.db;

import java.io.Serializable;

public class Apply extends TradeRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	// 交易密码
	private String tradePwd;
	// 银行编码
	private String bankno;
	// 银行卡id
	private String bankid;
	
	private String level;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTradePwd() {
		return tradePwd;
	}

	public void setTradePwd(String tradePwd) {
		this.tradePwd = tradePwd;
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	@Override
	public String toString() {
		return "ApplyVo [tradePwd=" + tradePwd + ", bankno=" + bankno
				+ ", bankid=" + bankid + ", toString()=" + super.toString()
				+ "]";
	}
	
}
