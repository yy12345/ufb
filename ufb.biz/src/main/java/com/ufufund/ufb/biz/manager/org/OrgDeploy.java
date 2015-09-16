package com.ufufund.ufb.biz.manager.org;

import java.util.List;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.action.org.SaveOrgGradeAction;
import com.ufufund.ufb.model.action.org.CreateOrgchargeinfoAction;
import com.ufufund.ufb.model.action.org.SavetermAction;
import com.ufufund.ufb.model.action.org.UpdateOrgchargeinfoAction;
import com.ufufund.ufb.model.db.Orgchargeinfo;
import com.ufufund.ufb.model.db.Orggrade;

public interface OrgDeploy {

	/*
	 * 获取当前的学年，学期信息,
	 * 如果没有配置 ，会返回1个空当前年份的学年对象。
	 */
	public Orggrade getOrgGradeInfo(String orgId) throws BizException;

	/*
	 * 保存修改学年 
	 */
	public void saveOrgGrade(SaveOrgGradeAction action) throws BizException;

	/*
	 * 保存第一学期
	 */
	public void saveterm1(SavetermAction action) throws BizException;

	/*
	 * 保存第二学期
	 */
	public void saveterm2(SavetermAction action) throws BizException;

	/*
	 * 开始学期
	 */
	public void startTerm(String orgId) throws BizException;

	/*
	 * 结束学期
	 */
	public void closeTerm(String orgId) throws BizException;

	/*
	 * 获取费用明细
	 */
	public List<Orgchargeinfo> getOrgchargeinfo(String orgId) throws BizException;

	/*
	 * 新增费用明细
	 */
	public void createOrgchargeinfo(CreateOrgchargeinfoAction action) throws BizException;

	/*
	 * 修改费用明细
	 */
	public void updateOrgchargeinfo(UpdateOrgchargeinfoAction action) throws BizException;
}
