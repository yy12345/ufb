package com.ufufund.ufb.model.vo;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

import lombok.Data;
@Data
public class QueryCustplandetail extends PrintableModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orgid;// char(24) NOT NULL COMMENT '机构ID',
	private String orgnm;// char(24) NOT NULL COMMENT '机构NM',
	private String detailid;// char(24) DEFAULT NULL,
	private String planid;// char(24) DEFAULT NULL COMMENT '计划ID',
	private String studentid;// char(24) DEFAULT NULL COMMENT '学生ID',
	private String studentnm;// char(24) DEFAULT NULL COMMENT '学生NM',
	private String payappamount="0";// decimal(16,2) DEFAULT NULL COMMENT '应缴金额',
	private String paydiscount="0";// decimal(16,2) DEFAULT NULL COMMENT '折扣金额',
	private String payackamount="0";// decimal(16,2) DEFAULT NULL COMMENT '实缴金额'
	private String ispay;// char(1) DEFAULT 'F' COMMENT '是否缴费 Y 已缴费 F 未缴费',
	private String paycustno;//` char(24) DEFAULT NULL COMMENT '缴费客户号',
	private String paycustnonm;//` char(24) DEFAULT NULL COMMENT '缴费客户NM',
	private String paydate;//` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP CO
	private String plandate;//` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP CO
	private String dat;     // 每月扣款日
	private String ackdate; //确认日期
	private String planname;//计划名
	private String plantype;//费用类型  F 固定、T 临时、R 退费
	private String cycletype;//类型 S单次，M多次
	private String type;     //其他类型  AT-月代扣按学期 AW-月代扣按学年 
	private String billdate;//记账周期
	private String cname;// 班级名
	private String code;//识别码
	private String startdate;//计划发起时间
	private OrgBankInfoVo orginfo;       //机构信息集合
}
