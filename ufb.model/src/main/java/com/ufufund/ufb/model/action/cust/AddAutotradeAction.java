package com.ufufund.ufb.model.action.cust;

import java.math.BigDecimal;

import com.ufufund.ufb.model.action.CommonAction;
import com.ufufund.ufb.model.enums.AutoTradeType;

import lombok.Data;
@Data
public class AddAutotradeAction extends CommonAction {
	
	private String autoname;// varchar(50) default '' comment '自动交易名称',
	private String custno;// char(24) default '' comment '客户编号',
	private AutoTradeType tradetype;// char(3) default null comment '业务类型', AUTO开头业务类型
	private String type;// char(1) default null comment '类型 S单次，E多次',
	private String cycle;// char(2) default null comment 'MM=每月；WW=每周;DD 每隔多少天； 如果当天非工作日，自动推迟到下个工作日 ',
	private String dat;// char(2) default null comment '扣款日',
	private String fromfundcorpno;// char(24) default null comment '源归属基金公司',
	private String fromfundcode;// varchar(6) default null comment '源基金代码',
	private String fromchargetype;// char(1) default null comment '源A：前收费 B：后收费',
	private String frombankserialid;// char(24) default null comment '源银行卡id',
	private String frombankacco;// char(24) default null comment '源银行卡id',
	private String fromaccoid;// int(11) default null comment '源交易账号编号',
	private String fromtradeacco;// varchar(17) default null comment '源交易账号',
	private String tofundcorpno;// char(24) default null comment '目标归属基金公司',
	private String tofundcode;// varchar(6) default null comment '目标基金代码',
	private String tochargetype;// char(1) default null comment '目标A：前收费 B：后收费',
	private String tobankserialid;// char(24) default null comment '目标银行卡id',
	private String tobankacco;// char(24) default null comment '源银行卡id',
	private String toaccoid;// int(11) default null comment '目标交易账号编号',
	private String totradeacco;// varchar(17) default null comment '目标交易账号',
	private BigDecimal autoamt=BigDecimal.ZERO;// decimal(16,2) default null comment '金额',
	private BigDecimal autovol=BigDecimal.ZERO;// decimal(16,2) default null comment '份额',
	private String lastdate;// char(8) default null comment '最近扣款日期',
	private String nextdate;// char(8) default null comment '下一扣款日期',
	private String summary;// varchar(100) default null comment '备注',
	private String tradepwd;
	
	private String detailid;// char(24) default '' comment 'ID',
	
	
}
