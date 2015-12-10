package com.ufufund.ufb.model.db;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CancelRequest{

	private String custno;
	private String serialno;
	private String sheetserialno;
	private String oldserialno;
	private String taserialno;
	private String fundcorpno;
	private String tradeacco;
	private String apkind;
	private String fundcode;
	private String ofundcode;
	private String canceldt;
	private String canceltm;
	private BigDecimal subquty;
	private BigDecimal subamt;
	private String cancelst;
	private String paytype; // 支付方式
	private String payst; // 支付状态
	private String transt;
	private String updatetime;
	private String accptmd; // 受理方式
}
