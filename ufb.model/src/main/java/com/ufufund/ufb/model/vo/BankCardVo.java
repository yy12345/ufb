package com.ufufund.ufb.model.vo;

import java.io.Serializable;

/**
 * 银行卡VO <br>
 * 创建日期：2015年3月
 * 
 * @author goodrich
 * @version 1.0
 */
public class BankCardVo implements Serializable {

	private static final long serialVersionUID = 2728670885588522587L;

	private String reqSeq; // 步骤标示

	private String custNo;
	private String invtp;
	private String level;
	private String TradePwd; // 交易密码
	private String TradePwd2; // 交易密码
	private String verifycode; // 系统验证码
	private String msgcode; // 手机验证码
	private String otherserial;
	private String openaccount;

	/** 经办人信息 **/
	private String operatornm; // 经办人
	private String operatoridtp;
	private String operatoridno;
	private String operatormobile;
	private String operatortelno;
	private String operatoremailadd;

	/** 银行信息 **/
	private String bankSerialId; // 银行卡ID(系统)
	private String bankNo;
	private String bankAcco; // 银行账户
	private String bankIdtp; // 银行证件类型
	private String bankIdno; // 银行证件号
	private String bankAcnm; // 银行开户户名
	private String bankMobile; // 银行开户手机
	private String bankprovinceno; // 省份
	private String bankprovincenm;
	private String bankcityno; // 城市
	private String bankcitynm;
	private String bankadd; // 支行网点
	private String sumaryCardName; // 银行卡概要名称（银行名+卡号后4位）

	/** 其他银行信息 **/
	private String perLimit; // 银行卡单笔充值限额
	private String dayLimit; // 银行卡日累计限额
	private String cardLevel; // 银行卡安全级别：屏蔽充值、强制升级、非强制升级
	private String limitDesc; // 单笔及每日限额话术
	private boolean b2c; // 是否B2C
	private String updateDesc; // 银行通道升级文本内容
	private String updateLink; // 银行通道升级链接

	/** 机构信息 **/
	private String business; // 营业执照注册号
	private String organization;// 幼教机构名称
	private String orgprovinceno; // 省份
	private String orgprovincenm;
	private String orgcityno; // 城市
	private String orgadd; // 实际办学经营地址
	private String orgcitynm;

	/** 法人信息 **/
	private String rerpidtp; // 法人证件类型
	private String rerpidno; // 法人证件号码
	private String rerpvalidt; // 法人证件有效日期
	private String rerpnm; // 法人姓名

	public String getReqSeq() {
		return reqSeq;
	}

	public void setReqSeq(String reqSeq) {
		this.reqSeq = reqSeq;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
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

	public String getTradePwd() {
		return TradePwd;
	}

	public void setTradePwd(String tradePwd) {
		TradePwd = tradePwd;
	}

	public String getTradePwd2() {
		return TradePwd2;
	}

	public void setTradePwd2(String tradePwd2) {
		TradePwd2 = tradePwd2;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getMsgcode() {
		return msgcode;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}

	public String getOtherserial() {
		return otherserial;
	}

	public void setOtherserial(String otherserial) {
		this.otherserial = otherserial;
	}

	public String getOpenaccount() {
		return openaccount;
	}

	public void setOpenaccount(String openaccount) {
		this.openaccount = openaccount;
	}

	public String getOperatornm() {
		return operatornm;
	}

	public void setOperatornm(String operatornm) {
		this.operatornm = operatornm;
	}

	public String getOperatoridtp() {
		return operatoridtp;
	}

	public void setOperatoridtp(String operatoridtp) {
		this.operatoridtp = operatoridtp;
	}

	public String getOperatoridno() {
		return operatoridno;
	}

	public void setOperatoridno(String operatoridno) {
		this.operatoridno = operatoridno;
	}

	public String getOperatormobile() {
		return operatormobile;
	}

	public void setOperatormobile(String operatormobile) {
		this.operatormobile = operatormobile;
	}

	public String getOperatortelno() {
		return operatortelno;
	}

	public void setOperatortelno(String operatortelno) {
		this.operatortelno = operatortelno;
	}

	public String getOperatoremailadd() {
		return operatoremailadd;
	}

	public void setOperatoremailadd(String operatoremailadd) {
		this.operatoremailadd = operatoremailadd;
	}

	public String getBankSerialId() {
		return bankSerialId;
	}

	public void setBankSerialId(String bankSerialId) {
		this.bankSerialId = bankSerialId;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankAcco() {
		return bankAcco;
	}

	public void setBankAcco(String bankAcco) {
		this.bankAcco = bankAcco;
	}

	public String getBankIdtp() {
		return bankIdtp;
	}

	public void setBankIdtp(String bankIdtp) {
		this.bankIdtp = bankIdtp;
	}

	public String getBankIdno() {
		return bankIdno;
	}

	public void setBankIdno(String bankIdno) {
		this.bankIdno = bankIdno;
	}

	public String getBankAcnm() {
		return bankAcnm;
	}

	public void setBankAcnm(String bankAcnm) {
		this.bankAcnm = bankAcnm;
	}

	public String getBankMobile() {
		return bankMobile;
	}

	public void setBankMobile(String bankMobile) {
		this.bankMobile = bankMobile;
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

	public String getSumaryCardName() {
		return sumaryCardName;
	}

	public void setSumaryCardName(String sumaryCardName) {
		this.sumaryCardName = sumaryCardName;
	}

	public String getPerLimit() {
		return perLimit;
	}

	public void setPerLimit(String perLimit) {
		this.perLimit = perLimit;
	}

	public String getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(String dayLimit) {
		this.dayLimit = dayLimit;
	}

	public String getCardLevel() {
		return cardLevel;
	}

	public void setCardLevel(String cardLevel) {
		this.cardLevel = cardLevel;
	}

	public String getLimitDesc() {
		return limitDesc;
	}

	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
	}

	public boolean isB2c() {
		return b2c;
	}

	public void setB2c(boolean b2c) {
		this.b2c = b2c;
	}

	public String getUpdateDesc() {
		return updateDesc;
	}

	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

	public String getUpdateLink() {
		return updateLink;
	}

	public void setUpdateLink(String updateLink) {
		this.updateLink = updateLink;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
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

	public String getOrgadd() {
		return orgadd;
	}

	public void setOrgadd(String orgadd) {
		this.orgadd = orgadd;
	}

	public String getOrgcitynm() {
		return orgcitynm;
	}

	public void setOrgcitynm(String orgcitynm) {
		this.orgcitynm = orgcitynm;
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

	@Override
	public String toString() {
		return "BankCardVO [bankSerialId=" + bankSerialId + ", bankNo=" + bankNo + ", custNo=" + custNo + ", bankAcco="
				+ bankAcco + ", sumaryCardName=" + sumaryCardName + ", bankAcnm=" + bankAcnm + ", bankIdtp=" + bankIdtp
				+ ", bankIdno=" + bankIdno + ", bankMobile=" + bankMobile + ", perLimit=" + perLimit + ", dayLimit="
				+ dayLimit + ", cardLevel=" + cardLevel + ", limitDesc=" + limitDesc + ", b2c=" + b2c + ", updateDesc="
				+ updateDesc + ", updateLink=" + updateLink + "]";
	}

}
