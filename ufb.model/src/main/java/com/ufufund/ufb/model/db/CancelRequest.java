package com.ufufund.ufb.model.db;

import java.io.Serializable;
import java.math.BigDecimal;

public class CancelRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String custno;
	private String serialno;
	private String sheetserialno;
	private String oldserialno;
	private String taserialno;
	private String fundcorpno;
	private String tradeacco;
	private String apkind;
	private String fundcode;
	private String ofundcode;
	private String canceldt;
	private String canceltm;
	private BigDecimal subquty;
	private BigDecimal subamt;
	private String cancelst;
	private String paytype; // 支付方式
	private String payst; // 支付状态
	private String transt;
	private String updatetime;
	private String accptmd; // 受理方式

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getSheetserialno() {
		return sheetserialno;
	}

	public void setSheetserialno(String sheetserialno) {
		this.sheetserialno = sheetserialno;
	}

	public String getOldserialno() {
		return oldserialno;
	}

	public void setOldserialno(String oldserialno) {
		this.oldserialno = oldserialno;
	}

	public String getTaserialno() {
		return taserialno;
	}

	public void setTaserialno(String taserialno) {
		this.taserialno = taserialno;
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

	public String getFundcode() {
		return fundcode;
	}

	public void setFundcode(String fundcode) {
		this.fundcode = fundcode;
	}

	public String getOfundcode() {
		return ofundcode;
	}

	public void setOfundcode(String ofundcode) {
		this.ofundcode = ofundcode;
	}

	public String getCanceldt() {
		return canceldt;
	}

	public void setCanceldt(String canceldt) {
		this.canceldt = canceldt;
	}

	public String getCanceltm() {
		return canceltm;
	}

	public void setCanceltm(String canceltm) {
		this.canceltm = canceltm;
	}

	public BigDecimal getSubquty() {
		return subquty;
	}

	public void setSubquty(BigDecimal subquty) {
		this.subquty = subquty;
	}

	public BigDecimal getSubamt() {
		return subamt;
	}

	public void setSubamt(BigDecimal subamt) {
		this.subamt = subamt;
	}

	public String getCancelst() {
		return cancelst;
	}

	public void setCancelst(String cancelst) {
		this.cancelst = cancelst;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPayst() {
		return payst;
	}

	public void setPayst(String payst) {
		this.payst = payst;
	}

	public String getTranst() {
		return transt;
	}

	public void setTranst(String transt) {
		this.transt = transt;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getAccptmd() {
		return accptmd;
	}

	public void setAccptmd(String accptmd) {
		this.accptmd = accptmd;
	}

	@Override
	public String toString() {
		return "CancelRequest [serialno=" + serialno + ", sheetserialno="
				+ sheetserialno + ", taserialno=" + taserialno + ", custno="
				+ custno + ", fundcorpno=" + fundcorpno + ", tradeacco="
				+ tradeacco + ", fundcode=" + fundcode + ", ofundcode="
				+ ofundcode + ", apkind=" + apkind + ", canceldt=" + canceldt
				+ ", canceltm=" + canceltm + ", subquty=" + subquty
				+ ", subamt=" + subamt + ", cancelst=" + cancelst
				+ ", paytype=" + paytype + ", transt=" + transt + ", payst="
				+ payst + "accptmd=" + accptmd + ", oldserialno=" + oldserialno
				+ ", updatetime=" + updatetime + "]";
	}

}
