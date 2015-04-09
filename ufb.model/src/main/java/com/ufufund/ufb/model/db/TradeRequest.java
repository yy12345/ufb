package com.ufufund.ufb.model.db;

import java.io.Serializable;
import java.math.BigDecimal;

public class TradeRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String serialno       ;
	private String custno         ;
	private String fundcorpno     ;
	private String tradeacco      ;
	private String apkind         ;
	private String apdt           ;
	private String aptm           ;
	private String workdate       ;
	private BigDecimal subquty        ;
	private BigDecimal subamt         ;
	private String bankno         ;
	private String bankacco       ;
	private String bankserialid   ;
	private String fundid         ;
	private String referno        ;
	private String remark         ;
	private String subapkind      ;
	private String accptmd        ;
	private String updatetimestamp;
	
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
	public String getApdt() {
		return apdt;
	}
	public void setApdt(String apdt) {
		this.apdt = apdt;
	}
	public String getAptm() {
		return aptm;
	}
	public void setAptm(String aptm) {
		this.aptm = aptm;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
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
	public String getBankno() {
		return bankno;
	}
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
	public String getBankacco() {
		return bankacco;
	}
	public void setBankacco(String bankacco) {
		this.bankacco = bankacco;
	}
	public String getBankserialid() {
		return bankserialid;
	}
	public void setBankserialid(String bankserialid) {
		this.bankserialid = bankserialid;
	}
	public String getFundid() {
		return fundid;
	}
	public void setFundid(String fundid) {
		this.fundid = fundid;
	}
	public String getReferno() {
		return referno;
	}
	public void setReferno(String referno) {
		this.referno = referno;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSubapkind() {
		return subapkind;
	}
	public void setSubapkind(String subapkind) {
		this.subapkind = subapkind;
	}
	public String getAccptmd() {
		return accptmd;
	}
	public void setAccptmd(String accptmd) {
		this.accptmd = accptmd;
	}
	public String getUpdatetimestamp() {
		return updatetimestamp;
	}
	public void setUpdatetimestamp(String updatetimestamp) {
		this.updatetimestamp = updatetimestamp;
	}
	@Override
	public String toString() {
		return "TradeRequest [serialno=" + serialno + ", custno=" + custno
				+ ", fundcorpno=" + fundcorpno + ", tradeacco=" + tradeacco
				+ ", apkind=" + apkind + ", apdt=" + apdt + ", aptm=" + aptm
				+ ", workdate=" + workdate + ", subquty=" + subquty
				+ ", subamt=" + subamt + ", bankno=" + bankno + ", bankacco="
				+ bankacco + ", bankserialid=" + bankserialid + ", fundid="
				+ fundid + ", referno=" + referno + ", remark=" + remark
				+ ", subapkind=" + subapkind + ", accptmd=" + accptmd
				+ ", updatetimestamp=" + updatetimestamp + "]";
	}
	
}
