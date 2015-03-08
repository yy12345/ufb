package com.ufufund.ufb.enums;

public enum Idtp {

	IDCARD(0), //身份证
	OTHER(1); //其他
	
	private int value;

	private Idtp(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}