package com.ufufund.ufb.model.chinapay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ReturnDetail {

	// 账户验证字段
	private String RET_CODE;
	private String ERR_MSG;
	private String ACCOUNT_NO;
	private String ACCOUNT_NAME;
	
	// 查询结果字段
	private String SN;
	private String ACCOUNT;
}
