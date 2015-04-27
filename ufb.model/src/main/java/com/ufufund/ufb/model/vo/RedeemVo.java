package com.ufufund.ufb.model.vo;

import java.io.Serializable;

import com.ufufund.ufb.model.db.TradeRequest;

public class RedeemVo extends TradeRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	// 交易密码
	private String tradePwd;

	public String getTradePwd() {
		return tradePwd;
	}

	public void setTradePwd(String tradePwd) {
		this.tradePwd = tradePwd;
	}

	@Override
	public String toString() {
		return "RedeemVo [tradePwd=" + tradePwd + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
