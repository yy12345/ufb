package com.ufufund.ufb.biz.manager.org;

import java.util.List;

import com.ufufund.ufb.model.vo.QueryOrggplan;
import com.ufufund.ufb.model.vo.QueryOrggplandetail;
import com.ufufund.ufb.model.vo.QueryOrggplandetailcharge;



public interface OrgQueryManager { 
	
	
	/*
	 * 收费计划管理
	 */
	public List<QueryOrggplan> getQueryOrggplan(String orgid);
	

	/*
	 * 收费计划详情
	 */
	public List<QueryOrggplandetail> getQueryOrggplandetail(String orgid,String planid);
	
	
	/*
	 * 收费计划详情费用列表
	 */
	public List<QueryOrggplandetailcharge> getQueryOrggplandetailcharge(
			String orgid,
			String planid,
			String detailid
			);
	//public List<Orgchargeinfo> getOrgchargeinfo(String orgId) throws BizException;
}
