package com.ufufund.ufb.remote.xml.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Requestbody {

	@XmlElement(name = "Request")
	private Object request;

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "Requestbody [request=" + request + "]";
	}
	
	
}
