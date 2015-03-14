package com.ufufund.ufb.model.db;

public class Fdacfinalresult {
	private String  serialno;//varchar(24) not null comment '流水号',
	private String  custno;//char(10) not null comment '客户编号',
	private String  tradeaccoid;// varchar(24) not null comment '交易账号',
	private String  bankserialid;//varchar(24) not null comment '银行账号',
	private String  apkind;//char(3) not null comment '业务代码',
	private String  workdate;// char(8) not null comment '工作日',
	private String  apdt;//char(8) not null comment '申请日期',
	private String  aptm;// char(6) not null comment '申请时间',
	private String  status;// char(1) not null comment '状态',
	//private String  updatetimestamp;//timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
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
	public String getTradeaccoid() {
		return tradeaccoid;
	}
	public void setTradeaccoid(String tradeaccoid) {
		this.tradeaccoid = tradeaccoid;
	}
	public String getBankserialid() {
		return bankserialid;
	}
	public void setBankserialid(String bankserialid) {
		this.bankserialid = bankserialid;
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



}
