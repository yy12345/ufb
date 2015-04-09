package com.ufufund.ufb.remote.xml.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractResponse {

	@XmlElement(name="Version")
	private String version;
	
	@XmlElement(name="MerchantId")
	private String merchantId;
	
	@XmlElement(name="DistributorCode")
	private String distributorCode;
	
	@XmlElement(name="BusinType")
	private String businType;
	
	@XmlElement(name="ApplicationNo")
	private String applicationNo;
	
	@XmlElement(name="ReturnCode")
	private String returnCode;
	
	@XmlElement(name="ReturnMsg")
	private String returnMsg;
	
	@XmlElement(name="Extension")
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
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	@Override
	public String toString() {
		return "AbstractResponse [version=" + version + ", merchantId="
				+ merchantId + ", distributorCode=" + distributorCode
				+ ", businType=" + businType + ", applicationNo="
				+ applicationNo + ", returnCode=" + returnCode + ", returnMsg="
				+ returnMsg + ", extension=" + extension + "]";
	}
	
}
