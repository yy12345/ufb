package com.ufufund.ufb.model.chinapay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class TransSum {

	private String BUSINESS_CODE;
	private String MERCHANT_ID;
	private Integer TOTAL_ITEM;
	private Integer TOTAL_SUM;
	
}
