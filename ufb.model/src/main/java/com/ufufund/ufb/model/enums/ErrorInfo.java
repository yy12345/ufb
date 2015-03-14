package com.ufufund.ufb.model.enums;


public enum ErrorInfo {
    
	NECESSARY_EMPTY("NECESSARY_EMPTY"),
	NOT_EQUALS_PASSWORD("NOT_EQUALS_PASSWORD"),
	FIELD_FORMAT_WRONG("FIELD_FORMAT_WRONG"),	
	
	WRONG_LOGIN_CODE("WRONG_LOGIN_CODE"),
	WRONG_LOGIN_PASSWORD("WRONG_LOGIN_PASSWORD"),
	WRONG_LOGIN_IDCARDNO("WRONG_LOGIN_IDCARDNO"),
	NO_IDCARDNO("NO_IDCARDNO"),
	SYSTEM_ERROR("SYSTEM_ERROR")
	;
	
	
	
	//WRONG_PASSWORD("WRONG_IDCARDNO"),
	//WRONG_MOBILE("WRONG_MOBILE");
	//个人
	//ORGANIZATION(2);//机构
	
	private String errorCode;

	private ErrorInfo(String errorCode) {
		this.errorCode = errorCode;
	}

	public String value() {
		return errorCode;
	}

}