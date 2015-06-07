package com.ufufund.ufb.model.hft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class OpenAccountOrgRequest extends AbstractRequest{

	private String ClearingAgencyCode                ;
	private String AcctNameOfInvestorInClearingAgency;
	private String AcctNoOfInvestorInClearingAgency  ;
	private String Province             ;
	private String City                 ;
	private String InvestorName         ;
	private String CertificateType      ;
	private String CertificateNo        ;
	private String CertValidDate        ;
	private String EmailAddress         ;
	private String MobileTelNo          ;
	private String OfficeTelNo          ;
	private String FaxNo                ;
	private String Address              ;
	private String PostCode             ;
	private String InstReprIDType       ;
	private String InstReprIDCode       ;
	private String InstReprName         ;
	private String ControlHolder        ;
	private String TransactorName       ;
	private String TransactorCertType   ;
	private String TransactorCertNo     ;
	private String InstTranCertValidDate;
	
	public String getClearingAgencyCode() {
		return ClearingAgencyCode;
	}
	public void setClearingAgencyCode(String clearingAgencyCode) {
		ClearingAgencyCode = clearingAgencyCode;
	}
	public String getAcctNameOfInvestorInClearingAgency() {
		return AcctNameOfInvestorInClearingAgency;
	}
	public void setAcctNameOfInvestorInClearingAgency(
			String acctNameOfInvestorInClearingAgency) {
		AcctNameOfInvestorInClearingAgency = acctNameOfInvestorInClearingAgency;
	}
	public String getAcctNoOfInvestorInClearingAgency() {
		return AcctNoOfInvestorInClearingAgency;
	}
	public void setAcctNoOfInvestorInClearingAgency(
			String acctNoOfInvestorInClearingAgency) {
		AcctNoOfInvestorInClearingAgency = acctNoOfInvestorInClearingAgency;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getInvestorName() {
		return InvestorName;
	}
	public void setInvestorName(String investorName) {
		InvestorName = investorName;
	}
	public String getCertificateType() {
		return CertificateType;
	}
	public void setCertificateType(String certificateType) {
		CertificateType = certificateType;
	}
	public String getCertificateNo() {
		return CertificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		CertificateNo = certificateNo;
	}
	public String getCertValidDate() {
		return CertValidDate;
	}
	public void setCertValidDate(String certValidDate) {
		CertValidDate = certValidDate;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public String getMobileTelNo() {
		return MobileTelNo;
	}
	public void setMobileTelNo(String mobileTelNo) {
		MobileTelNo = mobileTelNo;
	}
	public String getOfficeTelNo() {
		return OfficeTelNo;
	}
	public void setOfficeTelNo(String officeTelNo) {
		OfficeTelNo = officeTelNo;
	}
	public String getFaxNo() {
		return FaxNo;
	}
	public void setFaxNo(String faxNo) {
		FaxNo = faxNo;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPostCode() {
		return PostCode;
	}
	public void setPostCode(String postCode) {
		PostCode = postCode;
	}
	public String getInstReprIDType() {
		return InstReprIDType;
	}
	public void setInstReprIDType(String instReprIDType) {
		InstReprIDType = instReprIDType;
	}
	public String getInstReprIDCode() {
		return InstReprIDCode;
	}
	public void setInstReprIDCode(String instReprIDCode) {
		InstReprIDCode = instReprIDCode;
	}
	public String getInstReprName() {
		return InstReprName;
	}
	public void setInstReprName(String instReprName) {
		InstReprName = instReprName;
	}
	public String getControlHolder() {
		return ControlHolder;
	}
	public void setControlHolder(String controlHolder) {
		ControlHolder = controlHolder;
	}
	public String getTransactorName() {
		return TransactorName;
	}
	public void setTransactorName(String transactorName) {
		TransactorName = transactorName;
	}
	public String getTransactorCertType() {
		return TransactorCertType;
	}
	public void setTransactorCertType(String transactorCertType) {
		TransactorCertType = transactorCertType;
	}
	public String getTransactorCertNo() {
		return TransactorCertNo;
	}
	public void setTransactorCertNo(String transactorCertNo) {
		TransactorCertNo = transactorCertNo;
	}
	public String getInstTranCertValidDate() {
		return InstTranCertValidDate;
	}
	public void setInstTranCertValidDate(String instTranCertValidDate) {
		InstTranCertValidDate = instTranCertValidDate;
	}
	@Override
	public String toString() {
		return "OpenAccountOrgRequest [ClearingAgencyCode="
				+ ClearingAgencyCode + ", AcctNameOfInvestorInClearingAgency="
				+ AcctNameOfInvestorInClearingAgency
				+ ", AcctNoOfInvestorInClearingAgency="
				+ AcctNoOfInvestorInClearingAgency + ", Province=" + Province
				+ ", City=" + City + ", InvestorName=" + InvestorName
				+ ", CertificateType=" + CertificateType + ", CertificateNo="
				+ CertificateNo + ", CertValidDate=" + CertValidDate
				+ ", EmailAddress=" + EmailAddress + ", MobileTelNo="
				+ MobileTelNo + ", OfficeTelNo=" + OfficeTelNo + ", FaxNo="
				+ FaxNo + ", Address=" + Address + ", PostCode=" + PostCode
				+ ", InstReprIDType=" + InstReprIDType + ", InstReprIDCode="
				+ InstReprIDCode + ", InstReprName=" + InstReprName
				+ ", ControlHolder=" + ControlHolder + ", TransactorName="
				+ TransactorName + ", TransactorCertType=" + TransactorCertType
				+ ", TransactorCertNo=" + TransactorCertNo
				+ ", InstTranCertValidDate=" + InstTranCertValidDate
				+ ", toString()=" + super.toString() + "]";
	}
	
}
