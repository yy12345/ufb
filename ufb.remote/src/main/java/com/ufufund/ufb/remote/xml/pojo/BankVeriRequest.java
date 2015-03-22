package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractRequest;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankVeriRequest extends AbstractRequest{

	private String clearingAgencyCode;
	private String acctNameOfInvestorInClearingAgency;
	private String acctNoOfInvestorInClearingAgency;
	private String certificateType;
	private String certificateNo;       
	private String mobileTelNo;
	private String mobileAutoCode;
	private String accoreqSerial;
	
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
	@Override
	public String toString() {
		return "BankVeriRequest [clearingAgencyCode=" + clearingAgencyCode
				+ ", acctNameOfInvestorInClearingAgency="
				+ acctNameOfInvestorInClearingAgency
				+ ", acctNoOfInvestorInClearingAgency="
				+ acctNoOfInvestorInClearingAgency + ", certificateType="
				+ certificateType + ", certificateNo=" + certificateNo
				+ ", mobileTelNo=" + mobileTelNo + ", mobileAutoCode="
				+ mobileAutoCode + ", accoreqSerial=" + accoreqSerial
				+ ", toString()=" + super.toString() + "]";
	}
	
}
