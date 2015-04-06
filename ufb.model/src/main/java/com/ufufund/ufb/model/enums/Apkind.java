package com.ufufund.ufb.model.enums;

public enum Apkind {

	LOGININ("001"), //登录
	REGISTER("002"),//注册
	CHANGE_PASSWORD("003"),//修改密码
	OPEN_ACCOUNT("004");//开户 
	
	
	private String value;

	private Apkind(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
