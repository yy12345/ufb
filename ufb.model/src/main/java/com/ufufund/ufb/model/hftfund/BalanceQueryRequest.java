package com.ufufund.ufb.model.hftfund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BalanceQueryRequest extends AbstractRequest {

	private String TransactionAccountID;
	private String FundCode;
	private String PageNo;
	private String PageSize;
	private String ShareClass;

	public String getTransactionAccountID() {
		return TransactionAccountID;
	}

	public void setTransactionAccountID(String transactionAccountID) {
		TransactionAccountID = transactionAccountID;
	}

	public String getFundCode() {
		return FundCode;
	}

	public void setFundCode(String fundCode) {
		FundCode = fundCode;
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

	public String getShareClass() {
		return ShareClass;
	}

	public void setShareClass(String shareClass) {
		ShareClass = shareClass;
	}

	@Override
	public String toString() {
		return "BalanceQueryRequest [TransactionAccountID="
				+ TransactionAccountID + ", FundCode=" + FundCode + ", PageNo="
				+ PageNo + ", PageSize=" + PageSize + ", ShareClass="
				+ ShareClass + ", toString()=" + super.toString() + "]";
	}

}
