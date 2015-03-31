package com.ufufund.ufb.model.vo;

import java.io.Serializable;

/**
 * 银行卡VO
 * <br>创建日期：2015年3月
 * @author goodrich
 * @version 1.0
 */
public class BankVo implements Serializable{
	
	private static final long serialVersionUID = 2728670885588522587L;

	/**
	 * 银行卡ID
	 */
	private String bankSerialId;
	
	/**
	 * 银行卡ID
	 */
	private String bankAccoDisplay;
	
	/**
	 * 银行渠道
	 */
	private String bankNo;
	
	/**
	 * 银行账户
	 */
	private String bankAcco;
	
	/**
	 * 银行卡概要名称（银行名+卡号后4位）
	 */
	private String sumaryCardName;

	/**
	 * 银行卡单笔充值限额
	 */
	private String perLimit;
	
	/**
	 * 银行卡日累计限额
	 */
	private String dayLimit;
	
	/**
	 * 银行卡安全级别：屏蔽充值、强制升级、非强制升级
	 */
	private String cardLevel;
	
	/**
	 * 单笔及每日限额话术
	 */
	private String limitDesc;
	
	/**
	 * 是否B2C
	 */
	private boolean b2c;
	
	/**
	 * 银行通道升级文本内容
	 */
	private String updateDesc;
	
	/**
	 * 银行通道升级链接
	 */
	private String updateLink;
	
	public String getBankSerialId() {
		return bankSerialId;
	}

	public void setBankSerialId(String bankSerialId) {
		this.bankSerialId = bankSerialId;
	}
	
	public String getBankAccoDisplay() {
		return bankAccoDisplay;
	}

	public void setBankAccoDisplay(String bankAccoDisplay) {
		this.bankAccoDisplay = bankAccoDisplay;
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

	@Override
	public String toString() {
		return "TopupBnkCardVO [bankSerialId=" + bankSerialId + ", bankNo="
				+ bankNo + ", bankAcco=" + bankAcco + ", sumaryCardName="
				+ sumaryCardName + ", perLimit=" + perLimit + ", dayLimit="
				+ dayLimit + ", cardLevel=" + cardLevel + ", limitDesc="
				+ limitDesc + ", b2c=" + b2c + ", updateDesc=" + updateDesc
				+ ", updateLink=" + updateLink + "]";
	}

}
