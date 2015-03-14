package com.ufufund.ufb.model.enums;


public enum ErrorInfo {

	WRONG_MOBILE("WRONG_MOBILE"); //个人
	//ORGANIZATION(2);//机构
	
	private String errorCode;

	private ErrorInfo(String errorCode) {
		this.errorCode = errorCode;
	}

	public String value() {
		return errorCode;
	}

}