package com.ufufund.ufb.model.action.cust;

import com.ufufund.ufb.model.action.CommonAction;


public class ChangePasswordAction  extends CommonAction {

	private String mobile;
	private String loginPassword;
	private String loginPassword2;//确认密码
	
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getLoginPassword2() {
		return loginPassword2;
	}
	public void setLoginPassword2(String loginPassword2) {
		this.loginPassword2 = loginPassword2;
	}

	
	
	
	
	
	
	
	
}
