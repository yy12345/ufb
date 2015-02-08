package com.ufufund.req;

public class BankSwiftVerifyReq {

	/*
	 *
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码		Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		5002	ApplicationNo	A	24	合作平台申请单编号		Y
		
		120		TransactionAccountID	C	17	基金公司用户交易账号		N
		5023	ValidateState	A	1	认证状态	银行验证结果 1-成功，0-失败	Y
		5024	Cdcard	C	40	银行签约协议号		Y
		5020	AccoreqSerial	C	20	请求序列号	农行，其他为空	
		5006	ReturnCode	C	4	返回代码		Y
		
		5007	ReturnMsg	C	255	返回描述		Y
		5008	Extension	C	500	备注		N


	 */
	
	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String applicationNo;
	
	private String transactionAccountID;
	private String validateState;
	private String cdcard;
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
	public String getTransactionAccountID() {
		return transactionAccountID;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		this.transactionAccountID = transactionAccountID;
	}
	public String getValidateState() {
		return validateState;
	}
	public void setValidateState(String validateState) {
		this.validateState = validateState;
	}
	public String getCdcard() {
		return cdcard;
	}
	public void setCdcard(String cdcard) {
		this.cdcard = cdcard;
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
