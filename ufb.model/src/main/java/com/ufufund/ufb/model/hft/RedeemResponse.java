package com.ufufund.ufb.model.hft;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RedeemResponse extends AbstractResponse{

	private String AppSheetSerialNo;
	private String TransactionDate ;
	private String Transactiontime ;
	private BigDecimal TotalFundVol    ;
	private BigDecimal AvailableVol    ;
	private BigDecimal TotalFrozenVol  ;
	
	public String getAppSheetSerialNo() {
		return AppSheetSerialNo;
	}
	public void setAppSheetSerialNo(String appSheetSerialNo) {
		AppSheetSerialNo = appSheetSerialNo;
	}
	public String getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}
	public String getTransactiontime() {
		return Transactiontime;
	}
	public void setTransactiontime(String transactiontime) {
		Transactiontime = transactiontime;
	}
	public BigDecimal getTotalFundVol() {
		return TotalFundVol;
	}
	public void setTotalFundVol(BigDecimal totalFundVol) {
		TotalFundVol = totalFundVol;
	}
	public BigDecimal getAvailableVol() {
		return AvailableVol;
	}
	public void setAvailableVol(BigDecimal availableVol) {
		AvailableVol = availableVol;
	}
	public BigDecimal getTotalFrozenVol() {
		return TotalFrozenVol;
	}
	public void setTotalFrozenVol(BigDecimal totalFrozenVol) {
		TotalFrozenVol = totalFrozenVol;
	}
	@Override
	public String toString() {
		return "RedeemResponse [AppSheetSerialNo=" + AppSheetSerialNo
				+ ", TransactionDate=" + TransactionDate + ", Transactiontime="
				+ Transactiontime + ", TotalFundVol=" + TotalFundVol
				+ ", AvailableVol=" + AvailableVol + ", TotalFrozenVol="
				+ TotalFrozenVol + ", toString()=" + super.toString() + "]";
	}
	
	
}
