package com.ufufund.ufb.model.db;

import java.io.Serializable;
import java.math.BigDecimal;

public class FundBalance implements Serializable {
	private static final long serialVersionUID = 1L;

	private String custno;
	private String fundcorpno;
	private String tradeacco;
	private String fundcode;
	private String shareclass; // 收费方式 A:前收费B:后收费
	private String defdividendmethod; // 0-红利转投，1-现金分红

	private BigDecimal totalfundvol; // 总份额
	private BigDecimal availablevol; // 用户当前可用份额
	private BigDecimal totalfrozenvol; // 冻结份额
	private BigDecimal undistributemonetaryincome; // 未付收益金额

	private BigDecimal funddayincome; // 每日收益
	private BigDecimal totalincome; // 累计收益

	private String lastdate; // 最后余额变动日期
	private String updatetime; // 更新时间

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

	public String getTradeacco() {
		return tradeacco;
	}

	public void setTradeacco(String tradeacco) {
		this.tradeacco = tradeacco;
	}

	public String getFundcode() {
		return fundcode;
	}

	public void setFundcode(String fundcode) {
		this.fundcode = fundcode;
	}

	public String getShareclass() {
		return shareclass;
	}

	public void setShareclass(String shareclass) {
		this.shareclass = shareclass;
	}

	public String getDefdividendmethod() {
		return defdividendmethod;
	}

	public void setDefdividendmethod(String defdividendmethod) {
		this.defdividendmethod = defdividendmethod;
	}

	public BigDecimal getTotalfundvol() {
		return totalfundvol;
	}

	public void setTotalfundvol(BigDecimal totalfundvol) {
		this.totalfundvol = totalfundvol;
	}

	public BigDecimal getAvailablevol() {
		return availablevol;
	}

	public void setAvailablevol(BigDecimal availablevol) {
		this.availablevol = availablevol;
	}

	public BigDecimal getTotalfrozenvol() {
		return totalfrozenvol;
	}

	public void setTotalfrozenvol(BigDecimal totalfrozenvol) {
		this.totalfrozenvol = totalfrozenvol;
	}

	public BigDecimal getUndistributemonetaryincome() {
		return undistributemonetaryincome;
	}

	public void setUndistributemonetaryincome(
			BigDecimal undistributemonetaryincome) {
		this.undistributemonetaryincome = undistributemonetaryincome;
	}

	public BigDecimal getFunddayincome() {
		return funddayincome;
	}

	public void setFunddayincome(BigDecimal funddayincome) {
		this.funddayincome = funddayincome;
	}

	public BigDecimal getTotalincome() {
		return totalincome;
	}

	public void setTotalincome(BigDecimal totalincome) {
		this.totalincome = totalincome;
	}

	public String getLastdate() {
		return lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "FundBalance [custno=" + custno + ", fundcorpno=" + fundcorpno
				+ ", tradeacco=" + tradeacco + ", fundcode=" + fundcode
				+ ", shareclass=" + shareclass + ", defdividendmethod="
				+ defdividendmethod + ", totalfundvol=" + totalfundvol
				+ ", availablevol=" + availablevol + ", totalfrozenvol="
				+ totalfrozenvol + ", undistributemonetaryincome="
				+ undistributemonetaryincome + ", funddayincome="
				+ funddayincome + ", totalincome=" + totalincome
				+ ", lastdate=" + lastdate + ", updatetime=" + updatetime + "]";
	}

}
