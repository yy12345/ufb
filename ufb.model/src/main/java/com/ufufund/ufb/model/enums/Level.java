package com.ufufund.ufb.model.enums;

public enum Level {

	PERSONAL("0"), //个人
	OPERATOR("1");//经办人
	
	private String value;

	private Level(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}