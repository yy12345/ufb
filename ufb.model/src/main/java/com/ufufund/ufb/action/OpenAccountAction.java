package com.ufufund.ufb.action;

import com.ufufund.ufb.enums.Apkind;
import com.ufufund.ufb.enums.Idtp;
import com.ufufund.ufb.enums.Invtp;

public class OpenAccountAction  {
	/*
	 * 不需要填写
	 */
	private Invtp invtp;  //用户类型
	private String intnm;  //用户姓名
	private Idtp idtp;   //证件类型
	private String idno;   //证件号码
	private String tradepwd;
	
	private String tradepwd2;//交易密码
	private String bankno;  //银行编码
	private String bankacco; //银行卡号
	private String bankidtp; //'银行证件类型',  默认身份证
	private String bankidno; // '银行证件号码', 默认idno
	
	private String bankacnm; //'银行开户户名',  默认intnm
	
	public String getIntnm() {
		return intnm;
	}
	public void setIntnm(String intnm) {
		this.intnm = intnm;
	}

	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getTradepwd() {
		return tradepwd;
	}
	public void setTradepwd(String tradepwd) {
		this.tradepwd = tradepwd;
	}
	public String getTradepwd2() {
		return tradepwd2;
	}
	public void setTradepwd2(String tradepwd2) {
		this.tradepwd2 = tradepwd2;
	}
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
	public Invtp getInvtp() {
		return invtp;
	}
	public void setInvtp(Invtp invtp) {
		this.invtp = invtp;
	}
	public Idtp getIdtp() {
		return idtp;
	}
	public void setIdtp(Idtp idtp) {
		this.idtp = idtp;
	}
	
	
	
	
}
