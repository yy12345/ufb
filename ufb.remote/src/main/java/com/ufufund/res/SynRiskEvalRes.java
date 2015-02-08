package com.ufufund.res;

public class SynRiskEvalRes {

	/*
	 * 3.1.12	风险测试结果同步
版本号	version	char(10)	R	版本号默认为1.0
机构标识	instId	char(10)	R	直销平台分配给基金公司的机构号
个人/机构标志	isIndividual	char (1)	R	0-机构，1-个人
基金公司用户交易账号	contractNo	char(17)	R	与银行卡一一对应，每个银行都有一个唯一的交易账号，一个用户可以有多个交易账号。
直销平台申请单据流水号	applicationNo	char (30)	R	

投资人姓名	investorName	char (120)	R	投资人姓名
证件类型	certType	char (2)	R	见证件类型一览表
证件号码	certificateNo	char (30)	R	
风险承受能力类型	riskType	char (1)	R	基金直销平台风险等级
测评日期	evaluateDate	char (8)	R	YYYYMMDD （24小时制）

测评时间	evaluateTime	char (6)	R	HHMMSS（24小时制）
消息扩展	extension	char (500)	O	

	 */
	
//	private String version;
//	private String instId;
//	private String isIndividual;
//	private String contractNo;
//	private String applicationNo;
//	
//	private String investorName;
//	private String certType;
//	private String certificateNo;
//	private String riskType;
//	private String evaluateDate;
//	
//	private String evaluateTime;
//	private String extension;
}
