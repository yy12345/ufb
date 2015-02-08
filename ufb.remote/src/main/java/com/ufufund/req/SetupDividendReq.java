package com.ufufund.req;

public class SetupDividendReq {

	/*
	 * 
版本号	version	char(10)	R	版本号默认为1.0
机构标识	instId	char(10)	R	直销平台分配给基金公司的机构号
个人/机构标志	isIndividual	char (1)	R	0-机构，1-个人
基金公司用户交易账号	contractNo	char (16)	R	
直销平台申请单据流水号	applicationNo	char (30)	R	

基金公司申请单流水号	appSheetSerialNo	char (24)	C	同一销售机构不能重复；交易失败时为空；
交易所属日期	transactionDate	char (8)	C	基金的交易日期格式为：YYYYMMDD 非撤销交易不可为空。国庆前一天晚上发生的交易，其交易所属日期是国庆后第一个交易日
交易发生时间	transactionTime	char (14)	R	基金公司订单落地时间格式为：YYYYMMDDHHmmSS
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
//	private String appSheetSerialNo;
//	private String transactionDate;
//	private String transactionTime;
//	private String errorCode;
//	private String errorMessage;
//	
//	private String extension;
}
