package com.ufufund.ufb.model.action.org;

public class UpdateOrgPlanAction1 extends CreateOrgPlanAction1 {

	/*
	 * 修改必填 
	 */
	private String planid; // char(24) NOT NULL COMMENT '计划ID', //修改的时候传入该ID

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

}
