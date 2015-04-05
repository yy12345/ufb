package com.ufufund.ufb.model.enums;

public enum Invtp {

	PERSONAL("0"), //个人
	ORGANIZATION("1");//机构
	
	private String value;

	private Invtp(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}