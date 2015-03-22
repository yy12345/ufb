package com.ufufund.ufb.remote.xml.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Requestbody<T> {

	@XmlElement(name = "Request")
	private T request;

	public T getRequest() {
		return request;
	}

	public void setRequest(T request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "Requestbody [request=" + request + "]";
	}
	
	
}
