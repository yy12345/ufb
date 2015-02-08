package com.ufufund.res;

import javax.xml.bind.annotation.XmlElement;

public class BankSwiftAuthRes {
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
	@XmlElement(name = "acctNameOfInvestorInClearingAgency")
	public String getAcctNameOfInvestorInClearingAgency() {
		return acctNameOfInvestorInClearingAgency;
	}
	@XmlElement(name = "acctNoOfInvestorInClearingAgency")
	public String getAcctNoOfInvestorInClearingAgency() {
		return acctNoOfInvestorInClearingAgency;
	}
	@XmlElement(name = "certificateType")
	public String getCertificateType() {
		return certificateType;
	}
	@XmlElement(name = "certificateNo")
	public String getCertificateNo() {
		return certificateNo;
	}
	@XmlElement(name = "emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}
	@XmlElement(name = "mobileTelNo")
	public String getMobileTelNo() {
		return mobileTelNo;
	}
	@XmlElement(name = "sex")
	public String getSex() {
		return sex;
	}
	@XmlElement(name = "specialFlag")
	public String getSpecialFlag() {
		return specialFlag;
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

	public void setAcctNameOfInvestorInClearingAgency(
			String acctNameOfInvestorInClearingAgency) {
		this.acctNameOfInvestorInClearingAgency = acctNameOfInvestorInClearingAgency;
	}

	public void setAcctNoOfInvestorInClearingAgency(
			String acctNoOfInvestorInClearingAgency) {
		this.acctNoOfInvestorInClearingAgency = acctNoOfInvestorInClearingAgency;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setMobileTelNo(String mobileTelNo) {
		this.mobileTelNo = mobileTelNo;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSpecialFlag(String specialFlag) {
		this.specialFlag = specialFlag;
	}

	public void setAccoreqSerial(String accoreqSerial) {
		this.accoreqSerial = accoreqSerial;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
	
	
	
}
