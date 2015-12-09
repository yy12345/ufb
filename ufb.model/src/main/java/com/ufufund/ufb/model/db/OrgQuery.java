package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class OrgQuery{

	private String orgid;// 机构id
	
	private String charge_type;
	private String amount;
	private String pay_date;
	
	// 学生和班级
	private String cid;// 班级id
	private String cname;// 班级名称
	private String sname;// 学生姓名
	private String sid;// 学生编号
	private String code; // 识别码
	private String custno;// 用户编号
	private String custname;// 用户名
	
	// 机构信息表
	private String orgname;// 机构名称
	private String license;
	private String address ;
	private String operator_name;
	private String operator_mobile;
	private String openbankaddr;//银行开户网点
	private String banknm;
	private String bankacco;
	
}
