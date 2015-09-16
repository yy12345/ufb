package com.ufufund.ufb.model.action.org;

import com.ufufund.ufb.model.action.CommonAction;

public class CreateOrgPlanAction3 extends CommonAction {

	
	private String chargeid;//	char	24	主键
	private String chargetype;//	char	2	计费类型
	private String chargename;//	varchar	30	名称
	private String chargeamount;//	number	16,2	默认金额
	private String cycle;//	char	1	计费周期 学年/学期/每月
	
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
	  
	  
	  
}
