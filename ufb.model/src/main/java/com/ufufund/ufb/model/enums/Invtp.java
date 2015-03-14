package com.ufufund.ufb.model.enums;

public enum Invtp {

	PERSONAL(1), //个人
	ORGANIZATION(2);//机构
	
	private int value;

	private Invtp(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}