package com.ufufund.ufb.model.hft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankAuthRequest extends AbstractRequest{

	private String ClearingAgencyCode;
	private String AcctNameOfInvestorInClearingAgency;
	private String AcctNoOfInvestorInClearingAgency;
	private String CertificateType;
	private String CertificateNo;
	private String MobileTelNo;
	private String AccoreqSerial;
	
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
	public String getMobileTelNo() {
		return MobileTelNo;
	}
	public void setMobileTelNo(String mobileTelNo) {
		MobileTelNo = mobileTelNo;
	}
	public String getAccoreqSerial() {
		return AccoreqSerial;
	}
	public void setAccoreqSerial(String accoreqSerial) {
		AccoreqSerial = accoreqSerial;
	}
	@Override
	public String toString() {
		return "BankAuthRequest [ClearingAgencyCode=" + ClearingAgencyCode
				+ ", AcctNameOfInvestorInClearingAgency="
				+ AcctNameOfInvestorInClearingAgency
				+ ", AcctNoOfInvestorInClearingAgency="
				+ AcctNoOfInvestorInClearingAgency + ", CertificateType="
				+ CertificateType + ", CertificateNo=" + CertificateNo
				+ ", MobileTelNo=" + MobileTelNo + ", AccoreqSerial="
				+ AccoreqSerial + ", toString()=" + super.toString() + "]";
	}
	
}
