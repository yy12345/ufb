package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractResponse;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransQueryResponse extends AbstractResponse {

	private String TotalRecord;
	
	//以下字段可能多次出现
	private String AppSheetSerialNo;
	private String TransactionDate;
	private String Transactiontime;
	private String ConfirmFlag;
	private String PayFlag;
	private String TradeDate;
	//以上字段可能多次出现
	
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

	public String getTotalRecord() {
		return TotalRecord;
	}

	public void setTotalRecord(String totalRecord) {
		TotalRecord = totalRecord;
	}

	@Override
	public String toString() {
		return "TransQueryResponse [TotalRecord=" + TotalRecord
				+ ", toString()=" + super.toString() + "]";
	}

}

//class TransQuery {
//
//	private String AppSheetSerialNo;
//	private String TransactionDate;
//	private String Transactiontime;
//	private String ConfirmFlag;
//	private String PayFlag;
//	private String TradeDate;
//
//	public String getAppSheetSerialNo() {
//		return AppSheetSerialNo;
//	}
//
//	public void setAppSheetSerialNo(String appSheetSerialNo) {
//		AppSheetSerialNo = appSheetSerialNo;
//	}
//
//	public String getTransactionDate() {
//		return TransactionDate;
//	}
//
//	public void setTransactionDate(String transactionDate) {
//		TransactionDate = transactionDate;
//	}
//
//	public String getTransactiontime() {
//		return Transactiontime;
//	}
//
//	public void setTransactiontime(String transactiontime) {
//		Transactiontime = transactiontime;
//	}
//
//	public String getConfirmFlag() {
//		return ConfirmFlag;
//	}
//
//	public void setConfirmFlag(String confirmFlag) {
//		ConfirmFlag = confirmFlag;
//	}
//
//	public String getPayFlag() {
//		return PayFlag;
//	}
//
//	public void setPayFlag(String payFlag) {
//		PayFlag = payFlag;
//	}
//
//	public String getTradeDate() {
//		return TradeDate;
//	}
//
//	public void setTradeDate(String tradeDate) {
//		TradeDate = tradeDate;
//	}
//
//}
