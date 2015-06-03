package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class Jobcontral extends PrintableModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jobname; //` varchar(20) not null comment '任务名',
	private String jobstatus;//` varchar(2) not null comment '任务状态',
	private String starttime;//` varchar(14) default null comment '起始时间',
	private String endtime;//` varchar(14) default null comment '结束时间',
	private String workdate;//` varchar(8) default null comment '工作日'
	
	
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getJobstatus() {
		return jobstatus;
	}
	public void setJobstatus(String jobstatus) {
		this.jobstatus = jobstatus;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	
	
	
}
