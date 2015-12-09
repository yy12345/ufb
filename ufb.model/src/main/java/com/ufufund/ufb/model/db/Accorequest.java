package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class Accorequest {
	private String  serialno;// varchar(24) not null comment '流水号',
	private String  custno;//char(10) not null comment '客户编号',
	private String  fundcorpno;// char(2) not null comment '交易账号类型：归属基金公司',
	private String  invtp;// char(1) not null comment '投资人类型 0：机构 1：个人',
	private String  name;// varchar(120) not null comment '投资人姓名',
	private String  idtp;// char(1) not null comment '证件类型',
	private String  idno;// varchar(30) not null comment '证件号码',
	private String  telno;// varchar(24) default null comment '固定电话',
	private String  mobileno;// varchar(24) not null comment '手机号',
	private String  email;//varchar(40) default null comment '电子信箱',
	private String  faxno;// varchar(24) default null comment '传真号',
	private String  postcode;// varchar(24) default null comment '邮政编码',
	private String  addr;// varchar(120) default null comment '地址',
	private String  melonmd;// varchar(1) default null comment '分红方式',
	private String  bankno;//char(3) not null comment '银行代码',
	private String  banklongname;// varchar(60) not null comment '银行全称',
	private String  bankacnm;// varchar(60) not null comment '银行户名',
	private String  bankacco;// varchar(28) not null comment '银行账号',
	private String  apdt;// char(8) not null comment '申请日期',
	private String  aptm;//char(6) not null comment '申请时间',
	private String  birthdary;// varchar(8) default null comment '生日',
	private String  provincecode;// varchar(2) default null comment '省份代码',
	private String  cityname;// varchar(50) default null comment '城市名称',
	private String  workdate;// char(8) not null comment '工作日',

}
