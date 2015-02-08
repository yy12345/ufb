package com.ufufund.action;



public class CancelApply {

	/*
	 * 
	 * 撤单交易申请
		5011	Version	C	10	1.0.0	版本号	
		5000	MerchantId	C	10	机构标识	机构标识	Y
		313		DistributorCode	C	9	销售人代码	销售人代码	Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		120		TransactionAccountID	C	17	交易账号		Y
		
		5002	ApplicationNo	A	24	直销平台申请单编号	直销平台申请单编号	Y
		90		OriginalAppSheetNo	A	24	原申请流水号		Y
		5008	Extension	C	500	消息扩展		N


	 */
	
	
	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String transactionAccountID;
	
	private String applicationNo;
	private String originalAppSheetNo;
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
	public String getOriginalAppSheetNo() {
		return originalAppSheetNo;
	}
	public void setOriginalAppSheetNo(String originalAppSheetNo) {
		this.originalAppSheetNo = originalAppSheetNo;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
	
}
