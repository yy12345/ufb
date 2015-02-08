package com.ufufund.res;

import javax.xml.bind.annotation.XmlElement;

public class StaitcshareQueryRes {

	
	/*3.1.14	份额查询（普通接口）
	 * 
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码	销售人代码	Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		120		TransactionAccountID	C	17	交易账号		Y
		
		5002	ApplicationNo	A	24	直销平台申请单编号		Y
		67		FundCode	C	6	基金代码		Y
		260		ShareClass	A	1	收费方式		Y
		5008	Extension	C	500	消息扩展		N
	 * 
	 */
	
	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String transactionAccountID;
	
	private String applicationNo;
	private String fundCode;
	private String shareClass;
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
	@XmlElement(name = "fundCode")
	public String getFundCode() {
		return fundCode;
	}
	
	@XmlElement(name = "shareClass")
	public String getShareClass() {
		return shareClass;
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
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	
	public void setShareClass(String shareClass) {
		this.shareClass = shareClass;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
}
