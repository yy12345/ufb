package com.ufufund.ufb.model.db;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ufufund.ufb.model.action.PrintableModel;

public class TradeAccoinfoOfMore extends PrintableModel  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** tradeacco基本属性 **/
	private String tradeacco;
	private String fundcorpno;
	
	/** bankcardinfo表属性 **/
	private String serialid;
	private String custno;
	private String bankno;
	private String bankacco;
	private String bankaccodisplay;
	private String bankidtp;
	private String bankidno;
	private String bankidvaliddt;
	private String bankacnm;
	private String subbankno;
	private String banklongname;
	private String issign;
	private String signno;
	private String bankproexists;
	private String protocol_no;
	private String state;
	private String opendt;
	private String closedt;
	private String disorder;
	private String mainflag;
	private String updatetimestamp;
	
	/** 资金 **/
	private BigDecimal total = new BigDecimal("0.00");
	private BigDecimal available = new BigDecimal("0.00");
	private BigDecimal realavailable = new BigDecimal("0.00");
	private BigDecimal frozen = new BigDecimal("0.00");
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getAvailable() {
		return available;
	}
	public void setAvailable(BigDecimal available) {
		this.available = available;
	}
	public BigDecimal getRealavailable() {
		return realavailable;
	}
	public void setRealavailable(BigDecimal realavailable) {
		this.realavailable = realavailable;
	}
	public BigDecimal getFrozen() {
		return frozen;
	}
	public void setFrozen(BigDecimal frozen) {
		this.frozen = frozen;
	}
	public String getSerialid() {
		return serialid;
	}
	public void setSerialid(String serialid) {
		this.serialid = serialid;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
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
	public String getBankaccodisplay() {
		return bankaccodisplay;
	}
	public void setBankaccodisplay(String bankaccodisplay) {
		this.bankaccodisplay = bankaccodisplay;
	}
	public String getBankidtp() {
		return bankidtp;
	}
	public void setBankidtp(String bankidtp) {
		this.bankidtp = bankidtp;
	}
	public String getBankidno() {
		return bankidno;
	}
	public void setBankidno(String bankidno) {
		this.bankidno = bankidno;
	}
	public String getBankidvaliddt() {
		return bankidvaliddt;
	}
	public void setBankidvaliddt(String bankidvaliddt) {
		this.bankidvaliddt = bankidvaliddt;
	}
	public String getBankacnm() {
		return bankacnm;
	}
	public void setBankacnm(String bankacnm) {
		this.bankacnm = bankacnm;
	}
	public String getSubbankno() {
		return subbankno;
	}
	public void setSubbankno(String subbankno) {
		this.subbankno = subbankno;
	}
	public String getBanklongname() {
		return banklongname;
	}
	public void setBanklongname(String banklongname) {
		this.banklongname = banklongname;
	}
	public String getIssign() {
		return issign;
	}
	public void setIssign(String issign) {
		this.issign = issign;
	}
	public String getSignno() {
		return signno;
	}
	public void setSignno(String signno) {
		this.signno = signno;
	}
	public String getBankproexists() {
		return bankproexists;
	}
	public void setBankproexists(String bankproexists) {
		this.bankproexists = bankproexists;
	}
	public String getProtocol_no() {
		return protocol_no;
	}
	public void setProtocol_no(String protocol_no) {
		this.protocol_no = protocol_no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOpendt() {
		return opendt;
	}
	public void setOpendt(String opendt) {
		this.opendt = opendt;
	}
	public String getClosedt() {
		return closedt;
	}
	public void setClosedt(String closedt) {
		this.closedt = closedt;
	}
	public String getDisorder() {
		return disorder;
	}
	public void setDisorder(String disorder) {
		this.disorder = disorder;
	}
	public String getUpdatetimestamp() {
		return updatetimestamp;
	}
	public void setUpdatetimestamp(String updatetimestamp) {
		this.updatetimestamp = updatetimestamp;
	}
	public String getTradeacco() {
		return tradeacco;
	}
	public void setTradeacco(String tradeacco) {
		this.tradeacco = tradeacco;
	}
	public String getFundcorpno() {
		return fundcorpno;
	}
	public void setFundcorpno(String fundcorpno) {
		this.fundcorpno = fundcorpno;
	}
	public String getMainflag() {
		return mainflag;
	}
	public void setMainflag(String mainflag) {
		this.mainflag = mainflag;
	}
							
}
