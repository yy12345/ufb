package com.ufufund.ufb.model.remote.hft;


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
