package com.ufufund.ufb.action;

import com.ufufund.ufb.enums.Apkind;
import com.ufufund.ufb.enums.Idtp;
import com.ufufund.ufb.enums.Invtp;

public class OpenAccountAction  {
	
	/*
	 * 客户已经具有身份证账号 不需要填写以下信息
	 */
	
	
	private String custno;
	
	private String bankno;  //银行编码
	private String bankacco; //银行卡号
	private String bankidtp; //'银行证件类型',  默认身份证
	private String bankidno; // '银行证件号码', 默认idno
	private String bankacnm; //'银行开户户名',  默认intnm
	
	
	public String getBankno() {
		return bankno;
	}
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
	public String getBankacco() {
		return bankacco;
	}
	public void setBankacco(String bankacco) {
		this.bankacco = bankacco;
	}
	public String getBankidtp() {
		return bankidtp;
	}
	public void setBankidtp(String bankidtp) {
		this.bankidtp = bankidtp;
	}
	public String getBankidno() {
		return bankidno;
	}
	public void setBankidno(String bankidno) {
		this.bankidno = bankidno;
	}
	public String getBankacnm() {
		return bankacnm;
	}
	public void setBankacnm(String bankacnm) {
		this.bankacnm = bankacnm;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	

	
	
	
	
}
