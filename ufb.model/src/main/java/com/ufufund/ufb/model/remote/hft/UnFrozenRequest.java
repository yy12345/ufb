package com.ufufund.ufb.model.remote.hft;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class UnFrozenRequest extends AbstractRequest{

	private String TransactionAccountID;
	private String OriginalAppSheetNo;
	private BigDecimal ApplicationVol;
	
	public String getTransactionAccountID() {
		return TransactionAccountID;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		TransactionAccountID = transactionAccountID;
	}
	public String getOriginalAppSheetNo() {
		return OriginalAppSheetNo;
	}
	public void setOriginalAppSheetNo(String originalAppSheetNo) {
		OriginalAppSheetNo = originalAppSheetNo;
	}
	public BigDecimal getApplicationVol() {
		return ApplicationVol;
	}
	public void setApplicationVol(BigDecimal applicationVol) {
		ApplicationVol = applicationVol;
	}
	
	@Override
	public String toString() {
		return "UnFrozenRequest [TransactionAccountID=" + TransactionAccountID
				+ ", OriginalAppSheetNo=" + OriginalAppSheetNo
				+ ", ApplicationVol=" + ApplicationVol + ", toString()="
				+ super.toString() + "]";
	}
	
}
