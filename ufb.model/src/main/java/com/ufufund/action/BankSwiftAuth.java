package com.ufufund.action;


public class BankSwiftAuth {
/*用户从基金直销平台发起银行快捷鉴权
 * 
5011	Version	C	10	1.0.0	版本号	Y
5000	MerchantId	C	10	机构标识	机构标识	Y
121		DistributorCode	C	9	销售人代码		Y
5024	BusinType	C	20	业务类型	见6.1	Y
5002	ApplicationNo	A	24	合作平台申请单编号		Y

183		ClearingAgencyCode	A	9	投资人收款银行账户开户行	N
181		AcctNameOfInvestorInClearingAgency	C	60	投资人收款银行账户户名		N
182		AcctNoOfInvestorInClearingAgency	C	28	投资人收款银行账户账号		N
27		CertificateType	C	1	机构证件类型	个人证件类型
							0-身份证，1-护照
							2-军官证，3-士兵证
							4-港澳居民来往内地通行证，5-户口本
							6-外国护照，7-其它
							8-文职证，9-警官证
							A-台胞证	Y
72		CertificateNo	C	30	投资人证件号码		Y

49		EmailAddress	C	40	投资人E-MAIL地址		N
83		MobileTelNo	C	24	投资人手机号码		N
126		Sex	A	1	性别	1-男，2-女	N
5019	SpecialFlag	C	2	特殊标识	农行传1表示重新发送,其他为空	
5020	AccoreqSerial	C	20	特殊标识位	农行，其他为空	

5008	Extension	C	500	备注		N

 * 
 */
	
	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String applicationNo;
	
	private String clearingAgencyCode;
	private String acctNameOfInvestorInClearingAgency;
	private String acctNoOfInvestorInClearingAgency;
	private String certificateType;
	private String certificateNo;
	
	private String emailAddress;
	private String mobileTelNo;
	private String sex;
	private String specialFlag;
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

	public String getAcctNameOfInvestorInClearingAgency() {
		return acctNameOfInvestorInClearingAgency;
	}

	public void setAcctNameOfInvestorInClearingAgency(
			String acctNameOfInvestorInClearingAgency) {
		this.acctNameOfInvestorInClearingAgency = acctNameOfInvestorInClearingAgency;
	}

	public String getAcctNoOfInvestorInClearingAgency() {
		return acctNoOfInvestorInClearingAgency;
	}

	public void setAcctNoOfInvestorInClearingAgency(
			String acctNoOfInvestorInClearingAgency) {
		this.acctNoOfInvestorInClearingAgency = acctNoOfInvestorInClearingAgency;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public String getSpecialFlag() {
		return specialFlag;
	}

	public void setSpecialFlag(String specialFlag) {
		this.specialFlag = specialFlag;
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
