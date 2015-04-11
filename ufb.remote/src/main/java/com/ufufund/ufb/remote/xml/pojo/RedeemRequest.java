package com.ufufund.ufb.remote.xml.pojo;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractRequest;

@XmlAccessorType(XmlAccessType.FIELD)
public class RedeemRequest extends AbstractRequest{

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
		return "RedeemRequest [TransactionAccountID=" + TransactionAccountID
				+ ", FundCode=" + FundCode + ", ApplicationVol="
				+ ApplicationVol + ", ShareClass=" + ShareClass
				+ ", toString()=" + super.toString() + "]";
	}
}
