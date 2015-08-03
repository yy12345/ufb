package com.ufufund.ufb.model.hftfund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankAuthResponse extends AbstractResponse{

	private String OtherSerial;
	private String ProtocolNo;
	
	public String getOtherSerial() {
		return OtherSerial;
	}
	public void setOtherSerial(String otherSerial) {
		OtherSerial = otherSerial;
	}
	public String getProtocolNo() {
		return ProtocolNo;
	}
	public void setProtocolNo(String protocolNo) {
		ProtocolNo = protocolNo;
	}
	@Override
	public String toString() {
		return "BankAuthResponse [OtherSerial=" + OtherSerial + ", ProtocolNo="
				+ ProtocolNo + ", toString()=" + super.toString() + "]";
	}

}
