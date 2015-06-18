package com.ufufund.ufb.model.db;

import java.io.Serializable;

public class FundNav implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fundcorpno; // 基金公司编号',
	private String fundcode; // 基金编码',
	private String fundname; // 基金名称',
	private String date; // 基金净值日',
	private String nav; // 基金单位净值：四位小数',
	private String navadditive; // 累计基金单位净值',
	private String income; // 万份收益：5位小数',
	private String yield; // 7日年化收益率：5位小数',

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

	@Override
	public String toString() {
		return "FundInfo [fundcorpno=" + fundcorpno + ", fundcode=" + fundcode
				+ ", fundname=" + fundname + ", date=" + date + ", nav=" + nav
				+ ", navadditive=" + navadditive + ", income=" + income
				+ ", yield=" + yield + "]";
	}

}
