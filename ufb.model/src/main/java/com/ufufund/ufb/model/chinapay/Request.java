package com.ufufund.ufb.model.chinapay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name="GZELINK")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Request {

	private RequestInfo INFO;
	private RequestBody BODY;

}
