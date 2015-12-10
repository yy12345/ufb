package com.ufufund.ufb.model.db;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TradeQutyChg{

	private String serialno;
	private String custno;
	private String fundcorpno;
	private String tradeacco;
	private String apkind;
	private String appdate;
	private String workdate;
	private String fundcode;
	private BigDecimal total = new BigDecimal("0.00");
	private BigDecimal available = new BigDecimal("0.00");
	private BigDecimal frozen = new BigDecimal("0.00");
	private String oldserialno;
	private String updatetime;
}
