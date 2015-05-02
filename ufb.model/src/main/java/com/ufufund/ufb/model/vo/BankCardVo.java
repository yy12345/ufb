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

	private String reqSeq;

	private static final long serialVersionUID = 2728670885588522587L;

	private String bankSerialId; // 银行卡ID
	private String custNo;
	private String bankNo;
	private String bankAcco; // 银行账户
	private String bankIdtp; // 银行证件类型
	private String bankIdno; // 银行证件号
	private String bankAcnm; // 银行开户户名
	private String bankMobile; // 银行开户手机
	private String TradePwd; // 交易密码
	private String TradePwd2; // 交易密码
	private String sumaryCardName; // 银行卡概要名称（银行名+卡号后4位）
	private String perLimit; // 银行卡单笔充值限额
	private String dayLimit; // 银行卡日累计限额
	private String cardLevel; // 银行卡安全级别：屏蔽充值、强制升级、非强制升级
	private String limitDesc; // 单笔及每日限额话术
	private boolean b2c; // 是否B2C
	private String updateDesc; // 银行通道升级文本内容
	private String updateLink; // 银行通道升级链接
	private String verifycode; // 系统验证码
	private String msgcode; // 短信验证码
	private String otherserial;
	private String organization;// 幼教机构名称
	private String business; // 营业执照注册号
	private String invtp;
	private String level;
	private String openaccount;

	public String getReqSeq() {
		return reqSeq;
	}

	public void setReqSeq(String reqSeq) {
		this.reqSeq = reqSeq;
	}

	public String getBankSerialId() {
		return bankSerialId;
	}

	public void setBankSerialId(String bankSerialId) {
		this.bankSerialId = bankSerialId;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
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

	public String getOpenaccount() {
		return openaccount;
	}

	public void setOpenaccount(String openaccount) {
		this.openaccount = openaccount;
	}

	@Override
	public String toString() {
		return "BankCardVO [bankSerialId=" + bankSerialId + ", bankNo="
				+ bankNo + ", custNo=" + custNo + ", bankAcco=" + bankAcco
				+ ", sumaryCardName=" + sumaryCardName + ", bankAcnm="
				+ bankAcnm + ", bankIdtp=" + bankIdtp + ", bankIdno="
				+ bankIdno + ", bankMobile=" + bankMobile + ", perLimit="
				+ perLimit + ", dayLimit=" + dayLimit + ", cardLevel="
				+ cardLevel + ", limitDesc=" + limitDesc + ", b2c=" + b2c
				+ ", updateDesc=" + updateDesc + ", updateLink=" + updateLink
				+ "]";
	}

}
