package com.ufufund.ufb.model.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PayListVo {

	private OrgBankInfoVo orginfo;       //某机构信息集合
	private List<QueryOrgStudent> studentList;   //某机构学生信息集合
	private List<QueryCustplandetail> planList;  //当月某机构缴费计划
	private int plancount;                       //当月某机构总的缴费计划数
	private BigDecimal monthtotalamt;            //当月某机构缴费金额
	private BigDecimal monthbackamt;             //上月某机构退费金额
	
}
