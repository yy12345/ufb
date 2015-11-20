package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

import lombok.Data;

@Data
public class Tradeaccoinfo extends PrintableModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String custno;     // 客户编号
	private String fundcorpno; // 交易账号类型：归属基金公司
	private String level;
	private String bankserialid;    //银行账号serialid(银行账号表pk)
	private String tradeacco;       // 交易账号(基金公司返回的交易账号)
	private String tradeaccost="N"; // 交易账号状态：n-正常 F-确认失败',
	private String opendt;          // 开户日期
	private String ackdate;          // 确认日期
}
