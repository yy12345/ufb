package com.ufufund.ufb.model.db;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PayList {

	private OrgQuery orginfo;       //某机构信息集合
	private List<OrgQuery> studentList;   //某机构学生信息集合
	private List<OrgPlanDetail> planList;  //当月某机构缴费计划
	private int plancount;                       //当月某机构总的缴费计划数
	private BigDecimal monthtotalamt;            //当月某机构缴费金额
	private BigDecimal monthbackamt;             //上月某机构退费金额
	
}
