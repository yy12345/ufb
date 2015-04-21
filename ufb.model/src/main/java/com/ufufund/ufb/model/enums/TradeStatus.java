package com.ufufund.ufb.model.enums;

public enum TradeStatus {

	N("N", "初始化"),
	I("I", "处理中"),
	Y("Y", "成功"),
	F("F", "失败");
	
	private String value;
	private String desc;
	
	private TradeStatus(String value, String desc){
		this.value = value;
		this.desc = desc;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getDesc(){
		return desc;
	}
}
