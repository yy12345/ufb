package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.ufufund.ufb.remote.xml.base.AbstractRequest;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankVeriResponse extends AbstractRequest{

	@XmlElement(name="ValidateState")
	private String validateState;

	public String getValidateState() {
		return validateState;
	}

	public void setValidateState(String validateState) {
		this.validateState = validateState;
	}

	@Override
	public String toString() {
		return "BankVeriResponse [validateState=" + validateState
				+ ", toString()=" + super.toString() + "]";
	}

}
