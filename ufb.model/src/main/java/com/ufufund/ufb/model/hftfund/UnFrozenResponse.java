package com.ufufund.ufb.model.hftfund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class UnFrozenResponse extends AbstractResponse{

	private String AppSheetSerialNo;
	private String TransactionDate;
	
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
	
	@Override
	public String toString() {
		return "UnFrozenResponse [AppSheetSerialNo=" + AppSheetSerialNo
				+ ", TransactionDate=" + TransactionDate + ", toString()="
				+ super.toString() + "]";
	}
	
}
