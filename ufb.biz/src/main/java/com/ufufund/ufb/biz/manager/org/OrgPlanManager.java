package com.ufufund.ufb.biz.manager.org;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction1;


public interface OrgPlanManager { 
	
	
	/*
	 * 发起收费计划
	 */
	public void createOrgPlanAction1(CreateOrgPlanAction1 action) throws BizException;
	
	/*
	 * 保存收费计划
	 */
	public void createOrgPlanAction2(CreateOrgPlanAction1 action) throws BizException;
	
	
}
