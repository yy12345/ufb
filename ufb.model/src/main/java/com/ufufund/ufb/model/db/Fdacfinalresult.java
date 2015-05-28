package com.ufufund.ufb.model.db;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ufufund.ufb.model.action.PrintableModel;

public class Fdacfinalresult extends PrintableModel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  serialno;//varchar(24) not null comment '流水号',
	private String  custno;//char(10) not null comment '客户编号',
	private String  apkind;//char(3) not null comment '业务代码',
	private String  workdate;// char(8) not null comment '工作日',
	private String  apdt;//char(8) not null comment '申请日期',
	private String  aptm;// char(6) not null comment '申请时间',
	private String  status="Y";// char(1) not null comment '状态',
	//private String  updatetimestamp;//timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
	
	
//	private String  toTradeaccoid;// varchar(24) not null comment '交易账号',
//	private String  toBankserialid;//varchar(24) not null comment '银行账号',
//	private String  tofundCode;//varchar(24) not null comment '银行账号',
//	private String  fromTradeaccoid;// varchar(24) not null comment '交易账号',
//	private String  fromBankserialid;//varchar(24) not null comment '银行账号',
//	private String  fromFundCode;//varchar(24) not null comment '银行账号',
	
	private String  frombankserialid;// varchar(24) default null comment '银行卡id',
	private String  fromaccoid;// int(11) default null comment '交易账号编号',
	private String  fromtradeacco;// varchar(17) default null comment '交易账号',
	private String  fromfundcode;// varchar(6) default null comment '基金代码',
	private String  fromfundcorpno;// varchar(24) default null comment '归属基金公司',
	private String  fromchargetype;// varchar(1) default null comment 'A：前收费 B：后收费',
	private String  tobankserialid;// varchar(24) default null,
	private String  toaccoid;// int(11) default null,
	private String  totradeacco;// varchar(17) default null,
	private String  tofundcode;// varchar(6) default null comment '基金代码',
	private String  tofundcorpno;// varchar(24) default null,
	private String  tochargetype;// varchar(1) default null,
	
	private BigDecimal appamt;//` decimal(16,2) default null comment '申请金额',
	private BigDecimal appvol;//` decimal(16,2) default null comment '申请份额',
	private BigDecimal ackamt;//` decimal(16,2) default null comment '确认金额',
	private BigDecimal ackvol;//` decimal(16,2) default null comment '确认份额',
//	private String  fundcorpno;
	
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	
	public String getApkind() {
		return apkind;
	}
	public void setApkind(String apkind) {
		this.apkind = apkind;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public String getApdt() {
		return apdt;
	}
	public void setApdt(String apdt) {
		this.apdt = apdt;
	}
	public String getAptm() {
		return aptm;
	}
	public void setAptm(String aptm) {
		this.aptm = aptm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getAppamt() {
		return appamt;
	}
	public void setAppamt(BigDecimal appamt) {
		this.appamt = appamt;
	}
	public BigDecimal getAppvol() {
		return appvol;
	}
	public void setAppvol(BigDecimal appvol) {
		this.appvol = appvol;
	}
	public BigDecimal getAckamt() {
		return ackamt;
	}
	public void setAckamt(BigDecimal ackamt) {
		this.ackamt = ackamt;
	}
	public BigDecimal getAckvol() {
		return ackvol;
	}
	public void setAckvol(BigDecimal ackvol) {
		this.ackvol = ackvol;
	}
//	public String getFundcorpno() {
//		return fundcorpno;
//	}
//	public void setFundcorpno(String fundcorpno) {
//		this.fundcorpno = fundcorpno;
//	}
	public String getFrombankserialid() {
		return frombankserialid;
	}
	public void setFrombankserialid(String frombankserialid) {
		this.frombankserialid = frombankserialid;
	}
	public String getFromaccoid() {
		return fromaccoid;
	}
	public void setFromaccoid(String fromaccoid) {
		this.fromaccoid = fromaccoid;
	}
	public String getFromtradeacco() {
		return fromtradeacco;
	}
	public void setFromtradeacco(String fromtradeacco) {
		this.fromtradeacco = fromtradeacco;
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
	public String getToaccoid() {
		return toaccoid;
	}
	public void setToaccoid(String toaccoid) {
		this.toaccoid = toaccoid;
	}
	public String getTotradeacco() {
		return totradeacco;
	}
	public void setTotradeacco(String totradeacco) {
		this.totradeacco = totradeacco;
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
	



}
