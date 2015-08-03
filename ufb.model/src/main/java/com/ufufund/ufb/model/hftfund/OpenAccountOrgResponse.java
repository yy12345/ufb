package com.ufufund.ufb.model.hftfund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class OpenAccountOrgResponse extends AbstractResponse{

	private String TransactionAccountID;

	public String getTransactionAccountID() {
		return TransactionAccountID;
	}

	public void setTransactionAccountID(String transactionAccountID) {
		TransactionAccountID = transactionAccountID;
	}

	@Override
	public String toString() {
		return "OpenAccountOrgResponse [TransactionAccountID="
				+ TransactionAccountID + ", toString()=" + super.toString()
				+ "]";
	}

	
}
