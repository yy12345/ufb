package com.ufufund.ufb.biz.manager.org;

import java.util.List;

import com.ufufund.ufb.model.vo.QueryCustPayInfo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgPayInfo;
import com.ufufund.ufb.model.vo.QueryOrgplan;
import com.ufufund.ufb.model.vo.QueryOrgplandetail;
import com.ufufund.ufb.model.vo.QueryOrgplandetailcharge;



public interface OrgQueryManager { 
	
	
	/*
	 * 收费计划管理
	 */
	public List<QueryOrgplan> getQueryOrgplan(String orgid);
	

	/*
	 * 收费计划详情
	 */
	public List<QueryOrgplandetail> getQueryOrgplandetail(String orgid,String planid);
	
	
	/*
	 * 收费计划详情费用列表
	 */
	public List<QueryOrgplandetailcharge> getQueryOrgplandetailcharge(
			String orgid,
			String planid,
			String detailid
			);

	/*
	 * 学年缴费查询
	 */
	public QueryOrgPayInfo getQueryOrgByGrade(
			String orgid,
			String gradeid
			);

	/*
	 * 学期缴费查询
	 */
	public QueryOrgPayInfo getQueryOrgByTerm(
			String orgid,
			String termid
			);
	
	/*
	 * 月缴费查询  month ：YYYYMM
	 */
	public QueryOrgPayInfo getQueryOrgByMonth(
			String orgid,
			String month
			);
	
	
	/*
	 * 个人用户缴费查询
	 */
	public QueryCustPayInfo getQueryCustPayInfo(String custno);
	
	
	/*
	 * 个人用户未缴费查询
	 */
	public QueryCustPayInfo getQueryCustNotPayInfo(String custno);
	
	
	/*
	 * 个人用户查询收费计划详情
	 */
	public List<QueryCustplandetail> getQueryCustplandetail(String custno);
}
