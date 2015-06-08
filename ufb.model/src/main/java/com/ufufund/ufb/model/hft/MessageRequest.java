package com.ufufund.ufb.model.hft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Message")
public class MessageRequest {

	@XmlElement(name="Requestbody", type= Requestbody.class)
	private Requestbody requestbody;
	
	@XmlElement(name="Signature")
	private String signature;

	public Requestbody getRequestbody() {
		return requestbody;
	}

	public void setRequestbody(Requestbody requestbody) {
		this.requestbody = requestbody;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "MessageRequest [requestbody=" + requestbody
				+ ", signature=" + signature + "]";
	}
	
}
