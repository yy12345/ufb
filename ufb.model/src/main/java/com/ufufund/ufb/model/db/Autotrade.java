package com.ufufund.ufb.model.db;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Autotrade {

	private String autoid;     // 自动交易ID
	private String autoname;   // 自动交易名称
	private String custno;     // 客户编号
	private String state;      // N:正常 C：删除 P：暂停 
	private String tradetype;  // 业务类型
	private String type;       // 类型 S单次，E多次
	private String cycle;      // MM=每月；WW=每周;DD 每隔多少天；如果当天非工作日，自动推迟到下个工作日 
	private String dat;        // 扣款日

	private String fromfundcorpno;  // 源归属基金公司
	private String fromfundcode;    // 源基金代码
	private String fromchargetype;  // 源A：前收费  B：后收费
	private String frombankserialid;// 源银行卡id
	private String frombankacco;
	private String frombanknm;
	private String frombankno;
	private String frombanktail;
	private String fromtradeacco;   // 源交易账号

	private String tofundcorpno;    // 目标归属基金公司
	private String tofundcode;      // 目标基金代码
	private String tochargetype;	//  目标A：前收费 B：后收费
	private String totradeacco;		//  目标交易账号
	private String tobankserialid; //  目标银行卡id
	private String tobankacco;//
	private String tobanknm;
    private String bankno;
	
	private BigDecimal autoamt;//金额
	private BigDecimal autovol;//份额
	private String lastdate;  // 最近扣款日期
	private String nextdate;  // 下一扣款日期
	private String summary;   // 备注
	private String createtime;
	private String updatetime;
    private String detailid;
    private String tradepwd;
    private String step;
}
