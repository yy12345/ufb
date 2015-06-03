package com.ufufund.ufb.model.enums;

public enum AutoTradeType {

	AUTORECHARGE("51"),  //自动充值
	AUTOWITHDRAWAL("52") //自动取现
	;
	private String value;

	private AutoTradeType(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

//	public static void main(String[] args) {
//		AutoTradeType autoTradeType = AutoTradeType.AUTORECHARGE;
//		System.out.println(autoTradeType);
//	}
}


