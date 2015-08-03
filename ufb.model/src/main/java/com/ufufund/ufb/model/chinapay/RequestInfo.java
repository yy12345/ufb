package com.ufufund.ufb.model.chinapay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class RequestInfo {

	private String TRX_CODE;
	private String VERSION;
	private String DATA_TYPE;
	private String LEVEL;
	private String USER_NAME;
	private String USER_PASS;
	private String REQ_SN;
	private String SIGNED_MSG;
	
}
