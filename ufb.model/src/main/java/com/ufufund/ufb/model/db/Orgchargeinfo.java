package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class Orgchargeinfo extends PrintableModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String orgid;//	char	24	机构ID
	private String chargeid;//	char	24	主键
	private String chargetype;//	char	1	计费类型
	private String chargename;//	varchar	30	名称
	private String chargeamount;//	number	16,2	默认金额
	private String cycle;//	char	1	计费周期 学年/学期/每月
	private String cycletype;//	char	1	单次/月固定  (学年,学期-单次，月-月固定) S/M
	private String isdelete;//	char	1	是否删除
	private String createno;//	char	24	创建人 客户号
	//createtime	timestamp		创建时间
	private String updateno;//	char	24	最近修改 客户号
	//updatetime	timestamp		最近时间
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
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
	public String getCycletype() {
		return cycletype;
	}
	public void setCycletype(String cycletype) {
		this.cycletype = cycletype;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
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
