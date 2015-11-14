package com.ufufund.ufb.biz.manager.org.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.impl.ImplCommon;
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.dao.OrgQueryMapper;
import com.ufufund.ufb.model.vo.PayRecordQryVo;
import com.ufufund.ufb.model.vo.QueryCustPayInfo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgPayInfo;
import com.ufufund.ufb.model.vo.QueryOrgStudent;
import com.ufufund.ufb.model.vo.QueryOrgplan;
import com.ufufund.ufb.model.vo.QueryOrgplandetail;
import com.ufufund.ufb.model.vo.QueryOrgplandetailcharge;


@Service
public class OrgQueryManagerImpl extends ImplCommon implements OrgQueryManager {

	@Autowired
	private OrgQueryMapper orgQueryMapper;
	
	/*
	 * 
	 */
	public List<QueryOrgStudent> getQueryOrgByCustno(String custno){
		return orgQueryMapper.getQueryOrgByCustno(custno);
	}
	
	/*
	 * 
	 */
	public List<QueryOrgStudent> getQueryStudentByCustno(String custno){
		return null;
	}
	
	/*
	 * 
	 */
	public List<QueryOrgStudent> getQueryStudentByOrgid(String orgid, String custno){
		return orgQueryMapper.getQueryStudentByOrgid(orgid, custno);
	}
	
	@Override
	public List<QueryOrgplan> getQueryOrgplan(String orgid) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryOrgplan(orgid);
	}

	@Override
	public List<QueryOrgplandetail> getQueryOrgplandetail(String orgid, String planid) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryOrgplandetail(orgid, planid);
	}

	@Override
	public List<QueryOrgplandetailcharge> getQueryOrgplandetailcharge(String orgid, String planid, String detailid) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryOrgplandetailcharge(orgid, planid, detailid);
	}

	@Override
	public QueryOrgPayInfo getQueryOrgByGrade(String orgid, String gradeid) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryOrgPayInfo(orgid, gradeid, "GRADE");
	}

	@Override
	public QueryOrgPayInfo getQueryOrgByTerm(String orgid, String termid) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryOrgPayInfo(orgid, termid, "TERM");
	}

	@Override
	public QueryOrgPayInfo getQueryOrgByMonth(String orgid, String month) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryOrgPayInfo(orgid, month, "MONTH");
	}

	@Override
	public QueryCustPayInfo getQueryCustPayInfo(String custno) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryCustPayInfo(custno, "ALL");
	}

	@Override
	public QueryCustPayInfo getQueryCustNotPayInfo(String custno) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryCustPayInfo(custno, "NOPAY");
	}

	@Override
	public String getOrgidByDetailid(String detailid) {
		return orgQueryMapper.getOrgidByDetailid(detailid);
	}
	
	@Override
	public List<QueryCustplandetail> getQueryCustplandetail(String custno, String orgid,String detailid,List<String> ispaylist) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryCustplandetail(custno, orgid, detailid,ispaylist);
	}
	
	/**
	 * 缴费明细查询:分页查询
	 * @param vo
	 * @return
	 */
	public List<QueryCustplandetail> getPayRecords(PayRecordQryVo vo){
		return orgQueryMapper.getPayRecords(vo);
	}
	
	/**
	 * 缴费明细查询:总数
	 * @param vo
	 * @return
	 */
	public int getPayRecordCount(PayRecordQryVo vo){
		return orgQueryMapper.getPayRecordCount(vo);
	}
	
	/**
	 * 查询已缴费用总额
	 * @param custno
	 * @return
	 */
	public BigDecimal getPaidTotalAmt(PayRecordQryVo vo){
		BigDecimal result = orgQueryMapper.getPaidTotalAmt(vo);
		if(result == null) result = new BigDecimal("0.00");
		return result;
	}
	
	/**
	 * 查询已收到的退费总额
	 * @param custno
	 * @return
	 */
	public BigDecimal getReversedTotalAmt(PayRecordQryVo vo){
		BigDecimal result = orgQueryMapper.getReversedTotalAmt(vo);
		if(result == null) result = new BigDecimal("0.00");
		return result;
	}

	
}
