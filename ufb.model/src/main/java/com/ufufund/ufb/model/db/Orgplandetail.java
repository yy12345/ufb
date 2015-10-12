package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class Orgplandetail extends PrintableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orgid;// char(24) NOT NULL COMMENT '机构ID',
	private String detailid;// char(24) DEFAULT NULL,
	private String planid;// char(24) DEFAULT NULL COMMENT '计划ID',
	private String studentid;// char(24) DEFAULT NULL COMMENT '学生ID',
	private String payappamount="0";// decimal(16,2) DEFAULT NULL COMMENT '应缴金额',
	private String paydiscount="0";// decimal(16,2) DEFAULT NULL COMMENT '折扣金额',
	private String payackamount="0";// decimal(16,2) DEFAULT NULL COMMENT '实缴金额'
	private String stats;// char(1) DEFAULT NULL,
	
	
	public String getDetailid() {
		return detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getPayappamount() {
		return payappamount;
	}

	public void setPayappamount(String payappamount) {
		this.payappamount = payappamount;
	}

	public String getPaydiscount() {
		return paydiscount;
	}

	public void setPaydiscount(String paydiscount) {
		this.paydiscount = paydiscount;
	}

	public String getPayackamount() {
		return payackamount;
	}

	public void setPayackamount(String payackamount) {
		this.payackamount = payackamount;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

}
