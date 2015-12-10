package com.ufufund.ufb.model.db;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FundBalance{

	private String custno;
	private String fundcorpno;
	private String tradeacco;
	private String fundcode;
	private String shareclass; // 收费方式 A:前收费B:后收费
	private String defdividendmethod; // 0-红利转投，1-现金分红

	private BigDecimal totalfundvol; // 总份额
	private BigDecimal availablevol; // 用户当前可用份额
	private BigDecimal totalfrozenvol; // 冻结份额
	private BigDecimal undistributemonetaryincome; // 未付收益金额

	private BigDecimal funddayincome; // 每日收益
	private BigDecimal totalincome; // 累计收益

	private String lastdate; // 最后余额变动日期
	private String updatetime; // 更新时间
}
