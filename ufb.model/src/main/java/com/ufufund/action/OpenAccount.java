package com.ufufund.action;


public class OpenAccount{

/*
 用户从基金直销平台发起开户请求。	
 * 
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码		Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		5002	ApplicationNo	A	24	合作平台申请单编号		Y
		
		183		ClearingAgencyCode	A	9	投资人收款银行账户开户行	N
		181		AcctNameOfInvestorInClearingAgency	C	60	投资人收款银行账户户名		N
		182		AcctNoOfInvestorInClearingAgency	C	28	投资人收款银行账户账号		N
		340		Province	C	6	投资人银行省/直辖市		N
		341		City	C	6	投资人银行市		N
		
		85		InvestorName	C	120	投资人户名		Y
		27		CertificateType	C	1	机构证件类型	个人证件类型
									0-身份证，1-护照
									2-军官证，3-士兵证
									4-港澳居民来往内地通行证，5-户口本
									6-外国护照，7-其它
									8-文职证，9-警官证
									A-台胞证	Y
		72		CertificateNo	C	30	投资人证件号码		Y
		286		CertValidDate	A	8	证件有效期		Y
		49		EmailAddress	C	40	投资人E-MAIL地址		N
		
		83		MobileTelNo	C	24	投资人手机号码		N
		88		OfficeTelNo	C	22	投资人单位电话号码		N
		51		FaxNo	C	24	投资人传真号码		N
		4		Address	C	120	通讯地址		N
		101		PostCode	A	6	投资人邮政编码		N
		
		5008	Extension	C	500	备注		N


 */
	
	
	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String applicationNo;
	
	private String clearingAgencyCode;
	private String acctNameOfInvestorInClearingAgency;
	private String acctNoOfInvestorInClearingAgency;
	private String province;
	private String city;
	
	private String investorName;
	private String certificateType;
	private String certificateNo;
	private String certValidDate;
	private String emailAddress;
	
	private String mobileTelNo;
	private String officeTelNo;
	private String faxNo;
	private String address;
	private String postCode;
	
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
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

	public String getCertValidDate() {
		return certValidDate;
	}

	public void setCertValidDate(String certValidDate) {
		this.certValidDate = certValidDate;
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

	public String getOfficeTelNo() {
		return officeTelNo;
	}

	public void setOfficeTelNo(String officeTelNo) {
		this.officeTelNo = officeTelNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	
}
