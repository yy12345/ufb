package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractRequest;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankAuthRequest extends AbstractRequest{

	private String clearingAgencyCode;
	private String acctNameOfInvestorInClearingAgency;
	private String acctNoOfInvestorInClearingAgency;
	private String certificateType;
	private String certificateNo;
	private String mobileTelNo;
	
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
	public String getMobileTelNo() {
		return mobileTelNo;
	}
	public void setMobileTelNo(String mobileTelNo) {
		this.mobileTelNo = mobileTelNo;
	}
	@Override
	public String toString() {
		return "BankAuthRequest [clearingAgencyCode=" + clearingAgencyCode
				+ ", acctNameOfInvestorInClearingAgency="
				+ acctNameOfInvestorInClearingAgency
				+ ", acctNoOfInvestorInClearingAgency="
				+ acctNoOfInvestorInClearingAgency + ", certificateType="
				+ certificateType + ", certificateNo=" + certificateNo
				+ ", mobileTelNo=" + mobileTelNo + ", toString()="
				+ super.toString() + "]";
	}
	
}
