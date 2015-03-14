package com.ufufund.ufb.model.action;


public class OpenAccountAction  {
	
	/*
	 * 客户已经具有身份证账号 不需要填写以下信息
	 * 如果没有 则必须填写
	 */
//	private Invtp invtp = Invtp.PERSONAL;  //用户类型
//	private Idtp idtp = Idtp.IDCARD;   //证件类型

	
	private String invnm;//用户姓名',
	private String idno;//证件号码',
	private String tradepwd;//交易密码，md5密文',
	/*
	 * 绑卡必填信息
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
	public String getInvnm() {
		return invnm;
	}
	public void setInvnm(String invnm) {
		this.invnm = invnm;
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
	
	
	

	
	
	
	
}
