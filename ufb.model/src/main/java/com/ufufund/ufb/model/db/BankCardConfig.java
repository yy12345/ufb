package com.ufufund.ufb.model.db;

import java.io.Serializable;
import java.math.BigDecimal;

public class BankCardConfig implements Serializable{

	private static final long serialVersionUID = -6326117612335723497L;
	
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

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public BigDecimal getPerLimit() {
		return perLimit;
	}

	public void setPerLimit(BigDecimal perLimit) {
		this.perLimit = perLimit;
	}

	public String getLimitDesc() {
		return limitDesc;
	}

	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
	}

	public BigDecimal getLimitPerDay() {
		return limitPerDay;
	}

	public void setLimitPerDay(BigDecimal limitPerDay) {
		this.limitPerDay = limitPerDay;
	}

	public boolean isB2c() {
		return b2c;
	}

	public void setB2c(boolean b2c) {
		this.b2c = b2c;
	}

	public String getRecharge() {
		return recharge;
	}

	public void setRecharge(String recharge) {
		this.recharge = recharge;
	}

	public String getAutoRecharge() {
		return autoRecharge;
	}

	public void setAutoRecharge(String autoRecharge) {
		this.autoRecharge = autoRecharge;
	}

	public String getGuaranteRecharge() {
		return guaranteRecharge;
	}

	public void setGuaranteRecharge(String guaranteRecharge) {
		this.guaranteRecharge = guaranteRecharge;
	}

	public String getEncash() {
		return encash;
	}

	public void setEncash(String encash) {
		this.encash = encash;
	}

	public String getQuickEncash() {
		return quickEncash;
	}

	public void setQuickEncash(String quickEncash) {
		this.quickEncash = quickEncash;
	}
}
