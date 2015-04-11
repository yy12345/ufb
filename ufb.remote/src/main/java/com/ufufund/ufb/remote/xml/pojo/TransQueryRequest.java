package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractRequest;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransQueryRequest extends AbstractRequest {

	private String TransactionAccountID;
	private String AppSheetSerialNo;
	private String PageNo;
	private String PageSize;

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

	public String getPageNo() {
		return PageNo;
	}

	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}

	public String getPageSize() {
		return PageSize;
	}

	public void setPageSize(String pageSize) {
		PageSize = pageSize;
	}

	@Override
	public String toString() {
		return "TransQueryRequest [TransactionAccountID="
				+ TransactionAccountID + ", AppSheetSerialNo="
				+ AppSheetSerialNo + ", PageNo=" + PageNo + ", PageSize="
				+ PageSize + ", toString()=" + super.toString() + "]";
	}

}
