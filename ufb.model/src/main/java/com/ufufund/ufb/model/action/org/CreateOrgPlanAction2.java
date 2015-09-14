package com.ufufund.ufb.model.action.org;

import java.util.ArrayList;
import java.util.List;

import com.ufufund.ufb.model.action.CommonAction;

public class CreateOrgPlanAction2 extends CommonAction {

	private String studentid;// char(24) DEFAULT NULL COMMENT '学生ID',
	private String discount = "0";// 折扣金额
	private List<CreateOrgPlanAction3> chargeList = new ArrayList<CreateOrgPlanAction3>();

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public List<CreateOrgPlanAction3> getChargeList() {
		return chargeList;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

}
