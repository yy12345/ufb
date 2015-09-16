package com.ufufund.ufb.biz.manager.org;

import java.util.List;

import com.ufufund.ufb.model.action.org.QueryOrggplan;



public interface OrgQueryManager { 
	
	public List<QueryOrggplan> getQueryOrggplan(String orgid);
	//public List<Orgchargeinfo> getOrgchargeinfo(String orgId) throws BizException;
}
