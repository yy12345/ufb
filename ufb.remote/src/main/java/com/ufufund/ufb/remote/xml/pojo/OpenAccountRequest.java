package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractRequest;

@XmlAccessorType(XmlAccessType.FIELD)
public class OpenAccountRequest extends AbstractRequest{

	private String clearingAgencyCode;
	private String acctNameOfInvestorInClearingAgency;
	private String acctNoOfInvestorInClearingAgency;
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
	@Override
	public String toString() {
		return "OpenAccountRequest [clearingAgencyCode=" + clearingAgencyCode
				+ ", acctNameOfInvestorInClearingAgency="
				+ acctNameOfInvestorInClearingAgency
				+ ", acctNoOfInvestorInClearingAgency="
				+ acctNoOfInvestorInClearingAgency + ", investorName="
				+ investorName + ", certificateType=" + certificateType
				+ ", certificateNo=" + certificateNo + ", certValidDate="
				+ certValidDate + ", emailAddress=" + emailAddress
				+ ", mobileTelNo=" + mobileTelNo + ", officeTelNo="
				+ officeTelNo + ", faxNo=" + faxNo + ", address=" + address
				+ ", postCode=" + postCode + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
