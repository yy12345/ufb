package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractRequest;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankVeriResponse extends AbstractRequest{

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
