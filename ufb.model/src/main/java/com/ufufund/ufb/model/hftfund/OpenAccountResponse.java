package com.ufufund.ufb.model.hftfund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class OpenAccountResponse extends AbstractResponse{

	private String TransactionAccountID;

	public String getTransactionAccountID() {
		return TransactionAccountID;
	}

	public void setTransactionAccountID(String transactionAccountID) {
		TransactionAccountID = transactionAccountID;
	}

	@Override
	public String toString() {
		return "OpenAccountResponse [TransactionAccountID="
				+ TransactionAccountID + ", toString()=" + super.toString()
				+ "]";
	}

	
}
