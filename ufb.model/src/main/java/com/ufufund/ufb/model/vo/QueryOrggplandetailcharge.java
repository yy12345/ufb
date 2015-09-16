package com.ufufund.ufb.model.vo;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class QueryOrggplandetailcharge extends PrintableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orgid;// char(24) NOT NULL COMMENT '机构ID',
	private String planid;//	char	24	主键
	private String detailid;//	char	24	主键
	private String chargeid;//	char	24	主键
	private String chargetype;//	char	2	计费类型
	private String chargename;//	varchar	30	名称
	private String chargeamount;//	number	16,2	默认金额
	private String cycle;//	char	1	计费周期 学年/学期/
	
	public String getChargeid() {
		return chargeid;
	}
	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}
	public String getChargetype() {
		return chargetype;
	}
	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}
	public String getChargename() {
		return chargename;
	}
	public void setChargename(String chargename) {
		this.chargename = chargename;
	}
	public String getChargeamount() {
		return chargeamount;
	}
	public void setChargeamount(String chargeamount) {
		this.chargeamount = chargeamount;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
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
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}
