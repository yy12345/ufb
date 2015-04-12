package com.ufufund.ufb.model.remote.hft;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SubApplyRequest extends AbstractRequest{

	private String TransactionAccountID;
	private String FundCode         ;
	private BigDecimal ApplicationAmount;
	private String ShareClass       ;
	
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
	public BigDecimal getApplicationAmount() {
		return ApplicationAmount;
	}
	public void setApplicationAmount(BigDecimal applicationAmount) {
		ApplicationAmount = applicationAmount;
	}
	public String getShareClass() {
		return ShareClass;
	}
	public void setShareClass(String shareClass) {
		ShareClass = shareClass;
	}
	@Override
	public String toString() {
		return "SubApplyRequest [FundCode=" + FundCode + ", ApplicationAmount="
				+ ApplicationAmount + ", ShareClass=" + ShareClass
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
