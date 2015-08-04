package com.ufufund.ufb.model.chinapay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class TransDetail {

	// 账户验证字段
	private String BANK_CODE;
	private String ACCOUNT_NO;
	private String ACCOUNT_NAME;
	private String ID_TYPE;
	private String ID;
	private String TEL;
	
	// 代收付字段
	private String ACCOUNT_PROP;	// 账号属性：0-对私；1-对公；可不填，默认为对私。
	private Integer SN;
	private Integer AMOUNT;
	private String RECKON_ACCOUNT;	// 清分账户，根据需要填写
	private String PROVINCE;		// 省份，根据需要填写
	private String CITY;			// 城市，根据需要填写
}
