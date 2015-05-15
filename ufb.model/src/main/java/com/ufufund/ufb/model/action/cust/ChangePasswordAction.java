package com.ufufund.ufb.model.action.cust;

import com.ufufund.ufb.model.action.CommonAction;

public class ChangePasswordAction extends CommonAction {

	private String custno;
	private String mobile;
	private String password0;
	private String password1;
	private String password2;
	private String actionType;

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword0() {
		return password0;
	}

	public void setPassword0(String password0) {
		this.password0 = password0;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}
