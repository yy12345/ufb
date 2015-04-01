package com.ufufund.ufb.remote.xml.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Message")
public class MessageRequest {

	@XmlAttribute
	private String id;
	
	@XmlElement(name="Requestbody", type= Requestbody.class)
	private Requestbody requestbody;
	
	@XmlElement(name="Signature")
	private String signature;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
		return "MessageRequest [id=" + id + ", requestbody=" + requestbody
				+ ", signature=" + signature + "]";
	}

	
}