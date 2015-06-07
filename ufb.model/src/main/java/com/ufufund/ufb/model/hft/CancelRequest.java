package com.ufufund.ufb.model.hft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class CancelRequest extends AbstractRequest{

	private String TransactionAccountID;
	private String OriginalAppSheetNo  ;
	
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
	@Override
	public String toString() {
		return "CancelRequest [TransactionAccountID=" + TransactionAccountID
				+ ", OriginalAppSheetNo=" + OriginalAppSheetNo
				+ ", toString()=" + super.toString() + "]";
	}
	
}
