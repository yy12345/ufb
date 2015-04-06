package com.ufufund.ufb.model.vo;

import java.io.Serializable;

public class RedeemVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String businType ;
	private String transacco ;
	private String serialNo  ;
	private String fundCode  ;
	private String amount    ;
	private String shareClass;
	
	public String getBusinType() {
		return businType;
	}
	public void setBusinType(String businType) {
		this.businType = businType;
	}
	public String getTransacco() {
		return transacco;
	}
	public void setTransacco(String transacco) {
		this.transacco = transacco;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getShareClass() {
		return shareClass;
	}
	public void setShareClass(String shareClass) {
		this.shareClass = shareClass;
	}
	@Override
	public String toString() {
		return "RedeemVo [businType=" + businType + ", transacco=" + transacco
				+ ", serialNo=" + serialNo + ", fundCode=" + fundCode
				+ ", amount=" + amount + ", shareClass=" + shareClass + "]";
	}
	
	
}
