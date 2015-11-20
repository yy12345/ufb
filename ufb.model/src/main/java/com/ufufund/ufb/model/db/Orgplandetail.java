package com.ufufund.ufb.model.db;



import java.util.Date;

import lombok.Data;
@Data
public class Orgplandetail {
	private static final long serialVersionUID = 1L;
	
	private String orgid;           // 机构ID
	private String detailid;
	private String planid;   		 // 计划ID
	private String studentid;		 // 学生ID
	private String payappamount="0";// 应缴金额
	private String paydiscount="0";	 // 折扣金额
	private String payackamount="0"; // 实缴金额
	private String stats;			 //状态 N初始化 F家长确认
	private String ispay;            //状态：0-等待缴费;1-等待扣款;2-缴费（退费）成功; 3-待补扣；4-缴费（退费）失败；5-手工补登；9-已撤销',
	
	private String paycustno;		 // 缴费客户号
	private String acktype;			 // 扣款方式 U-幼富宝  B-银行卡
	private String ackcustno;		 // 缴费客户号
	private String ackbankcardid;	 // 缴费银行卡id
	private String acktradeacco;    // 缴费交易帐号
	private String redeemstats;     // 幼富宝赎回状态
	private String redeemdate;      // 幼富宝赎回日
	
	
}
