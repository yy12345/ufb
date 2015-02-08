package com.ufufund.res;

import javax.xml.bind.annotation.XmlElement;

public class BankSwiftVerifyRes {

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
	@XmlElement(name = "applicationNo")
	public String getApplicationNo() {
		return applicationNo;
	}
	@XmlElement(name = "clearingAgencyCode")
	public String getClearingAgencyCode() {
		return clearingAgencyCode;
	}
	@XmlElement(name = "mobileTelNo")
	public String getMobileTelNo() {
		return mobileTelNo;
	}
	@XmlElement(name = "sex")
	public String getSex() {
		return sex;
	}
	@XmlElement(name = "mobileAutoCode")
	public String getMobileAutoCode() {
		return mobileAutoCode;
	}
	@XmlElement(name = "accoreqSerial")
	public String getAccoreqSerial() {
		return accoreqSerial;
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

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public void setClearingAgencyCode(String clearingAgencyCode) {
		this.clearingAgencyCode = clearingAgencyCode;
	}

	public void setMobileTelNo(String mobileTelNo) {
		this.mobileTelNo = mobileTelNo;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setMobileAutoCode(String mobileAutoCode) {
		this.mobileAutoCode = mobileAutoCode;
	}

	public void setAccoreqSerial(String accoreqSerial) {
		this.accoreqSerial = accoreqSerial;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
	
}
