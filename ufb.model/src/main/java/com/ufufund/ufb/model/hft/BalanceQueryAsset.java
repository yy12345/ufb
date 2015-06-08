package com.ufufund.ufb.model.hft;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BalanceQueryAsset {

	private String TransactionAccountID;
	private String FundCode;
	private String ShareClass;
	private BigDecimal TotalFundVol;
	private BigDecimal AvailableVol;
	private BigDecimal TotalFrozenVol;
	private BigDecimal UndistributeMonetaryIncome;
	private BigDecimal FundDayIncome;
	private BigDecimal TotalIncome;
	
	public String getTransactionAccountID() {
		return TransactionAccountID;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		TransactionAccountID = transactionAccountID;
	}
	public String getFundCode() {
		return FundCode;
	}
	public void setFundCode(String fundCode) {
		FundCode = fundCode;
	}
	public String getShareClass() {
		return ShareClass;
	}
	public void setShareClass(String shareClass) {
		ShareClass = shareClass;
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
	public BigDecimal getUndistributeMonetaryIncome() {
		return UndistributeMonetaryIncome;
	}
	public void setUndistributeMonetaryIncome(BigDecimal undistributeMonetaryIncome) {
		UndistributeMonetaryIncome = undistributeMonetaryIncome;
	}
	public BigDecimal getFundDayIncome() {
		return FundDayIncome;
	}
	public void setFundDayIncome(BigDecimal fundDayIncome) {
		FundDayIncome = fundDayIncome;
	}
	public BigDecimal getTotalIncome() {
		return TotalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		TotalIncome = totalIncome;
	}
	@Override
	public String toString() {
		return "BalanceQueryAsset [TransactionAccountID="
				+ TransactionAccountID + ", FundCode=" + FundCode
				+ ", ShareClass=" + ShareClass + ", TotalFundVol="
				+ TotalFundVol + ", AvailableVol=" + AvailableVol
				+ ", TotalFrozenVol=" + TotalFrozenVol
				+ ", UndistributeMonetaryIncome=" + UndistributeMonetaryIncome
				+ ", FundDayIncome=" + FundDayIncome + ", TotalIncome="
				+ TotalIncome + "]";
	}
	
}
