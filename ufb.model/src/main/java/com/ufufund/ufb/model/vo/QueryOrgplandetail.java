package com.ufufund.ufb.model.vo;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class QueryOrgplandetail extends PrintableModel implements Serializable {

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
	private String ispay;// char(1) DEFAULT 'F' COMMENT '是否缴费 Y 已缴费 F 未缴费',
	private String paycustno;//` char(24) DEFAULT NULL COMMENT '缴费客户号',
	private String paydate;//` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP CO
	
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

	public String getIspay() {
		return ispay;
	}

	public void setIspay(String ispay) {
		this.ispay = ispay;
	}

	public String getPaycustno() {
		return paycustno;
	}

	public void setPaycustno(String paycustno) {
		this.paycustno = paycustno;
	}

	public String getPaydate() {
		return paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	
	
}
