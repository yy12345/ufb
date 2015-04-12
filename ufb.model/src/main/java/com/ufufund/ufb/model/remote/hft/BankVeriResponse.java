package com.ufufund.ufb.model.remote.hft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankVeriResponse extends AbstractResponse{

	private String ValidateState;
	private String ProtocolNo;
	
	public String getValidateState() {
		return ValidateState;
	}
	public void setValidateState(String validateState) {
		ValidateState = validateState;
	}
	public String getProtocolNo() {
		return ProtocolNo;
	}
	public void setProtocolNo(String protocolNo) {
		ProtocolNo = protocolNo;
	}
	@Override
	public String toString() {
		return "BankVeriResponse [ValidateState=" + ValidateState
				+ ", ProtocolNo=" + ProtocolNo + ", toString()="
				+ super.toString() + "]";
	}
}
