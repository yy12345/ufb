package com.ufufund.req;

public class BankSwiftAuthReq {
	
	/*
	 *
	5011	Version	C	10	1.0.0	版本号	Y
	5000	MerchantId	C	10	机构标识	机构标识	Y
	121		DistributorCode	C	9	销售人代码		Y
	5024	BusinType	C	20	业务类型	见6.1	Y
	5002	ApplicationNo	A	24	合作平台申请单编号		Y
	
	5020	AccoreqSerial	C	20	请求序列号		
	5006	ReturnCode	C	4	返回代码		Y
	5007	ReturnMsg	C	255	返回描述		Y
	5008	Extension	C	500	备注		N
	 * 
	 */

	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String applicationNo;
	
	private String accoreqSerial;
	private String returnCode;
	private String returnMsg;
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
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getAccoreqSerial() {
		return accoreqSerial;
	}
	public void setAccoreqSerial(String accoreqSerial) {
		this.accoreqSerial = accoreqSerial;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	

	
}
