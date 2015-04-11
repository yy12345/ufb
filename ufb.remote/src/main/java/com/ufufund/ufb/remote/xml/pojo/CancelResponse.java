package com.ufufund.ufb.remote.xml.pojo;

import com.ufufund.ufb.remote.xml.base.AbstractResponse;

public class CancelResponse extends AbstractResponse{

	private String AppSheetSerialNo;
	private String TransactionDate ;
	private String Transactiontime ;
	
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
	@Override
	public String toString() {
		return "CancelResponse [AppSheetSerialNo=" + AppSheetSerialNo
				+ ", TransactionDate=" + TransactionDate + ", Transactiontime="
				+ Transactiontime + ", toString()=" + super.toString() + "]";
	}
	
}
