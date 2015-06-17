package com.ufufund.ufb.model.enums;

/**
 * 常用基金交易基础信息
 * @author ayis
 * 2015年5月22日
 */
public enum BasicFundinfo {

	YFB("519505","0","0");
	
	private String fundCode;
	private String shareClass;   // 收费方式
	private String dividMethod; // 默认分红方式
	
	private BasicFundinfo(String fundCode, String shareClass, String dividMethod){
		this.fundCode = fundCode;
		this.shareClass = shareClass;
		this.dividMethod = dividMethod;
	}

	public String getFundCode() {
		return fundCode;
	}

	public String getShareClass() {
		return shareClass;
	}

	public String getDividMethod() {
		return dividMethod;
	}
	
}
