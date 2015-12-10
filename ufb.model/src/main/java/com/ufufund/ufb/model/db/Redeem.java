package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class Redeem extends TradeRequest {
	// 银行卡id
	private String bankid;
	// 交易密码
	private String tradePwd;
	
	private String level;
}
