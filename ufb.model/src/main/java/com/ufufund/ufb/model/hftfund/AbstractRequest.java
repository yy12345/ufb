package com.ufufund.ufb.model.hftfund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractRequest {

	private String Version;
	private String MerchantId;
	private String DistributorCode;
	private String BusinType;
	private String ApplicationNo;
	private String Extension;
	
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getMerchantId() {
		return MerchantId;
	}
	public void setMerchantId(String merchantId) {
		MerchantId = merchantId;
	}
	public String getDistributorCode() {
		return DistributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		DistributorCode = distributorCode;
	}
	public String getBusinType() {
		return BusinType;
	}
	public void setBusinType(String businType) {
		BusinType = businType;
	}
	public String getApplicationNo() {
		return ApplicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		ApplicationNo = applicationNo;
	}
	public String getExtension() {
		return Extension;
	}
	public void setExtension(String extension) {
		Extension = extension;
	}
	@Override
	public String toString() {
		return "AbstractRequest [Version=" + Version + ", MerchantId="
				+ MerchantId + ", DistributorCode=" + DistributorCode
				+ ", BusinType=" + BusinType + ", ApplicationNo="
				+ ApplicationNo + ", Extension=" + Extension + "]";
	}
	
}
