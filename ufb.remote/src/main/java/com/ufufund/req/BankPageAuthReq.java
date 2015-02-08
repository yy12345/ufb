package com.ufufund.req;



public class BankPageAuthReq {

	/*
	 * 
	版本号	version	char(10)	R	版本号默认为1.0
机构标识	instId	char(10)	R	直销平台分配给基金公司的机构号
个人/机构标志	isIndividual	char (1)	R	0-机构，1-个人
基金公司用户交易账号	contractNo	char(17)	R	与银行卡一一对应，每个银行都有一个唯一的交易账号，一个用户可以有多个交易账号。
平台申请单据流水号	applicationNo	char(30)	R	业务流水号

提交网银的参数	respparams	char(500)	R	包含所有要提交网银的参数字段；JSON格式字符串
银行编码	characterCode 	char(30)	R	银行需要的编码
请求序列号	accoreqSerial 	char(20)	R	
网银地址	bankUrl	char(50)	R	页面鉴权方式提交的地址
错误代码	errorCode	char(4)	R	

错误描述	errorMessage	char(256)	R	
消息扩展	extension	char(500)	O	

	 * 
	 */
	
//	private String version;
//	private String instId;
//	private String isIndividual;
//	private String contractNo;
//	private String applicationNo;
//	
//	private String respparams;
//	private String characterCode;
//	private String accoreqSerial;
//	private String bankUrl;
//	private String errorCode;
//	
//	private String errorMessage;
//	private String extension;

	
}
