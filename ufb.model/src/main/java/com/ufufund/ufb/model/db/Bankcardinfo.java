package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class Bankcardinfo{
	private String serialid;// ;//int(11) not null comment '银行卡id',
	private String custno;// char(10) not null default '0' comment '直销平台用户id',
	private String bankno;// char(3) not null comment '银行编码',
	private String bankacco;// varchar(28) not null comment '银行卡号',
	private String bankaccodisplay;// varchar(28) not null comment '银行提示账号',
	private String bankidtp;// char(1) default null comment '银行证件类型',
	private String bankidno;// varchar(30) default null comment '银行证件号码',
	private String bankidvaliddt;// char(8) default null comment '证件有效期',
	private String bankacnm;// varchar(60) default null comment '银行开户户名',
	private String subbankno;// char(3) default null comment '银行编号',
	private String banklongname;// varchar(60) default null comment '银行开户分行名称',
	private String issign;// char(1) default null comment '是否已签约：y是；n否',
	private String signno;// varchar(40) default null comment '银行签约协议号',
	private String bankproexists;// char(2) default null comment '定期定额银行协议是否签订
									// 00:未签订 01:已签订',
	private String protocol_no;// varchar(100) default null comment '定期定额银行协议号码
								// ',
	private String state;
							
	private String opendt;
	private String closedt;
	private String disorder;
	private String updatetimestamp;
									
	
	private String bankmobile;
	private String bankprovincenm;
	private String bankcitynm;
	private String bankadd; // 支行网点
	
	private String level;   // 银行卡级别:1-幼富宝卡；2-普通卡
}
