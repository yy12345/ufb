package com.ufufund.ufb.model.action.cust;

import com.ufufund.ufb.model.action.CommonAction;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.enums.Level;

public class RegisterAction extends CommonAction {

	private String loginCode;
	private String loginPassword;
	private String loginPassword2;// 确认密码
	private Invtp invtp = Invtp.PERSONAL; // 用户类型
	private Level level = Level.PERSONAL;// 级别
	private String organization;// 机构名称
	private String business;// 营业执照

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

	public String getLoginPassword2() {
		return loginPassword2;
	}

	public void setLoginPassword2(String loginPassword2) {
		this.loginPassword2 = loginPassword2;
	}

	public Invtp getInvtp() {
		return invtp;
	}

	public void setInvtp(Invtp invtp) {
		this.invtp = invtp;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	// @Override
	// public String toString() {
	// return "RegisterAction [loginCode=" + loginCode + ", loginPassword=" +
	// loginPassword + ", loginPassword2=" + loginPassword2 + ", invtp=" +
	// invtp.getValue() + ", organization=" + organization
	// + ", level=" + level.getValue() + ", business=" + business + "]";
	// }

}
