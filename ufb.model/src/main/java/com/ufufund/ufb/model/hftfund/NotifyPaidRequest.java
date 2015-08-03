package com.ufufund.ufb.model.hftfund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class NotifyPaidRequest extends AbstractRequest{

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
		return "NotifyPaidRequest [TransactionAccountID=" + TransactionAccountID
				+ ", AppSheetSerialNo=" + AppSheetSerialNo + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
