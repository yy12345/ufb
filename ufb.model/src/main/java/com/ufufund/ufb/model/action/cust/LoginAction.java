package com.ufufund.ufb.model.action.cust;

import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.enums.Level;

public class LoginAction  {

	
	private String loginCode;
	private String loginPassword;
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	@Override
	public String toString() {
		return "LoginAction [loginCode=" + loginCode + ", loginPassword=" + loginPassword + "]";
	}
	

	


	
	
	
}
