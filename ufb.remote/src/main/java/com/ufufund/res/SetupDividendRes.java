package com.ufufund.res;

public class SetupDividendRes {

	
	/*
	 * 3.1.11	修改分红方式
	 * 
版本号	version	char(10)	R	版本号默认为1.0
机构标识	instId	char(10)	R	直销平台分配给基金公司的机构号
个人/机构标志	isIndividual	char (1)	R	0-机构，1-个人
基金公司用户交易账号	contractNo	char(17)	R	与银行卡一一对应，每个银行都有一个唯一的交易账号，一个用户可以有多个交易账号。
直销平台申请单据流水号	applicationNo	char (30)	R	

业务代码	businessCode	char (3)	R	编码见业务类型代码一览表
基金代码	fundCode	char (6)	R	
收费方式	chargeType	char (1)	R	A-前收费,B后收费
默认分红方式	dividendType	char(1)	R	0－红利转投，1-现金分红
消息扩展	extension	char (500)	O	

	 */
//	private String version;
//	private String instId;
//	private String isIndividual;
//	private String contractNo;
//	private String applicationNo;
//	
//	private String businessCode;
//	private String fundCode;
//	private String chargeType;
//	private String dividendType;
//	private String extension;
}
