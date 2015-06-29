package com.ufufund.ufb.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.Apkind;

public class CancelVo extends TradeRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String oldserialno;
	// 交易密码
	private String tradePwd;
	// 银行编码
	private String bankno;
	// 银行卡id
	private String bankid;

	private String canceldt;
	private String canceltm;
	private String cancelst;
	private String ofundcode;
	private BigDecimal subquty;
	private BigDecimal subamt;
	private String payst;
	private String paytype;

	public String getOldserialno() {
		return oldserialno;
	}

	public void setOldserialno(String oldserialno) {
		this.oldserialno = oldserialno;
	}

	public String getTradePwd() {
		return tradePwd;
	}

	public void setTradePwd(String tradePwd) {
		this.tradePwd = tradePwd;
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
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

	public String getCancelst() {
		return cancelst;
	}

	public void setCancelst(String cancelst) {
		this.cancelst = cancelst;
	}

	public String getOfundcode() {
		return ofundcode;
	}

	public void setOfundcode(String ofundcode) {
		this.ofundcode = ofundcode;
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

	public String getPayst() {
		return payst;
	}

	public void setPayst(String payst) {
		this.payst = payst;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	@Override
	public String toString() {
		return "CancelVo [oldserialno=" + oldserialno + ", tradeacco="
				+ super.getTradeacco() + "]";
	}

}
