package com.ufufund.req;

public class TransResultQueryDelList {

	/*
	 * 
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码	销售人代码	Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		5002	ApplicationNo	A	24	直销平台申请单编号	直销平台申请单编号	Y
		
		5018	TotalRecord	A	3	总记录数		Y
				以下字段可能多次出现
				8	AppSheetSerialNo	A	24	基金公司申请单编号		N
				92	TransactionDate	A	8	交易发生日期		N
				93	Transactiontime	A	6	交易发生时间		N
				5003	ConfirmFlag	A	1	确认状态		N
				5004	PayFlag	A	1	扣款状态		N
				
				5012	TradeDate	A	8	当前交易日		N
				以上字段可能多次出现
		5006	ReturnCode	C	4	返回代码		Y
		5007	ReturnMsg	C	255	返回描述		Y
		5008	Extension	C	500	消息扩展		N


	 * 
	 */
	
	private String appSheetSerialNo;
	private String transactionDate;
	private String transactiontime;
	private String confirmFlag;
	private String payFlag;
	
	private String tradeDate;

	public String getAppSheetSerialNo() {
		return appSheetSerialNo;
	}

	public void setAppSheetSerialNo(String appSheetSerialNo) {
		this.appSheetSerialNo = appSheetSerialNo;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactiontime() {
		return transactiontime;
	}

	public void setTransactiontime(String transactiontime) {
		this.transactiontime = transactiontime;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	
	
	
}
