package com.ufufund.ufb.model.hftfund;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class FrozenRequest extends AbstractRequest{

	private String TransactionAccountID;
	private String FundCode         ;
	private BigDecimal ApplicationVol;
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
		return "FrozenRequest [FundCode=" + FundCode + ", ApplicationVol="
				+ ApplicationVol + ", ShareClass=" + ShareClass
				+ ", toString()=" + super.toString() + "]";
	}
	
}
