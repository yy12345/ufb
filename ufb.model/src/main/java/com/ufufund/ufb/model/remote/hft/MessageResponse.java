package com.ufufund.ufb.model.remote.hft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Message")
public class MessageResponse {

	@XmlElement(name="Responsebody", type= Responsebody.class)
	private Responsebody responsebody;
	
	@XmlElement(name="Signature")
	private String signature;

	public Responsebody getResponsebody() {
		return responsebody;
	}

	public void setResponsebody(Responsebody responsebody) {
		this.responsebody = responsebody;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "MessageResponse [responsebody=" + responsebody
				+ ", signature=" + signature + "]";
	}
}
