package com.ufufund.ufb.model.db;

import java.io.Serializable;
import java.math.BigDecimal;

public class TradeQutyChg implements Serializable {
	private static final long serialVersionUID = 1L;

	private String serialno;
	private String custno;
	private String fundcorpno;
	private String tradeacco;
	private String apkind;
	private String appdate;
	private String workdate;
	private String fundcode;
	private BigDecimal changequty;
	private String oldserialno;
	private String updatetime;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
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

	public String getTradeacco() {
		return tradeacco;
	}

	public void setTradeacco(String tradeacco) {
		this.tradeacco = tradeacco;
	}

	public String getApkind() {
		return apkind;
	}

	public void setApkind(String apkind) {
		this.apkind = apkind;
	}

	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}

	public String getWorkdate() {
		return workdate;
	}

	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}

	public String getFundcode() {
		return fundcode;
	}

	public void setFundcode(String fundcode) {
		this.fundcode = fundcode;
	}

	public BigDecimal getChangequty() {
		return changequty;
	}

	public void setChangequty(BigDecimal changequty) {
		this.changequty = changequty;
	}

	public String getOldserialno() {
		return oldserialno;
	}

	public void setOldserialno(String oldserialno) {
		this.oldserialno = oldserialno;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "TradeQutyChg [serialno=" + serialno + ", custno=" + custno
				+ ", fundcorpno=" + fundcorpno + ", tradeacco=" + tradeacco
				+ ", apkind=" + apkind + ", appdate=" + appdate + ", workdate="
				+ workdate + ", fundcode=" + fundcode + ", changequty="
				+ changequty + ", oldserialno=" + oldserialno + ", updatetime="
				+ updatetime + "]";
	}

}
