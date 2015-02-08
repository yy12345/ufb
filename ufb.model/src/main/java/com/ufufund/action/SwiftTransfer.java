package com.ufufund.action;



public class SwiftTransfer {
/*3.1.11	快速过户
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码	销售人代码	Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		120		TransactionAccountID	C	17	交易账号		Y
		
		5002	ApplicationNo	A	24	直销平台申请单编号		Y
		67		FundCode	C	6	基金代码		Y
		132		ApplicationVol	N	16	申请份数	（两位小数）	Y
		5008	Extension	C	500	消息扩展		N
		
		

 */
	
	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String transactionAccountID;
	
	private String applicationNo;
	private String fundCode;
	private String applicationVol;
	private String extension;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getDistributorCode() {
		return distributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	public String getBusinType() {
		return businType;
	}
	public void setBusinType(String businType) {
		this.businType = businType;
	}
	public String getTransactionAccountID() {
		return transactionAccountID;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		this.transactionAccountID = transactionAccountID;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getApplicationVol() {
		return applicationVol;
	}
	public void setApplicationVol(String applicationVol) {
		this.applicationVol = applicationVol;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
}
