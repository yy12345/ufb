package com.ufufund.ufb.model.action;

import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.Invtp;

public class LoginAction  {
	/*
	 * 不需要填写
	 */
	private Apkind loginType;
	
	private String loginCode;
	private String loginPassword;
//	private String identifyCode;//验证码
//	private String sessionidentifyCode;//验证码
	/*
	 * 登录不需
	 */
//	private String mobileCode;//验证码
//	private String sessionmobileCode;//验证码
	private String loginPassword2;//确认密码
	
	/*
	 * 
	 */
	private Invtp invtp = Invtp.PERSONAL ;  //用户类型
	
	public String getLoginPassword2() {
		return loginPassword2;
	}
	public void setLoginPassword2(String loginPassword2) {
		this.loginPassword2 = loginPassword2;
	}
	public Apkind getLoginType() {
		return loginType;
	}
	public void setLoginType(Apkind loginType) {
		this.loginType = loginType;
	}
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
	public Invtp getInvtp() {
		return invtp;
	}
	public void setInvtp(Invtp invtp) {
		this.invtp = invtp;
	}
	@Override
	public String toString() {
		return "LoginAction [loginType=" + loginType + ", loginCode=" + loginCode + ", loginPassword=" + loginPassword + ", loginPassword2=" + loginPassword2 + ", invtp=" + invtp + "]";
	}
	
	


	
	
	
}
