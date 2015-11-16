package com.ufufund.ufb.biz.manager.org;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction1;
import com.ufufund.ufb.model.action.org.PersonConfirmAction;
import com.ufufund.ufb.model.action.org.UpdateOrgPlanAction1;
import com.ufufund.ufb.model.db.Custinfo;


public interface OrgPlanManager { 
	
	
	/*
	 * 发起收费计划第一步校验
	 */
	public void createOrgPlanAction1(CreateOrgPlanAction1 action) throws BizException;
	
	/*
	 * 保存新建收费计划
	 */
	public void createOrgPlanAction2(CreateOrgPlanAction1 action) throws BizException;
	
	/*
	 * 修改收费计划
	 */
	public void updateOrgPlanAction2(UpdateOrgPlanAction1 action) throws BizException;
	
	/*
	 * 
	 * 家庭确认发起收费
	 */
	public void personConfirmPlandetail(PersonConfirmAction action) throws BizException;
	
	public String confirmDetail(String detailids,Custinfo d_custinfo,String paytype);
}
