package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractResponse;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankAuthResponse extends AbstractResponse{

	private String AccoreqSerial;

	public String getAccoreqSerial() {
		return AccoreqSerial;
	}

	public void setAccoreqSerial(String accoreqSerial) {
		AccoreqSerial = accoreqSerial;
	}

	@Override
	public String toString() {
		return "BankAuthResponse [AccoreqSerial=" + AccoreqSerial
				+ ", toString()=" + super.toString() + "]";
	}

}
