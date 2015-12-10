package com.ufufund.ufb.model.db;

import java.math.BigDecimal;

import com.ufufund.ufb.model.action.PrintableModel;

import lombok.Data;
@Data
public class Fdacfinalresult extends PrintableModel{
	private String  serialno;  //流水号
	private String  custno;    //客户编号
	private String  apkind;    //业务代码
	private String  workdate;  //工作日
	private String  apdt;      //申请日期
	private String  aptm;      // 申请时间
	private String  status="Y";//状态 
	
	private String  frombankserialid;//银行卡id
	private String  fromtradeacco;  // 交易账号
	private String  fromfundcode;   // 基金代码
	private String  fromfundcorpno; //归属基金公司
	private String  fromchargetype; // A：前收费 B：后收费
	private String  tobankserialid; 
	private String  totradeacco;    
	private String  tofundcode;     // 基金代码
	private String  tofundcorpno;
	private String  tochargetype;
	
	private BigDecimal appamt;//申请金额
	private BigDecimal appvol;//申请份额
	private BigDecimal ackamt;//确认金额
	private BigDecimal ackvol;//确认份额
	
	private String autoid;   // 自动交易ID
	private String orserialno;

}
