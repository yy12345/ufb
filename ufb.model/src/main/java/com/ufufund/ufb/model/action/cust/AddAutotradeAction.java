package com.ufufund.ufb.model.action.cust;

import java.math.BigDecimal;

import com.ufufund.ufb.model.enums.AutoTradeType;

import lombok.Data;
@Data
public class AddAutotradeAction{
	
	private String autoname;// 自动交易名称
	private String custno;// 客户编号
	private AutoTradeType tradetype;// 业务类型 AUTO开头业务类型
	private String type;// 类型 S单次，E多次
	private String cycle;// MM=每月；WW=每周;DD 每隔多少天； 如果当天非工作日，自动推迟到下个工作日 
	private String dat;// 扣款日
	private String fromfundcorpno;// 源归属基金公司
	private String fromfundcode;// 源基金代码
	private String fromchargetype;// 源A：前收费 B：后收费
	private String frombankserialid;// 源银行卡id
	private String frombankacco;// 源银行卡id
	private String fromtradeacco;// 源交易账号
	private String tofundcorpno;// 目标归属基金公司
	private String tofundcode;// 目标基金代码
	private String tochargetype;// 目标A：前收费 B：后收费
	private String tobankserialid;// 目标银行卡id
	private String tobankacco;// 源银行卡id
	private String totradeacco;// 目标交易账号
	private BigDecimal autoamt=BigDecimal.ZERO;// 金额
	private BigDecimal autovol=BigDecimal.ZERO;// 份额
	private String lastdate;// 最近扣款日期
	private String nextdate;// 下一扣款日期
	private String summary;// 备注
	private String tradepwd;
	
	private String detailid;// detailID
	
	
}
