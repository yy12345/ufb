package com.ufufund.ufb.model.action.cust;

import com.ufufund.ufb.model.action.CommonAction;
import com.ufufund.ufb.model.enums.Merchant;


public class OpenAccountAction extends CommonAction {
	
	/*
	 * 客户已经具有身份证账号 不需要填写以下信息
	 * 如果没有 则必须填写
	 */
//	private Invtp invtp = Invtp.PERSONAL;  //用户类型
//	private Idtp idtp = Idtp.IDCARD;   //证件类型

	/*
	 * 绑卡必填信息
	 */
	private String custno;
	
	private String invnm;//用户姓名',
	private String idno;//证件号码',
	private String tradepwd;//交易密码，md5密文',
	private String tradepwd2;//交易密码，md5密文',
	
	private String bankno;  //UFB银行编码
	private String bankacnm; //'银行开户户名',  默认intnm
	private String bankidtp; //UFB'银行证件类型',  默认身份证
	private String bankidno; //UFB '银行证件号码', 默认idno
	private String bankacco; //银行卡号
	private String bankmobile; //
	
	private String mobileAutoCode; //手机验证码
	
	//private String custst;
	private Merchant merchant = Merchant.HFT_FUND;//默认开户机构 海富通
	/*
	 * 不用填写
	 */
	
	private String otherserial;//对方序列号
	private String protocolno;//银行协议编号
	private String serialno;//varchar(24) not null comment '流水号'
	private String accoreqSerial;//请求序列号
	private String transactionAccountID;
	
	public String getOtherserial() {
		return otherserial;
	}
	public void setOtherserial(String otherserial) {
		this.otherserial = otherserial;
	}
	public String getProtocolno() {
		return protocolno;
	}
	public void setProtocolno(String protocolno) {
		this.protocolno = protocolno;
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
	public String getBankacnm() {
		return bankacnm;
	}
	public void setBankacnm(String bankacnm) {
		this.bankacnm = bankacnm;
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
	public String getBankacco() {
		return bankacco;
	}
	public void setBankacco(String bankacco) {
		this.bankacco = bankacco;
	}
	public String getBankmobile() {
		return bankmobile;
	}
	public void setBankmobile(String bankmobile) {
		this.bankmobile = bankmobile;
	}
//	public String getCustst() {
//		return custst;
//	}
//	public void setCustst(String custst) {
//		this.custst = custst;
//	}
	
	
	public String getMobileAutoCode() {
		return mobileAutoCode;
	}
	public void setMobileAutoCode(String mobileAutoCode) {
		this.mobileAutoCode = mobileAutoCode;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getAccoreqSerial() {
		return accoreqSerial;
	}
	public void setAccoreqSerial(String accoreqSerial) {
		this.accoreqSerial = accoreqSerial;
	}
	public String getTransactionAccountID() {
		return transactionAccountID;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		this.transactionAccountID = transactionAccountID;
	}
	
	
	
	
	


	
	
	
	

	
	
	
	
}
