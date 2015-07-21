package com.ufufund.ufb.model.action.cust;

import com.ufufund.ufb.model.action.CommonAction;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.enums.Level;

public class RegisterAction extends CommonAction {

	private String custno;
	private String logincode;
	private String loginpwd;
	private String loginpwd2;// 确认密码
	private Invtp invtp = Invtp.PERSONAL; // 用户类型
	private Level level = Level.PERSONAL;// 级别
	private String custst;
	private String orgnm;// 机构名称
	private String orgbusiness;// 营业执照

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getLoginpwd2() {
		return loginpwd2;
	}

	public void setLoginpwd2(String loginpwd2) {
		this.loginpwd2 = loginpwd2;
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

	public String getCustst() {
		return custst;
	}

	public void setCustst(String custst) {
		this.custst = custst;
	}

	public String getOrgnm() {
		return orgnm;
	}

	public void setOrgnm(String orgnm) {
		this.orgnm = orgnm;
	}

	public String getOrgbusiness() {
		return orgbusiness;
	}

	public void setOrgbusiness(String orgbusiness) {
		this.orgbusiness = orgbusiness;
	}

	// @Override
	// public String toString() {
	// return "RegisterAction [loginCode=" + loginCode + ", loginPassword=" +
	// loginPassword + ", loginPassword2=" + loginPassword2 + ", invtp=" +
	// invtp.getValue() + ", organization=" + organization
	// + ", level=" + level.getValue() + ", business=" + business + "]";
	// }

}
