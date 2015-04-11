package com.ufufund.ufb.remote.xml.pojo;

import com.ufufund.ufb.remote.xml.base.AbstractRequest;

public class BuyNotifyRequest extends AbstractRequest{

	private String TransactionAccountID;
	private String AppSheetSerialNo    ;
	
	public String getTransactionAccountID() {
		return TransactionAccountID;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		TransactionAccountID = transactionAccountID;
	}
	public String getAppSheetSerialNo() {
		return AppSheetSerialNo;
	}
	public void setAppSheetSerialNo(String appSheetSerialNo) {
		AppSheetSerialNo = appSheetSerialNo;
	}
	@Override
	public String toString() {
		return "BuyNotifyRequest [TransactionAccountID=" + TransactionAccountID
				+ ", AppSheetSerialNo=" + AppSheetSerialNo + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
