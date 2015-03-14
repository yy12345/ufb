package com.ufufund.ufb.model.model;

public class DateInfo {

	private String  workdate;// char(8) not null comment '工作日',
	private String  apdt;//char(8) not null comment '申请日期',
	private String  aptm;// char(6) not null comment '申请时间',
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
	
	
}
