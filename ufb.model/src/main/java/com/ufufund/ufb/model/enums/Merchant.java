package com.ufufund.ufb.model.enums;

public enum Merchant {

	HFT_FUND("00000001");// 海富通

	private String value;

	private Merchant(String value) {
		this.value = value;
	}

	public String Value() {
		return value;
	}

}