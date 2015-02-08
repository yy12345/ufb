package com.ufufund.req;

public class SynRiskEvalReq {

	/*
	 * 
版本号	version	char(10)	R	版本号默认为1.0
机构标识	instId	char(10)	R	直销平台分配给基金公司的机构号
个人/机构标志	isIndividual	char (1)	R	0-机构，1-个人
基金公司客户交易账号	contractNo	char(18)	R	基金公司银行唯一标识
直销平台申请单据流水号	applicationNo	char (30)	R	

错误代码	errorCode	char (4)	R	见交易返回码
错误描述	errorMessage	char (256)	R	
消息扩展	extension	char (500)	O	

	 */
	
//	private String version;
//	private String instId;
//	private String isIndividual;
//	private String contractNo;
//	private String applicationNo;
//	
//	private String errorCode;
//	private String errorMessage;
//	private String extension;
}
