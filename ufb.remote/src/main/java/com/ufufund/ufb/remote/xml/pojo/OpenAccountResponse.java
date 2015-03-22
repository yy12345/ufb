package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractResponse;

@XmlAccessorType(XmlAccessType.FIELD)
public class OpenAccountResponse extends AbstractResponse{

	private String transactionAccountID;

	public String getTransactionAccountID() {
		return transactionAccountID;
	}

	public void setTransactionAccountID(String transactionAccountID) {
		this.transactionAccountID = transactionAccountID;
	}

	@Override
	public String toString() {
		return "OpenAccountResponse [transactionAccountID="
				+ transactionAccountID + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
