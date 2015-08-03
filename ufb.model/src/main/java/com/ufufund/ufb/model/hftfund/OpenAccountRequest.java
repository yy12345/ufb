package com.ufufund.ufb.model.hftfund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class OpenAccountRequest extends AbstractRequest{

	private String ClearingAgencyCode;
	private String AcctNameOfInvestorInClearingAgency;
	private String AcctNoOfInvestorInClearingAgency;
	private String InvestorName;
	private String CertificateType;
	private String CertificateNo;
	private String CertValidDate;
	private String EmailAddress;
	private String MobileTelNo;
	private String OfficeTelNo;
	private String FaxNo;
	private String Address;
	private String PostCode;
	private String ProtocolNo;
	
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
	public String getProtocolNo() {
		return ProtocolNo;
	}
	public void setProtocolNo(String protocolNo) {
		ProtocolNo = protocolNo;
	}
	@Override
	public String toString() {
		return "OpenAccountRequest [ClearingAgencyCode=" + ClearingAgencyCode
				+ ", AcctNameOfInvestorInClearingAgency="
				+ AcctNameOfInvestorInClearingAgency
				+ ", AcctNoOfInvestorInClearingAgency="
				+ AcctNoOfInvestorInClearingAgency + ", InvestorName="
				+ InvestorName + ", CertificateType=" + CertificateType
				+ ", CertificateNo=" + CertificateNo + ", CertValidDate="
				+ CertValidDate + ", EmailAddress=" + EmailAddress
				+ ", MobileTelNo=" + MobileTelNo + ", OfficeTelNo="
				+ OfficeTelNo + ", FaxNo=" + FaxNo + ", Address=" + Address
				+ ", PostCode=" + PostCode + ", ProtocolNo=" + ProtocolNo
				+ ", toString()=" + super.toString() + "]";
	}
	
}
