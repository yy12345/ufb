package com.ufufund.ufb.model.enums;


public enum Merchant {

	//PERSONAL("0"), //个人
	HFT_FUND("1");//海富通
	
	private String value;

	private Merchant(String value) {
		this.value = value;
	}

	public String Value() {
		return value;
	}

}