package com.ufufund.ufb.model.enums;

/**
 * 性别定义
 */
public enum SexEnum {
	
	Man("1", "男"),
	Female("2", "女");
	
	private String key;
	private String value;
	
	private SexEnum(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public String getKey(){
		return key;
	}
	public String getValue(){
		return value;
	}
}
