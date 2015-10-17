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
	
	private String paycustno;// char(24) DEFAULT NULL COMMENT '缴费客户号',
	//private String paydate;// timestamp NULL DEFAULT NULL COMMENT '缴费日期',
	//private String plandate;// timestamp NULL DEFAULT NULL,
	private String acktype;// char(1) CHARACTER SET utf8mb4 DEFAULT 'U' COMMENT '扣款方式 U-幼富宝  B-银行卡',
	private String ackcustno;// char(24) DEFAULT NULL COMMENT '缴费客户号',
	private String ackbankcardid;// char(24) DEFAULT NULL COMMENT '缴费银行卡id',
	private String acktradeaccoid;// char(24) DEFAULT NULL COMMENT '缴费交易帐号id',
	private String acktradeacco;// char(17) DEFAULT NULL COMMENT '缴费交易帐号',
	//private String ackdate;// timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '确认日',
	private String redeemstats;// char(1) DEFAULT NULL COMMENT '幼富宝赎回状态',
	private String redeemdate;// timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '幼富宝赎回日'
	
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

	public String getPaycustno() {
		return paycustno;
	}

	public void setPaycustno(String paycustno) {
		this.paycustno = paycustno;
	}

	public String getAcktype() {
		return acktype;
	}

	public void setAcktype(String acktype) {
		this.acktype = acktype;
	}

	public String getAckcustno() {
		return ackcustno;
	}

	public void setAckcustno(String ackcustno) {
		this.ackcustno = ackcustno;
	}

	public String getAckbankcardid() {
		return ackbankcardid;
	}

	public void setAckbankcardid(String ackbankcardid) {
		this.ackbankcardid = ackbankcardid;
	}

	public String getAcktradeaccoid() {
		return acktradeaccoid;
	}

	public void setAcktradeaccoid(String acktradeaccoid) {
		this.acktradeaccoid = acktradeaccoid;
	}

	public String getAcktradeacco() {
		return acktradeacco;
	}

	public void setAcktradeacco(String acktradeacco) {
		this.acktradeacco = acktradeacco;
	}

	public String getRedeemstats() {
		return redeemstats;
	}

	public void setRedeemstats(String redeemstats) {
		this.redeemstats = redeemstats;
	}

	public String getRedeemdate() {
		return redeemdate;
	}

	public void setRedeemdate(String redeemdate) {
		this.redeemdate = redeemdate;
	}

	
	
}
