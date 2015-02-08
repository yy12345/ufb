package com.ufufund.req;

public class BuyApplyNotifyReq {

	/*
	 * 
版本号	version	char(7)	R	目前版本号：1.0
机构标识	instId	char(10)	R	报文发送方的机构标识，由直销平台分配。
个人/机构标志	isIndividual	char (1)	R	0-机构，1-个人
基金公司用户交易账号	contractNo	char(17)	R	与银行卡一一对应，每个银行都有一个唯一的交易账号，一个用户可以有多个交易账号。
平台申请单据流水号	applicationNo	char (30)	R	业务流水号

交易所属日期	transactionDate	char (8)	R	基金的交易日期格式为：YYYYMMDD
交易发生时间	transactionTime	char (14)	R	基金公司订单落地时间格式为：YYYYMMDDHHmmSS
错误代码	errorCode	char (4)	R	
错误描述	errorMessage	char (256)	R	
消息扩展	extension	char (500)	O	

	 * 
	 */
	
	
//	private String version;
//	private String instId;
//	private String isIndividual;
//	private String contractNo;
//	private String applicationNo;
//	
//	private String transactionDate;
//	private String transactionTime;
//	private String errorCode;
//	private String errorMessage;
//	private String extension;
}
