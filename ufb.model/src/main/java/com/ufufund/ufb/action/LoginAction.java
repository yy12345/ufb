package com.ufufund.ufb.action;

import com.ufufund.ufb.enums.Apkind;
import com.ufufund.ufb.enums.Invtp;

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
	
	
//	public String getIdentifyCode() {
//		return identifyCode;
//	}
//	public void setIdentifyCode(String identifyCode) {
//		this.identifyCode = identifyCode;
//	}
//	public String getMobileCode() {
//		return mobileCode;
//	}
//	public void setMobileCode(String mobileCode) {
//		this.mobileCode = mobileCode;
//	}
//	public String getSessionidentifyCode() {
//		return sessionidentifyCode;
//	}
//	public void setSessionidentifyCode(String sessionidentifyCode) {
//		this.sessionidentifyCode = sessionidentifyCode;
//	}
//	public String getSessionmobileCode() {
//		return sessionmobileCode;
//	}
//	public void setSessionmobileCode(String sessionmobileCode) {
//		this.sessionmobileCode = sessionmobileCode;
//	}

	
	
	
}