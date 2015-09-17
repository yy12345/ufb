package com.ufufund.ufb.biz.manager.org.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.impl.ImplCommon;
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.dao.OrgQueryMapper;
import com.ufufund.ufb.model.vo.QueryCustPayInfo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgPayInfo;
import com.ufufund.ufb.model.vo.QueryOrgplan;
import com.ufufund.ufb.model.vo.QueryOrgplandetail;
import com.ufufund.ufb.model.vo.QueryOrgplandetailcharge;


@Service
public class OrgQueryManagerImpl extends ImplCommon implements OrgQueryManager {

	@Autowired
	private OrgQueryMapper orgQueryMapper;
	
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
	public List<QueryCustplandetail> getQueryCustplandetail(String custno) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryCustplandetail(custno, "ALL");
	}

	
}
