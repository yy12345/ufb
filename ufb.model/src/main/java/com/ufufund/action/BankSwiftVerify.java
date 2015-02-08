package com.ufufund.action;


public class BankSwiftVerify {

/*用户从基金直销平台发起快捷验证信息的校验。
 * 
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码		Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		5002	ApplicationNo	A	24	合作平台申请单编号		Y
		
		183		ClearingAgencyCode	A	9	投资人收款银行账户开户行	N
		83		MobileTelNo	C	24	投资人手机号码		N
		126		Sex	A	1	性别	1-男，2-女	N
		5022	MobileAutoCode	C	2	手机验证码		
		5020	AccoreqSerial	C	20	特殊标识位	农行，其他为空	
		
		5008	Extension	C	500	备注		N


 */
	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String applicationNo;
	
	private String clearingAgencyCode;
	private String mobileTelNo;
	private String sex;
	private String mobileAutoCode;
	private String accoreqSerial;
	
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

	public String getClearingAgencyCode() {
		return clearingAgencyCode;
	}

	public void setClearingAgencyCode(String clearingAgencyCode) {
		this.clearingAgencyCode = clearingAgencyCode;
	}

	public String getMobileTelNo() {
		return mobileTelNo;
	}

	public void setMobileTelNo(String mobileTelNo) {
		this.mobileTelNo = mobileTelNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobileAutoCode() {
		return mobileAutoCode;
	}

	public void setMobileAutoCode(String mobileAutoCode) {
		this.mobileAutoCode = mobileAutoCode;
	}

	public String getAccoreqSerial() {
		return accoreqSerial;
	}

	public void setAccoreqSerial(String accoreqSerial) {
		this.accoreqSerial = accoreqSerial;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	
	
	
	
	
}
