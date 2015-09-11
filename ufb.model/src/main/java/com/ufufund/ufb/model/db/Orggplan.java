package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class Orggplan extends PrintableModel implements Serializable {

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
	private String planname;// varchar(50) NOT NULL COMMENT '计划名',
	private String plantype;// char(1) DEFAULT NULL COMMENT '费用类型 F 固定、T 临时、R 退费',
	private String cycletype;// char(1) DEFAULT NULL COMMENT '类型 S单次，E多次',
	private String type;// char(2) DEFAULT NULL COMMENT '其他类型 AT-月代扣按学期 AW-月代扣按学年 ',
	private String ackdat;// char(2) DEFAULT NULL COMMENT '确认日',
	private String dat;// char(2) DEFAULT NULL COMMENT '扣款日',
	private String lastdate;// varchar(8) DEFAULT NULL COMMENT '上一扣款日',
	private String nextdate;// varchar(8) DEFAULT NULL COMMENT '下一扣款日',
	private String stats;// char(1) DEFAULT NULL,
	private String replanid;// varchar(24) DEFAULT NULL COMMENT '退费原计划号',
	private String remark1;// varchar(100) DEFAULT NULL,
	private String remark2;// varchar(100) DEFAULT NULL,
	private String createno;// char(24) DEFAULT NULL,
	// private String createtime;//timestamp NULL DEFAULT NULL ON UPDATE CURRENTTIMESTAMP,
	private String updateno;// char(24) DEFAULT NULL,

	// private String updatetime;//timestamp NULL DEFAULT NULL ON UPDATE CURRENTTIMESTAMP
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getGradeid() {
		return gradeid;
	}

	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}

	public String getTermid() {
		return termid;
	}

	public void setTermid(String termid) {
		this.termid = termid;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public String getPlantype() {
		return plantype;
	}

	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	public String getCycletype() {
		return cycletype;
	}

	public void setCycletype(String cycletype) {
		this.cycletype = cycletype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAckdat() {
		return ackdat;
	}

	public void setAckdat(String ackdat) {
		this.ackdat = ackdat;
	}

	public String getDat() {
		return dat;
	}

	public void setDat(String dat) {
		this.dat = dat;
	}

	public String getLastdate() {
		return lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	public String getNextdate() {
		return nextdate;
	}

	public void setNextdate(String nextdate) {
		this.nextdate = nextdate;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	public String getReplanid() {
		return replanid;
	}

	public void setReplanid(String replanid) {
		this.replanid = replanid;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getCreateno() {
		return createno;
	}

	public void setCreateno(String createno) {
		this.createno = createno;
	}

	public String getUpdateno() {
		return updateno;
	}

	public void setUpdateno(String updateno) {
		this.updateno = updateno;
	}

}
