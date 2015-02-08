package com.ufufund.res;

import javax.xml.bind.annotation.XmlElement;


public class CancelApplyRes {

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
	
	@XmlElement(name = "version")
	public String getVersion() {
		return version;
	}
	@XmlElement(name = "merchantId")
	public String getMerchantId() {
		return merchantId;
	}
	@XmlElement(name = "distributorCode")
	public String getDistributorCode() {
		return distributorCode;
	}
	@XmlElement(name = "businType")
	public String getBusinType() {
		return businType;
	}
	@XmlElement(name = "transactionAccountID")
	public String getTransactionAccountID() {
		return transactionAccountID;
	}
	@XmlElement(name = "applicationNo")
	public String getApplicationNo() {
		return applicationNo;
	}
	@XmlElement(name = "originalAppSheetNo")
	public String getOriginalAppSheetNo() {
		return originalAppSheetNo;
	}
	@XmlElement(name = "extension")
	public String getExtension() {
		return extension;
	}
	
	
	
	public void setVersion(String version) {
		this.version = version;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	public void setBusinType(String businType) {
		this.businType = businType;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		this.transactionAccountID = transactionAccountID;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public void setOriginalAppSheetNo(String originalAppSheetNo) {
		this.originalAppSheetNo = originalAppSheetNo;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
	//
	
	
}
