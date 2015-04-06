package com.ufufund.ufb.model.vo;

import java.io.Serializable;

public class CancelVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String businType;
	private String transacco;
	private String serialNo;
	private String originSerialNo;
	
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
	public String getOriginSerialNo() {
		return originSerialNo;
	}
	public void setOriginSerialNo(String originSerialNo) {
		this.originSerialNo = originSerialNo;
	}
	@Override
	public String toString() {
		return "CancelVo [businType=" + businType + ", transacco=" + transacco
				+ ", serialNo=" + serialNo + ", originSerialNo="
				+ originSerialNo + "]";
	}
	
}
