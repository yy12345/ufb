package com.ufufund.ufb.model.db;



import java.util.Date;

import lombok.Data;
@Data
public class Orgplandetail {
	private static final long serialVersionUID = 1L;
	
	private String orgid;// char(24) NOT NULL COMMENT '机构ID',
	private String detailid;// char(24) DEFAULT NULL,
	private String planid;// char(24) DEFAULT NULL COMMENT '计划ID',
	private String studentid;// char(24) DEFAULT NULL COMMENT '学生ID',
	private String payappamount="0";// decimal(16,2) DEFAULT NULL COMMENT '应缴金额',
	private String paydiscount="0";// decimal(16,2) DEFAULT NULL COMMENT '折扣金额',
	private String payackamount="0";// decimal(16,2) DEFAULT NULL COMMENT '实缴金额'
	private String stats;// char(1) DEFAULT NULL,
	private String ispay;// char(1) DEFAULT NULL,
	
	private String paycustno;// char(24) DEFAULT NULL COMMENT '缴费客户号',
	//private String paydate;// timestamp NULL DEFAULT NULL COMMENT '缴费日期',
	//private String plandate;// timestamp NULL DEFAULT NULL,
	private String acktype;// char(1) CHARACTER SET utf8mb4 DEFAULT 'U' COMMENT '扣款方式 U-幼富宝  B-银行卡',
	private String ackcustno;// char(24) DEFAULT NULL COMMENT '缴费客户号',
	private String ackbankcardid;// char(24) DEFAULT NULL COMMENT '缴费银行卡id',
	private String acktradeaccoid;// char(24) DEFAULT NULL COMMENT '缴费交易帐号id',
	private String acktradeacco;// char(17) DEFAULT NULL COMMENT '缴费交易帐号',
	//private String ackdate;// timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '确认日',
	private String redeemstats;// char(1) DEFAULT NULL COMMENT '幼富宝赎回状态',
	private String redeemdate;// timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '幼富宝赎回日'
	
	
}
