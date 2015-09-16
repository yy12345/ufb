package com.ufufund.ufb.model.action.org;

import com.ufufund.ufb.model.action.CommonAction;

public class CreateOrgchargeinfoAction extends CommonAction {
	
	private String orgid;//	char	24	机构ID
	private String chargetype;//	char	2	计费类型   用字典表
	private String chargename;//	varchar	30	名称
	private String chargeamount;//	number	16,2	默认金额
	private String cycle;//	char	1	计费周期    Y- 学年 T- 学期  M-每月
	private String createno;//	char	24	创建人 客户号	
	
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
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
	public String getCreateno() {
		return createno;
	}
	public void setCreateno(String createno) {
		this.createno = createno;
	}

	
	
}
