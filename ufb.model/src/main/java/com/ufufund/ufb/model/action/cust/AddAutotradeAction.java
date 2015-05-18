package com.ufufund.ufb.model.action.cust;

import java.math.BigDecimal;

import com.ufufund.ufb.model.action.CommonAction;
import com.ufufund.ufb.model.enums.Apkind;

public class AddAutotradeAction extends CommonAction {

	

	
	//private String autoid;// char(24) default '' comment 'ID',
	private String autoname;// varchar(50) default '' comment '自动交易名称',
	private String custno;// char(24) default '' comment '客户编号',
	//private String state;// char(1) default null comment 'N:正常 C：删除 P：暂停  ',
	private Apkind apkind;// char(3) default null comment '业务类型', AUTO开头业务类型
	private String type;// char(1) default null comment '类型 S单次，E多次',
	private String cycle;// char(2) default null comment 'MM=每月；WW=每周;DD 每隔多少天；',
	private String dat;// char(2) default null comment '扣款日',
	private String frombankserialid;// char(24) default null comment '源银行卡id',
	//private int    fromaccoid;// int(11) default null comment '源交易账号编号',
	//private String fromtradeacco;// varchar(17) default null comment '源交易账号',
	
	private String fromfundcode;// varchar(6) default null comment '源基金代码',
	private String fromfundcorpno;// char(24) default null comment '源归属基金公司',
	private String fromchargetype;// char(1) default null comment '源A：前收费 B：后收费',
	
	private String tobankserialid;// char(24) default null comment '目标银行卡id',
	//private int    toaccoid;// int(11) default null comment '目标交易账号编号',
	//private String totradeacco;// varchar(17) default null comment '目标交易账号',
	
	private String tofundcode;// varchar(6) default null comment '目标基金代码',
	private String tofundcorpno;// char(24) default null comment '目标归属基金公司',
	private String tochargetype;// char(1) default null comment '目标A：前收费 B：后收费',
	
	private BigDecimal autoamt=BigDecimal.ZERO;// decimal(16,2) default null comment '金额',
	private BigDecimal autovol=BigDecimal.ZERO;// decimal(16,2) default null comment '份额',
	//private String lastdate;// char(8) default null comment '最近扣款日期',
	//private String nextdate;// char(8) default null comment '下一扣款日期',
	private String summary;// varchar(100) default null comment '备注',
	
	
	public String getAutoname() {
		return autoname;
	}
	public void setAutoname(String autoname) {
		this.autoname = autoname;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	public String getFrombankserialid() {
		return frombankserialid;
	}
	public void setFrombankserialid(String frombankserialid) {
		this.frombankserialid = frombankserialid;
	}
	public String getFromfundcode() {
		return fromfundcode;
	}
	public void setFromfundcode(String fromfundcode) {
		this.fromfundcode = fromfundcode;
	}
	public String getFromfundcorpno() {
		return fromfundcorpno;
	}
	public void setFromfundcorpno(String fromfundcorpno) {
		this.fromfundcorpno = fromfundcorpno;
	}
	public String getFromchargetype() {
		return fromchargetype;
	}
	public void setFromchargetype(String fromchargetype) {
		this.fromchargetype = fromchargetype;
	}
	public String getTobankserialid() {
		return tobankserialid;
	}
	public void setTobankserialid(String tobankserialid) {
		this.tobankserialid = tobankserialid;
	}
	public String getTofundcode() {
		return tofundcode;
	}
	public void setTofundcode(String tofundcode) {
		this.tofundcode = tofundcode;
	}
	public String getTofundcorpno() {
		return tofundcorpno;
	}
	public void setTofundcorpno(String tofundcorpno) {
		this.tofundcorpno = tofundcorpno;
	}
	public String getTochargetype() {
		return tochargetype;
	}
	public void setTochargetype(String tochargetype) {
		this.tochargetype = tochargetype;
	}
	public BigDecimal getAutoamt() {
		return autoamt;
	}
	public void setAutoamt(BigDecimal autoamt) {
		this.autoamt = autoamt;
	}
	public BigDecimal getAutovol() {
		return autovol;
	}
	public void setAutovol(BigDecimal autovol) {
		this.autovol = autovol;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Apkind getApkind() {
		return apkind;
	}
	public void setApkind(Apkind apkind) {
		this.apkind = apkind;
	}
	
	
	
	
	
	
}
