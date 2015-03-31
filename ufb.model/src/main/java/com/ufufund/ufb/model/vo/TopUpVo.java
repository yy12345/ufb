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
public class TopUpVo implements Serializable {

	private static final long serialVersionUID = -4471404892887128729L;

	/**
	 * 货基余额
	 */
	private String vcashAmt;

	/**
	 * 货基余额（金额展示值）
	 */
	private String vcashAmtDisplay;

	/**
	 * 最小充值金额
	 */
	private String minBid;

	/**
	 * 最大充值金额
	 */
	private String maxBid;

	/**
	 * 当日已充值金额
	 */
	private String dayUsedBid;

	/**
	 * 当日充值限额
	 */
	private String dayLimitBid;

	/**
	 * 是否充值
	 */
	private boolean canBid;

	/**
	 * 下一工作日
	 */
	private String nextWorkDay;

	/**
	 * 是否需要验证码（充值超过10次需要）
	 */
	private boolean needVerify;

	/**
	 * 充值银行卡通道列表
	 */
	private List<BankCardVo> bankCardVoList;
	
	

	public String getVcashAmt() {
		return vcashAmt;
	}



	public void setVcashAmt(String vcashAmt) {
		this.vcashAmt = vcashAmt;
	}



	public String getVcashAmtDisplay() {
		return vcashAmtDisplay;
	}



	public void setVcashAmtDisplay(String vcashAmtDisplay) {
		this.vcashAmtDisplay = vcashAmtDisplay;
	}



	public String getMinBid() {
		return minBid;
	}



	public void setMinBid(String minBid) {
		this.minBid = minBid;
	}



	public String getMaxBid() {
		return maxBid;
	}



	public void setMaxBid(String maxBid) {
		this.maxBid = maxBid;
	}



	public String getDayUsedBid() {
		return dayUsedBid;
	}



	public void setDayUsedBid(String dayUsedBid) {
		this.dayUsedBid = dayUsedBid;
	}



	public String getDayLimitBid() {
		return dayLimitBid;
	}



	public void setDayLimitBid(String dayLimitBid) {
		this.dayLimitBid = dayLimitBid;
	}



	public boolean isCanBid() {
		return canBid;
	}



	public void setCanBid(boolean canBid) {
		this.canBid = canBid;
	}



	public String getNextWorkDay() {
		return nextWorkDay;
	}



	public void setNextWorkDay(String nextWorkDay) {
		this.nextWorkDay = nextWorkDay;
	}



	public boolean isNeedVerify() {
		return needVerify;
	}



	public void setNeedVerify(boolean needVerify) {
		this.needVerify = needVerify;
	}



	public List<BankCardVo> getBankCardVoList() {
		return bankCardVoList;
	}



	public void setBankCardVoList(List<BankCardVo> bankCardVoList) {
		this.bankCardVoList = bankCardVoList;
	}



	@Override
	public String toString() {
		return "TopupVO [vcashAmt=" + vcashAmt + ", minBid=" + minBid
				+ ", maxBid=" + maxBid + ", dayUsedBid=" + dayUsedBid
				+ ", dayLimitBid=" + dayLimitBid + ", canBid=" + canBid
				+ ", nextWorkDay=" + nextWorkDay + ", needVerify=" + needVerify
				+ ", bankCardVoList=" + bankCardVoList + "]";
	}

}
