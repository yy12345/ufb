package com.ufufund.ufb.model.action.cust;


public class ChangePasswordAction {

	private String custno;
	private String loginPassword;
	private String loginPassword2;//确认密码
	
	
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
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
	@Override
	public String toString() {
		return "ChangePasswordAction [custno=" + custno + ", loginPassword=" + loginPassword + ", loginPassword2=" + loginPassword2 + "]";
	}
	
	
	
	
	
	
}
