package com.ufufund.action;




public class BankPageAuth {
/* 用户从基金直销平台发起银行页面鉴权
 * 
 版本号	version	char(10)	R	版本号默认为1.0
机构标识	instId	char(10)	R	直销平台分配给基金公司的机构号
个人/机构标志	isIndividual	char (1)	R	0-机构，1-个人
基金公司用户交易账号	contractNo	char(17)	O	与银行卡一一对应，每个银行都有一个唯一的交易账号，一个用户可以有多个交易账号。
平台申请单据流水号	applicationNo	char(30)	R	业务流水号

银行编号	bankCode	char(9)	R	见银行机构代码
银行账号	cardNo	char(20)	R	银行账号
投资人姓名	investorName	char(60)	R	投资人姓名
投资人证件类型	certType	char(2)	R	见证件类型一览表
投资人证件号码	certificateNo	char(30)	R	

投资人E-MAIL地址	email	char(40)	O	
投资人手机号码	mobilePhone	char(20)	O	
消息扩展	extension	char(500)	O	
	
 * 
 * 
 */
//	private String version;
//	private String instId;
//	private String isIndividual;
//	private String contractNo;
//	private String applicationNo;
//	
//	private String bankCode;
//	private String cardNo;
//	private String investorName;
//	private String certType;
//	private String certificateNo;
//	
//	private String email;
//	private String mobilePhone;
//	private String extension;
	
}
