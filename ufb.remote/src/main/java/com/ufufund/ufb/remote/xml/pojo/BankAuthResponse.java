package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.ufufund.ufb.remote.xml.base.AbstractResponse;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankAuthResponse extends AbstractResponse{

	@XmlElement(name="AccoreqSerial")
	private String accoreqSerial;

	public String getAccoreqSerial() {
		return accoreqSerial;
	}

	public void setAccoreqSerial(String accoreqSerial) {
		this.accoreqSerial = accoreqSerial;
	}

	@Override
	public String toString() {
		return "BankAuthResponse [accoreqSerial=" + accoreqSerial
				+ ", toString()=" + super.toString() + "]";
	}

}
