package com.ufufund.ufb.model.action.org;

import com.ufufund.ufb.model.action.CommonAction;

public class SaveOrgGradeAction extends CommonAction {

	private String orgid;// char 24 机构ID
	
	private String gradename;// varchar 学年名 -不填默认为学年年份 YYYY
	private String startdate;// char 8 开始日期
	private String enddate;// char 8 结束日期

	
	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}
