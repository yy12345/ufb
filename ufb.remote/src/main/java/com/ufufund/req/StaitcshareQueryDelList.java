package com.ufufund.req;


public class StaitcshareQueryDelList {

	/*
	 * 
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码	销售人代码	Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		5002	ApplicationNo	A	24	直销平台申请单编号	直销平台申请单编号	Y
		5018	TotalRecord	A	3	总记录数		Y
						以下字段可能多次出现
						120	TransactionAccountID	C	17	交易账号		Y
						67	FundCode	C	6	基金代码		Y
						260	ShareClass	A	1	收费方式		Y
						66	TotalFundVol	N	16	总份额	两位小数	Y
						13	AvailableVol	N	16	用户当前可用份额	两位小数	Y
						
						59	TotalFrozenVol	N	16	冻结份额	两位小数	Y
						507	UndistributeMonetaryIncome	N	16	未付收益金额	两位小数	N
						536	FundDayIncome	N	16	每日收益	两位小数	N
						5005	TotalIncome	N	16	累计收益	两位小数	N
						以上字段可能多次出现
		5006	ReturnCode	C	4	返回代码		Y
		5007	ReturnMsg	C	255	返回描述		Y
		5008	Extension	C	500	消息扩展		N

	 * 
	 */
	
	private String transactionAccountID;
	private String fundCode;
	private String shareClass;
	private String totalFundVol;
	private String availableVol;
	
	private String totalFrozenVol;
	private String undistributeMonetaryIncome;
	private String fundDayIncome;
	private String totalIncome;
	
	public String getTransactionAccountID() {
		return transactionAccountID;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		this.transactionAccountID = transactionAccountID;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getShareClass() {
		return shareClass;
	}
	public void setShareClass(String shareClass) {
		this.shareClass = shareClass;
	}
	public String getTotalFundVol() {
		return totalFundVol;
	}
	public void setTotalFundVol(String totalFundVol) {
		this.totalFundVol = totalFundVol;
	}
	public String getAvailableVol() {
		return availableVol;
	}
	public void setAvailableVol(String availableVol) {
		this.availableVol = availableVol;
	}
	public String getTotalFrozenVol() {
		return totalFrozenVol;
	}
	public void setTotalFrozenVol(String totalFrozenVol) {
		this.totalFrozenVol = totalFrozenVol;
	}
	public String getUndistributeMonetaryIncome() {
		return undistributeMonetaryIncome;
	}
	public void setUndistributeMonetaryIncome(String undistributeMonetaryIncome) {
		this.undistributeMonetaryIncome = undistributeMonetaryIncome;
	}
	public String getFundDayIncome() {
		return fundDayIncome;
	}
	public void setFundDayIncome(String fundDayIncome) {
		this.fundDayIncome = fundDayIncome;
	}
	public String getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}
	
	
	
	
}
