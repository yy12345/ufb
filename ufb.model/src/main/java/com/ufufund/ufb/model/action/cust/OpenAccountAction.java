package com.ufufund.ufb.model.action.cust;

import com.ufufund.ufb.model.action.CommonAction;
import com.ufufund.ufb.model.enums.Merchant;

public class OpenAccountAction extends CommonAction {

	private String reqSeq; // 绑卡开户顺序号 1初始化、2鉴权、3验证、4开户
	private String custno;
	private String fundcorpno;
	private String invnm; // 用户姓名',
	private String idno; // 证件号码',
	private String tradepwd; // 交易密码，md5密文',
	private String tradepwd2; // 交易密码，md5密文',
	private String bankno; // 银行编码
	private String bankacnm; // 银行开户户名
	private String bankidtp; // 银行证件类型
	private String bankidno; // 银行证件号码
	private String bankacco; // 银行卡号
	private String bankmobile; //
	private String mobileAutoCode; // 手机验证码
	private Merchant merchant = Merchant.HFT_FUND; // 默认开户机构 海富通
	private String otherserial; // 对方序列号
	private String protocolno; // 银行协议编号
	private String serialno; // 流水号
	private String accoreqSerial; // 请求序列号
	private String transactionAccountID;
	private String organization; // 幼教机构名称
	private String business; // 营业执照注册号
	private String invtp;
	private String level;
	private String openaccount; //是否已开户 
	
	public String getOpenaccount() {
		return openaccount;
	}

	public void setOpenaccount(String openaccount) {
		this.openaccount = openaccount;
	}

	public String getReqSeq() {
		return reqSeq;
	}

	public void setReqSeq(String reqSeq) {
		this.reqSeq = reqSeq;
	}

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getFundcorpno() {
		return fundcorpno;
	}

	public void setFundcorpno(String fundcorpno) {
		this.fundcorpno = fundcorpno;
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

	public String getInvtp() {
		return invtp;
	}

	public void setInvtp(String invtp) {
		this.invtp = invtp;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
