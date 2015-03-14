package com.ufufund.ufb.enums;

public enum Apkind {

	LOGININ("1"), //登录
	REGISTER("2"),//注册
	CHANGE_PASSWORD("3");//修改密码
	
	private String value;

	private Apkind(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
