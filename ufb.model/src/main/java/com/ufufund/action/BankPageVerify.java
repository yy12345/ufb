package com.ufufund.action;

public class BankPageVerify {
	/* 用户从基金直销平台发起验证信息的校验
	 * 
	 * 版本号	version	char(10)	R	版本号默认为1.0
机构标识	instId	char(10)	R	直销平台分配给基金公司的机构号
个人/机构标志	isIndividual	char (1)	R	0-机构，1-个人
基金公司用户交易账号	contractNo	char(17)	O	与银行卡一一对应，每个银行都有一个唯一的交易账号，一个用户可以有多个交易账号。
平台申请单据流水号	applicationNo	char(30)	R	业务流水号

银行编号	bankCode	char(9)	R	见银行机构代码
请求报文	reqParams	char(500)	R	包含银行返回的所有字段信息的JSON格式字符串，Base64编码该字符串
消息扩展	extension	char(500)	O	

	 * 
	 */
	
//	private String version;
//	private String instId;
//	private String isIndividual;
//	private String contractNo;
//	private String applicationNo;
//	
//	private String bankCode;
//	private String reqParams;
//	private String extension;
}
