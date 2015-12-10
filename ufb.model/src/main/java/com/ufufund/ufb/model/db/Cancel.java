package com.ufufund.ufb.model.db;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Cancel extends TradeRequest {

	private String oldserialno;
	// 交易密码
	private String tradePwd;
	// 银行编码
	private String bankno;
	// 银行卡id
	private String bankid;

	private String canceldt;
	private String canceltm;
	private String cancelst;
	private String ofundcode;
	private BigDecimal subquty;
	private BigDecimal subamt;
	private String payst;
	private String paytype;
}
