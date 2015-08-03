package com.ufufund.ufb.model.chinapay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ResponseInfo {

	private String TRX_CODE;
	private String VERSION;
	private String DATA_TYPE;
	private String REQ_SN;
	private String RET_CODE;
	private String ERR_MSG;
	private String SIGNED_MSG;
}
