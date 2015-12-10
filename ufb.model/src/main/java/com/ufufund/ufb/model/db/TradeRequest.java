package com.ufufund.ufb.model.db;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TradeRequest{
	
	private String serialno;             
	private String sheetserialno;
	private String taserialno;
	private String custno;               
	private String fundcorpno;           
	private String tradeacco;              
	private String fundcode;              
	private String apkind ;               
	private String workday;            
	private String appdate;              
	private String apptime;              
	private String ackdate;            
	private BigDecimal appamt;               
	private BigDecimal appvol;               
	private BigDecimal ackamt;               
	private BigDecimal ackvol;               
	private String state ;               
	private String transt;               
	private String payst ;               
	private String shareclass;           
	private String dividmethod;         
	private BigDecimal fee  ;                
	private String referno ;             
	private String updatetime;
	private String bankno;
	private String banknm;
	private String bankacco;
}
