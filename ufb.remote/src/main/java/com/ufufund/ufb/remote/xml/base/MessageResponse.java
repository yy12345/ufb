package com.ufufund.ufb.remote.xml.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Message")
public class MessageResponse {

	@XmlAttribute
	private String id;
	
	@XmlElement(name="Responsebody", type= Responsebody.class)
	private Responsebody responsebody;
	
	@XmlElement(name="Signature")
	private String signature;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
		return "MessageRequest [id=" + id + ", requestbody=" + responsebody
				+ ", signature=" + signature + "]";
	}

	
}
