package com.ufufund.ufb.enums;

public enum Apkind {

	LOGININ(1), //登录
	REGISTER(2);//注册
	
	private int value;

	private Apkind(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
