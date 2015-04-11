package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractResponse;

@XmlAccessorType(XmlAccessType.FIELD)
public class BalanceQueryResponse extends AbstractResponse {

	private String TotalRecord;

	//以下字段可能多次出现
	private String TransactionAccountID;
	private String FundCode;
	private String ShareClass;
	private String TotalFundVol;
	private String AvailableVol;
	private String TotalFrozenVol;
	private String UndistributeMonetaryIncome;
	private String FundDayIncome;
	private String TotalIncome;
	//以上字段可能多次出现

	// private List<BalanceQuery> list;
	
	@Override
	public String toString() {
		return "TransQueryResponse [TotalRecord=" + TotalRecord
				+ ", toString()=" + super.toString() + "]";
	}

	public String getTotalRecord() {
		return TotalRecord;
	}

	public void setTotalRecord(String totalRecord) {
		TotalRecord = totalRecord;
	}

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

	public String getShareClass() {
		return ShareClass;
	}

	public void setShareClass(String shareClass) {
		ShareClass = shareClass;
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

	public String getUndistributeMonetaryIncome() {
		return UndistributeMonetaryIncome;
	}

	public void setUndistributeMonetaryIncome(String undistributeMonetaryIncome) {
		UndistributeMonetaryIncome = undistributeMonetaryIncome;
	}

	public String getFundDayIncome() {
		return FundDayIncome;
	}

	public void setFundDayIncome(String fundDayIncome) {
		FundDayIncome = fundDayIncome;
	}

	public String getTotalIncome() {
		return TotalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		TotalIncome = totalIncome;
	}
}

// class BalanceQuery {
//
// private String TransactionAccountID;
// private String FundCode;
// private String ShareClass;
// private String TotalFundVol;
// private String AvailableVol;
// private String TotalFrozenVol;
// private String UndistributeMonetaryIncome;
// private String FundDayIncome;
// private String TotalIncome;
//
// public String getTransactionAccountID() {
// return TransactionAccountID;
// }
//
// public void setTransactionAccountID(String transactionAccountID) {
// TransactionAccountID = transactionAccountID;
// }
//
// public String getFundCode() {
// return FundCode;
// }
//
// public void setFundCode(String fundCode) {
// FundCode = fundCode;
// }
//
// public String getShareClass() {
// return ShareClass;
// }
//
// public void setShareClass(String shareClass) {
// ShareClass = shareClass;
// }
//
// public String getTotalFundVol() {
// return TotalFundVol;
// }
//
// public void setTotalFundVol(String totalFundVol) {
// TotalFundVol = totalFundVol;
// }
//
// public String getAvailableVol() {
// return AvailableVol;
// }
//
// public void setAvailableVol(String availableVol) {
// AvailableVol = availableVol;
// }
//
// public String getTotalFrozenVol() {
// return TotalFrozenVol;
// }
//
// public void setTotalFrozenVol(String totalFrozenVol) {
// TotalFrozenVol = totalFrozenVol;
// }
//
// public String getUndistributeMonetaryIncome() {
// return UndistributeMonetaryIncome;
// }
//
// public void setUndistributeMonetaryIncome(String undistributeMonetaryIncome)
// {
// UndistributeMonetaryIncome = undistributeMonetaryIncome;
// }
//
// public String getFundDayIncome() {
// return FundDayIncome;
// }
//
// public void setFundDayIncome(String fundDayIncome) {
// FundDayIncome = fundDayIncome;
// }
//
// public String getTotalIncome() {
// return TotalIncome;
// }
//
// public void setTotalIncome(String totalIncome) {
// TotalIncome = totalIncome;
// }
// }
