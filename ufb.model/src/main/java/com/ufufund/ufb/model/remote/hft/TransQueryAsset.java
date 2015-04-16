package com.ufufund.ufb.model.remote.hft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransQueryAsset {

	private String AppSheetSerialNo;
	private String TransactionDate;
	private String Transactiontime;
	private String ConfirmFlag;
	private String PayFlag;
	private String TradeDate;
	
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
	public String getConfirmFlag() {
		return ConfirmFlag;
	}
	public void setConfirmFlag(String confirmFlag) {
		ConfirmFlag = confirmFlag;
	}
	public String getPayFlag() {
		return PayFlag;
	}
	public void setPayFlag(String payFlag) {
		PayFlag = payFlag;
	}
	public String getTradeDate() {
		return TradeDate;
	}
	public void setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
	}
	@Override
	public String toString() {
		return "TransQueryAsset [AppSheetSerialNo=" + AppSheetSerialNo
				+ ", TransactionDate=" + TransactionDate + ", Transactiontime="
				+ Transactiontime + ", ConfirmFlag=" + ConfirmFlag
				+ ", PayFlag=" + PayFlag + ", TradeDate=" + TradeDate + "]";
	}
		
}
