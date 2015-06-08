package com.ufufund.ufb.model.hft;

import java.math.BigDecimal;

public class RealRedeemRequest extends AbstractRequest{

	private String TransactionAccountID;
	private String FundCode            ;
	private BigDecimal ApplicationVol      ;
	private String ShareClass          ;
	
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
	public BigDecimal getApplicationVol() {
		return ApplicationVol;
	}
	public void setApplicationVol(BigDecimal applicationVol) {
		ApplicationVol = applicationVol;
	}
	public String getShareClass() {
		return ShareClass;
	}
	public void setShareClass(String shareClass) {
		ShareClass = shareClass;
	}
	@Override
	public String toString() {
		return "RealRedeemRequest [TransactionAccountID=" + TransactionAccountID
				+ ", FundCode=" + FundCode + ", ApplicationVol="
				+ ApplicationVol + ", ShareClass=" + ShareClass
				+ ", toString()=" + super.toString() + "]";
	}
}
