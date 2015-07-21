package com.ufufund.ufb.model.action.cust;

import com.ufufund.ufb.model.action.CommonAction;

public class OpenAccountAction extends CommonAction {
	
	private String reqseq; // 绑卡开户顺序号 1初始化、2鉴权、3验证、4开户
	private String fundcorpno;
	private String custno;
	private String invtp;
	private String level;
	private String tradepwd; // 交易密码，md5密文',
	private String tradepwd2; // 交易密码，md5密文',
	private String mobileautocode; // 手机验证码
	
	/** 接口必须 **/
	private String serialno; // 流水号
	private String accoreqserial; // 请求序列号
	private String transactionaccountid;
	private String otherserial; // 对方序列号
	private String protocolno; // 银行协议编号
	
	/** 个人 **/
	private String invnm; // 用户姓名',
	private String idno; // 证件号码',
	private String idtp;
	private String mobile;
	private String telno;
	private String emailadd;
	
	/** 机构信息 **/
	private String orgnm; // 幼教机构名称
	private String orgbusiness; // 营业执照注册号
	private String orgprovinceno; // 省份
	private String orgprovincenm;
	private String orgcityno; // 城市
	private String orgcitynm;
	private String orgadd; // 实际办学经营地址
	
	/** 银行 **/
	// 1
	private String bankno; // 银行编码
	private String bankacnm; // 银行开户户名
	private String bankidtp; // 银行证件类型
	private String bankidno; // 银行证件号码
	private String bankacco; // 银行卡号
	private String bankacco2; // 银行卡号
	private String bankmobile; //
	// 2
	private String bankprovinceno; // 省份
	private String bankprovincenm;
	private String bankcityno; // 城市
	private String bankcitynm;
	private String bankadd; // 支行网点
	
	/** 法人信息 **/
	private String rerpidtp; // 法人证件类型
	private String rerpidno; // 法人证件号码
	private String rerpvalidt; // 法人证件有效日期
	private String rerpnm; // 法人姓名

	private int hftfamilytradeaccoct;
	private int hftoperatortradeaccoct;
	private int hftorganizationtradeaccoct;
	private int cpfamilytradeaccoct;
	private int cporganizationtradeaccoct;
	
	public String getReqseq() {
		return reqseq;
	}
	public void setReqseq(String reqseq) {
		this.reqseq = reqseq;
	}
	public String getFundcorpno() {
		return fundcorpno;
	}
	public void setFundcorpno(String fundcorpno) {
		this.fundcorpno = fundcorpno;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
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
	public String getMobileautocode() {
		return mobileautocode;
	}
	public void setMobileautocode(String mobileautocode) {
		this.mobileautocode = mobileautocode;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getAccoreqserial() {
		return accoreqserial;
	}
	public void setAccoreqserial(String accoreqserial) {
		this.accoreqserial = accoreqserial;
	}
	public String getTransactionaccountid() {
		return transactionaccountid;
	}
	public void setTransactionaccountid(String transactionaccountid) {
		this.transactionaccountid = transactionaccountid;
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
	public String getIdtp() {
		return idtp;
	}
	public void setIdtp(String idtp) {
		this.idtp = idtp;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getEmailadd() {
		return emailadd;
	}
	public void setEmailadd(String emailadd) {
		this.emailadd = emailadd;
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
	public String getOrgprovinceno() {
		return orgprovinceno;
	}
	public void setOrgprovinceno(String orgprovinceno) {
		this.orgprovinceno = orgprovinceno;
	}
	public String getOrgprovincenm() {
		return orgprovincenm;
	}
	public void setOrgprovincenm(String orgprovincenm) {
		this.orgprovincenm = orgprovincenm;
	}
	public String getOrgcityno() {
		return orgcityno;
	}
	public void setOrgcityno(String orgcityno) {
		this.orgcityno = orgcityno;
	}
	public String getOrgcitynm() {
		return orgcitynm;
	}
	public void setOrgcitynm(String orgcitynm) {
		this.orgcitynm = orgcitynm;
	}
	public String getOrgadd() {
		return orgadd;
	}
	public void setOrgadd(String orgadd) {
		this.orgadd = orgadd;
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
	public String getBankacco2() {
		return bankacco2;
	}
	public void setBankacco2(String bankacco2) {
		this.bankacco2 = bankacco2;
	}
	public String getBankmobile() {
		return bankmobile;
	}
	public void setBankmobile(String bankmobile) {
		this.bankmobile = bankmobile;
	}
	public String getBankprovinceno() {
		return bankprovinceno;
	}
	public void setBankprovinceno(String bankprovinceno) {
		this.bankprovinceno = bankprovinceno;
	}
	public String getBankprovincenm() {
		return bankprovincenm;
	}
	public void setBankprovincenm(String bankprovincenm) {
		this.bankprovincenm = bankprovincenm;
	}
	public String getBankcityno() {
		return bankcityno;
	}
	public void setBankcityno(String bankcityno) {
		this.bankcityno = bankcityno;
	}
	public String getBankcitynm() {
		return bankcitynm;
	}
	public void setBankcitynm(String bankcitynm) {
		this.bankcitynm = bankcitynm;
	}
	public String getBankadd() {
		return bankadd;
	}
	public void setBankadd(String bankadd) {
		this.bankadd = bankadd;
	}
	public String getRerpidtp() {
		return rerpidtp;
	}
	public void setRerpidtp(String rerpidtp) {
		this.rerpidtp = rerpidtp;
	}
	public String getRerpidno() {
		return rerpidno;
	}
	public void setRerpidno(String rerpidno) {
		this.rerpidno = rerpidno;
	}
	public String getRerpvalidt() {
		return rerpvalidt;
	}
	public void setRerpvalidt(String rerpvalidt) {
		this.rerpvalidt = rerpvalidt;
	}
	public String getRerpnm() {
		return rerpnm;
	}
	public void setRerpnm(String rerpnm) {
		this.rerpnm = rerpnm;
	}
	public int getHftfamilytradeaccoct() {
		return hftfamilytradeaccoct;
	}
	public void setHftfamilytradeaccoct(int hftfamilytradeaccoct) {
		this.hftfamilytradeaccoct = hftfamilytradeaccoct;
	}
	public int getHftoperatortradeaccoct() {
		return hftoperatortradeaccoct;
	}
	public void setHftoperatortradeaccoct(int hftoperatortradeaccoct) {
		this.hftoperatortradeaccoct = hftoperatortradeaccoct;
	}
	public int getHftorganizationtradeaccoct() {
		return hftorganizationtradeaccoct;
	}
	public void setHftorganizationtradeaccoct(int hftorganizationtradeaccoct) {
		this.hftorganizationtradeaccoct = hftorganizationtradeaccoct;
	}
	public int getCpfamilytradeaccoct() {
		return cpfamilytradeaccoct;
	}
	public void setCpfamilytradeaccoct(int cpfamilytradeaccoct) {
		this.cpfamilytradeaccoct = cpfamilytradeaccoct;
	}
	public int getCporganizationtradeaccoct() {
		return cporganizationtradeaccoct;
	}
	public void setCporganizationtradeaccoct(int cporganizationtradeaccoct) {
		this.cporganizationtradeaccoct = cporganizationtradeaccoct;
	}
	
}
