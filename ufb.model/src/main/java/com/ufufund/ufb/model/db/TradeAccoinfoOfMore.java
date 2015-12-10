package com.ufufund.ufb.model.db;

import java.math.BigDecimal;

import com.ufufund.ufb.model.action.PrintableModel;

import lombok.Data;

@Data
public class TradeAccoinfoOfMore extends PrintableModel{
	
	/** tradeacco基本属性 **/
	private String tradeacco;
	private String tradeaccost;
	private String fundcorpno;
	private String level;
	
	/** bankcardinfo表属性 **/
	private String serialid;   // 银行卡编号（流水号）
	private String custno;     // 用户编号
	private String bankno;     // 银行编码
	private String bankacco;   // 银行卡号
	private String certtype;   // 银行证件类型
	private String certno;     // 银行证件号码
	private String state;      // Y 正常；N 已解绑
	private String province;   // 开户省份
	private String city;       // 开户城市
	private String subbank;    // 开户网点
	private String createtime; // 创建时间
	private String updatetime; // 更新时间	
	private String clevel;
	
	/** 资金 **/
	private BigDecimal total = new BigDecimal("0.00");
	private BigDecimal available = new BigDecimal("0.00");
	private BigDecimal realavailable = new BigDecimal("0.00");
	private BigDecimal frozen = new BigDecimal("0.00");
	
}
