package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractResponse;

@XmlAccessorType(XmlAccessType.FIELD)
public class RedeemResponse extends AbstractResponse{

	private String AppSheetSerialNo;
	private String TransactionDate ;
	private String Transactiontime ;
	private String TotalFundVol    ;
	private String AvailableVol    ;
	private String TotalFrozenVol  ;
	
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
	public String getTotalFundVol() {
		return TotalFundVol;
	}
	public void setTotalFundVol(String totalFundVol) {
		TotalFundVol = totalFundVol;
	}
	public String getAvailableVol() {
		return AvailableVol;
	}
	public void setAvailableVol(String availableVol) {
		AvailableVol = availableVol;
	}
	public String getTotalFrozenVol() {
		return TotalFrozenVol;
	}
	public void setTotalFrozenVol(String totalFrozenVol) {
		TotalFrozenVol = totalFrozenVol;
	}
	@Override
	public String toString() {
		return "RedeemResponse [AppSheetSerialNo=" + AppSheetSerialNo
				+ ", TransactionDate=" + TransactionDate + ", Transactiontime="
				+ Transactiontime + ", TotalFundVol=" + TotalFundVol
				+ ", AvailableVol=" + AvailableVol + ", TotalFrozenVol="
				+ TotalFrozenVol + ", toString()=" + super.toString() + "]";
	}
	
	
}
