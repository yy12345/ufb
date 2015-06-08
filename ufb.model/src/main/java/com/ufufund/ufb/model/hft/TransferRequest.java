package com.ufufund.ufb.model.hft;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransferRequest extends AbstractResponse{

	private String TransactionAccountID;
	private String FundCode;
	private BigDecimal ApplicationVol;
	
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
	
	@Override
	public String toString() {
		return "TransferRequest [TransactionAccountID=" + TransactionAccountID
				+ ", FundCode=" + FundCode + ", ApplicationVol="
				+ ApplicationVol + ", toString()=" + super.toString() + "]";
	}
	
}
