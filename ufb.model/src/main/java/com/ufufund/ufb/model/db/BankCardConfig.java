package com.ufufund.ufb.model.db;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BankCardConfig {
	
	private String bankNo;
	
	private BigDecimal perLimit;
	
	private BigDecimal limitPerDay;
	
	private String limitDesc;
	
	private String recharge;
	
	private String encash;// 01-支持，其它不支持
	
	private String quickEncash;// 01-支持，其它不支持
	
	private String autoRecharge;
	
	private String guaranteRecharge;
	
	private boolean b2c;
}
