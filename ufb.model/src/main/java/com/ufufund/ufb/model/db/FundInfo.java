package com.ufufund.ufb.model.db;

import java.io.Serializable;

public class FundInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fundcorpno; // 基金公司编号',
	private String fundcode; // 基金编码',
	private String fundname; // 基金名称',
	private String status; // 基金状态：0-可申购赎回，1-发行，4-停止申购赎回，5-停止申购，6-停止赎回，9-基金封闭，a-基金终止\r\n
	private String fundtype; // 基金类型:01-股票型;02-债券型;03-混合型;04-货币型',
	private String fundvol; // 基金总份数',
	private String fundsize; // 基金规模',
	private String date; // 基金净值日',
	private String nav; // 基金单位净值：四位小数',
	private String navadditive; // 累计基金单位净值',
	private String income; // 万份收益：5位小数',
	private String yield; // 7日年化收益率：5位小数',
	private String iscontract; // 是否需要签订电子合同：0 不需要；1 需要',
	private String devmethod; // 分红方式：0-红利转投，1-现金分红',
	private String shareclass; // 收费方式',
	
	

	public String getFundcorpno() {
		return fundcorpno;
	}



	public void setFundcorpno(String fundcorpno) {
		this.fundcorpno = fundcorpno;
	}



	public String getFundcode() {
		return fundcode;
	}



	public void setFundcode(String fundcode) {
		this.fundcode = fundcode;
	}



	public String getFundname() {
		return fundname;
	}



	public void setFundname(String fundname) {
		this.fundname = fundname;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getFundtype() {
		return fundtype;
	}



	public void setFundtype(String fundtype) {
		this.fundtype = fundtype;
	}



	public String getFundvol() {
		return fundvol;
	}



	public void setFundvol(String fundvol) {
		this.fundvol = fundvol;
	}



	public String getFundsize() {
		return fundsize;
	}



	public void setFundsize(String fundsize) {
		this.fundsize = fundsize;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getNav() {
		return nav;
	}



	public void setNav(String nav) {
		this.nav = nav;
	}



	public String getNavadditive() {
		return navadditive;
	}



	public void setNavadditive(String navadditive) {
		this.navadditive = navadditive;
	}



	public String getIncome() {
		return income;
	}



	public void setIncome(String income) {
		this.income = income;
	}



	public String getYield() {
		return yield;
	}



	public void setYield(String yield) {
		this.yield = yield;
	}



	public String getIscontract() {
		return iscontract;
	}



	public void setIscontract(String iscontract) {
		this.iscontract = iscontract;
	}



	public String getDevmethod() {
		return devmethod;
	}



	public void setDevmethod(String devmethod) {
		this.devmethod = devmethod;
	}



	public String getShareclass() {
		return shareclass;
	}



	public void setShareclass(String shareclass) {
		this.shareclass = shareclass;
	}



	@Override
	public String toString() {
		return "FundInfo [" + 
				"fundcorpno = " + fundcorpno + 
				"fundcode = " + fundcode + 
				"fundname = " + fundname + 
				"status = " + status + 
				"fundtype = " + fundtype + 
				"fundvol = " + fundvol + 
				"fundsize = " + fundsize + 
				"date = " + date + 
				"nav = " + nav + 
				"navadditive = " + navadditive + 
				"income = " + income + 
				"yield = " + yield + 
				"iscontract = " + iscontract + 
				"devmethod = " + devmethod + 
				"shareclass = " + shareclass + 
				"]";
	}

}
