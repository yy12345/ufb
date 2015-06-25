package com.ufufund.ufb.model.db;

import java.io.Serializable;
import java.math.BigDecimal;

public class TradeRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String serialno;             
	private String sheetserialno;
	private String taserialno;
	private String custno;               
	private String fundcorpno;           
	private String tradeacco;              
	private String fundcode;              
	private String apkind ;               
	private String workday;            
	private String appdate;              
	private String apptime;              
	private String ackdate;            
	private BigDecimal appamt;               
	private BigDecimal appvol;               
	private BigDecimal ackamt;               
	private BigDecimal ackvol;               
	private String state ;               
	private String transt;               
	private String payst ;               
	private String shareclass;           
	private String dividmethod;         
	private BigDecimal fee  ;                
	private String referno ;             
	private String updatetime;
	private String bankno;
	private String banknm;
	private String bankacco;
	
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
	public String getTaserialno() {
		return taserialno;
	}
	public void setTaserialno(String taserialno) {
		this.taserialno = taserialno;
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
	public String getFundcode() {
		return fundcode;
	}
	public void setFundcode(String fundcode) {
		this.fundcode = fundcode;
	}
	public String getApkind() {
		return apkind;
	}
	public void setApkind(String apkind) {
		this.apkind = apkind;
	}
	public String getWorkday() {
		return workday;
	}
	public void setWorkday(String workday) {
		this.workday = workday;
	}
	public String getAppdate() {
		return appdate;
	}
	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}
	public String getApptime() {
		return apptime;
	}
	public void setApptime(String apptime) {
		this.apptime = apptime;
	}
	public String getAckdate() {
		return ackdate;
	}
	public void setAckdate(String ackdate) {
		this.ackdate = ackdate;
	}
	public BigDecimal getAppamt() {
		return appamt;
	}
	public void setAppamt(BigDecimal appamt) {
		this.appamt = appamt;
	}
	public BigDecimal getAppvol() {
		return appvol;
	}
	public void setAppvol(BigDecimal appvol) {
		this.appvol = appvol;
	}
	public BigDecimal getAckamt() {
		return ackamt;
	}
	public void setAckamt(BigDecimal ackamt) {
		this.ackamt = ackamt;
	}
	public BigDecimal getAckvol() {
		return ackvol;
	}
	public void setAckvol(BigDecimal ackvol) {
		this.ackvol = ackvol;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTranst() {
		return transt;
	}
	public void setTranst(String transt) {
		this.transt = transt;
	}
	public String getPayst() {
		return payst;
	}
	public void setPayst(String payst) {
		this.payst = payst;
	}
	public String getShareclass() {
		return shareclass;
	}
	public void setShareclass(String shareclass) {
		this.shareclass = shareclass;
	}
	public String getDividmethod() {
		return dividmethod;
	}
	public void setDividmethod(String dividmethod) {
		this.dividmethod = dividmethod;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getReferno() {
		return referno;
	}
	public void setReferno(String referno) {
		this.referno = referno;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getBanknm() {
		return banknm;
	}
	public void setBanknm(String banknm) {
		this.banknm = banknm;
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
	@Override
	public String toString() {
		return "TradeRequest [serialno=" + serialno + ", sheetserialno="
				+ sheetserialno + ", taserialno=" + taserialno + ", custno="
				+ custno + ", fundcorpno=" + fundcorpno + ", tradeacco="
				+ tradeacco + ", fundcode=" + fundcode + ", apkind=" + apkind
				+ ", workday=" + workday + ", appdate=" + appdate
				+ ", apptime=" + apptime + ", ackdate=" + ackdate + ", appamt="
				+ appamt + ", appvol=" + appvol + ", ackamt=" + ackamt
				+ ", ackvol=" + ackvol + ", state=" + state + ", transt="
				+ transt + ", payst=" + payst + ", shareclass=" + shareclass
				+ ", dividmethod=" + dividmethod + ", fee=" + fee
				+ ", referno=" + referno + ", updatetime=" + updatetime
				+ ", bankno=" + bankno + ", banknm=" + banknm + ", bankacco=" + bankacco + "]";
	}
	
}
