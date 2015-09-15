package com.ufufund.ufb.model.action.org;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class QueryOrggplan extends PrintableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String orgid;// char(24) NOT NULL COMMENT '机构ID',
	private String planid;// char(24) NOT NULL COMMENT '计划ID',
	private String gradeid;// char(24) NOT NULL COMMENT '学年ID',
	private String termid;// varchar(25) DEFAULT NULL COMMENT '学期ID',
	private String groupid; //varchar(25) DEFAULT NULL COMMENT '学期ID',
	
	private String planname;// varchar(50) NOT NULL COMMENT '计划名',
	private String plantype;// char(1) DEFAULT NULL COMMENT '费用类型 F 固定、T 临时、R 退费',
	private String cycletype;// char(1) DEFAULT NULL COMMENT '类型 S单次，E多次',
	private String type;// char(2) DEFAULT NULL COMMENT '其他类型 AT-月代扣按学期 AW-月代扣按学年 ',
	private String ackdat;// char(2) DEFAULT NULL COMMENT '确认日',
	private String dat;// char(2) DEFAULT NULL COMMENT '扣款日',
	private String paydate;// varchar(8) DEFAULT NULL COMMENT '下一扣款日',
	private String stats;// char(1) DEFAULT NULL,
	private String replanid;// varchar(24) DEFAULT NULL COMMENT '退费原计划号',
	private String remark1;// varchar(100) DEFAULT NULL,
	
	private String remark2;// varchar(100) DEFAULT NULL,
	private String createno;// char(24) DEFAULT NULL,
	// private String createtime;//timestamp NULL DEFAULT NULL ON UPDATE CURRENTTIMESTAMP,
	private String totalappamount;// 总应缴金额
	private String totaldiscount;//总折扣金额
	private String totalackamount;// 总应缴实际金额
	private String allnum;//  总应缴人数
	private String yespaynum;// 已缴人数
	private String nopaynum;// 未缴人数
	private String yespayamount;// 实际已缴总金额
	private String nopayamount;//  实际待缴总金额
	
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public void setPlanname(String planname) {
		this.planname = planname;
	}
	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}
	public void setCycletype(String cycletype) {
		this.cycletype = cycletype;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setAckdat(String ackdat) {
		this.ackdat = ackdat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
//	public void setLastdate(String lastdate) {
//		this.lastdate = lastdate;
//	}
//	public void setNextdate(String nextdate) {
//		this.nextdate = nextdate;
//	}
	public void setStats(String stats) {
		this.stats = stats;
	}
	public void setReplanid(String replanid) {
		this.replanid = replanid;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public void setCreateno(String createno) {
		this.createno = createno;
	}

	public String getOrgid() {
		return orgid;
	}
	public String getPlanid() {
		return planid;
	}
	public String getGradeid() {
		return gradeid;
	}
	public String getTermid() {
		return termid;
	}
	public String getPlanname() {
		return planname;
	}
	public String getPlantype() {
		return plantype;
	}
	public String getCycletype() {
		return cycletype;
	}
	public String getType() {
		return type;
	}
	public String getAckdat() {
		return ackdat;
	}
	public String getDat() {
		return dat;
	}
//	public String getLastdate() {
//		return lastdate;
//	}
//	public String getNextdate() {
//		return nextdate;
//	}
	public String getStats() {
		return stats;
	}
	public String getReplanid() {
		return replanid;
	}
	public String getRemark1() {
		return remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public String getCreateno() {
		return createno;
	}
	
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getTotalappamount() {
		return totalappamount;
	}
	public void setTotalappamount(String totalappamount) {
		this.totalappamount = totalappamount;
	}
	public String getTotaldiscount() {
		return totaldiscount;
	}
	public void setTotaldiscount(String totaldiscount) {
		this.totaldiscount = totaldiscount;
	}
	public String getTotalackamount() {
		return totalackamount;
	}
	public void setTotalackamount(String totalackamount) {
		this.totalackamount = totalackamount;
	}
	public String getAllnum() {
		return allnum;
	}
	public void setAllnum(String allnum) {
		this.allnum = allnum;
	}
	public String getYespaynum() {
		return yespaynum;
	}
	public void setYespaynum(String yespaynum) {
		this.yespaynum = yespaynum;
	}
	public String getNopaynum() {
		return nopaynum;
	}
	public void setNopaynum(String nopaynum) {
		this.nopaynum = nopaynum;
	}
	public String getYespayamount() {
		return yespayamount;
	}
	public void setYespayamount(String yespayamount) {
		this.yespayamount = yespayamount;
	}
	public String getNopayamount() {
		return nopayamount;
	}
	public void setNopayamount(String nopayamount) {
		this.nopayamount = nopayamount;
	}


}
