package com.ufufund.ufb.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 充值VO <br>
 * 创建日期：2015年3月
 * 
 * @author goodrich
 * @version 1.0
 */
public class TopUpComfVo implements Serializable {

	private static final long serialVersionUID = -4471404892887128729L;

	/**
	 * 充值流水
	 */
	private String serialNo;

	/**
	 * 充值金额
	 */
	private String amount;

	/**
	 * 充值金额（金额展示值）
	 */
	private String amountDisplay;

	/**
	 * 银行卡流水
	 */
	private String bankSerialId;

	/**
	 * 银行编码
	 */
	private String bankNo;

	/**
	 * 银行卡概要名称
	 */
	private String sumaryCardName;

	/**
	 * 客户号
	 */
	private String custNo;

	/**
	 * 验证码
	 */
	private String checkcode;

	/**
	 * 交易密码
	 */
	private String tradePwd;

	/**
	 * 托管方式
	 */
	private String acceptMode;

	/**
	 * 业务码
	 */
	private String apkind;

	/**
	 * 子业务码
	 */
	private String subApkind;

	/**
	 * 交易渠道-基金公司
	 */
	private String fundCorpNo;

	/**
	 * 是否b2c支付
	 */
	private boolean b2c;

	/**
	 * b2c返回formBean
	 */
	private String formBean;

	/**
	 * b2c银行支付跳转地址
	 */
	private String returnURL;
	
	

	public String getSerialNo() {
		return serialNo;
	}



	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public String getAmountDisplay() {
		return amountDisplay;
	}



	public void setAmountDisplay(String amountDisplay) {
		this.amountDisplay = amountDisplay;
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



	public String getSumaryCardName() {
		return sumaryCardName;
	}



	public void setSumaryCardName(String sumaryCardName) {
		this.sumaryCardName = sumaryCardName;
	}



	public String getCustNo() {
		return custNo;
	}



	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}



	public String getCheckcode() {
		return checkcode;
	}



	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}



	public String getTradePwd() {
		return tradePwd;
	}



	public void setTradePwd(String tradePwd) {
		this.tradePwd = tradePwd;
	}



	public String getAcceptMode() {
		return acceptMode;
	}



	public void setAcceptMode(String acceptMode) {
		this.acceptMode = acceptMode;
	}



	public String getApkind() {
		return apkind;
	}



	public void setApkind(String apkind) {
		this.apkind = apkind;
	}



	public String getSubApkind() {
		return subApkind;
	}



	public void setSubApkind(String subApkind) {
		this.subApkind = subApkind;
	}



	public String getFundCorpNo() {
		return fundCorpNo;
	}



	public void setFundCorpNo(String fundCorpNo) {
		this.fundCorpNo = fundCorpNo;
	}



	public boolean isB2c() {
		return b2c;
	}



	public void setB2c(boolean b2c) {
		this.b2c = b2c;
	}



	public String getFormBean() {
		return formBean;
	}



	public void setFormBean(String formBean) {
		this.formBean = formBean;
	}



	public String getReturnURL() {
		return returnURL;
	}



	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}



	@Override
	public String toString() {
		return "TopUpComfVO [serialNo=" + serialNo + ", amount=" + amount
				+ ", amountDisplay=" + amountDisplay + ", bankSerialId="
				+ bankSerialId + ", bankNo=" + bankNo + ", sumaryCardName="
				+ sumaryCardName + ", custNo=" + custNo + ", checkcode="
				+ checkcode + ", tradePwd=" + tradePwd + ", acceptMode="
				+ acceptMode + ", subApkind=" + subApkind + ", fundCorpNo="
				+ fundCorpNo + ", b2c=" + b2c + ", formBean=" + formBean
				+ ", returnURL=" + returnURL + "]";
	}

}
