package com.ufufund.ufb.biz.manager.org.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.SequenceManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.ImplCommon;
import com.ufufund.ufb.biz.manager.org.OrgDeploy;
import com.ufufund.ufb.biz.manager.org.impl.helper.OrgDeployHelper;
import com.ufufund.ufb.biz.manager.org.impl.validator.OrgDeployValidator;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.dao.OrgDeployMapper;
import com.ufufund.ufb.model.action.org.CreateOrgchargeinfoAction;
import com.ufufund.ufb.model.action.org.SaveOrgGradeAction;
import com.ufufund.ufb.model.action.org.SavetermAction;
import com.ufufund.ufb.model.action.org.UpdateOrgchargeinfoAction;
import com.ufufund.ufb.model.db.Orgchargeinfo;
import com.ufufund.ufb.model.db.Orggrade;
import com.ufufund.ufb.model.enums.ErrorInfo;

@Service
public class OrgDeployImpl extends ImplCommon implements OrgDeploy {

	@Autowired
	private OrgDeployMapper orgDeployMapper;

	@Autowired
	private OrgDeployValidator orgDeployValidator;

	// @Autowired
	// private OrgDeployHelper orgDeployHelper;

	@Autowired
	private SequenceManager sequenceManager;

	@Autowired
	private WorkDayManager workDayManager;

	/**
	 * 获取学期学年信息
	 */
	@Override
	public Orggrade getOrgGradeInfo(String orgId) throws BizException {
		String processId = this.getProcessId(orgId);
		if (RegexUtil.isNull(orgId)) {
			// 机构ID
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
		}
		Orggrade retOrg = this.getOrgGradeOpen(orgId);
		if (retOrg == null) {
			retOrg = new Orggrade();
			retOrg.setOrgid(orgId);
			retOrg.setIsopen(Constant.Orggrade.ISOPEN$Y);
			String systime = workDayManager.getSysTime();
			String year = systime.substring(0, 4);
			retOrg.setGradename(year);
			retOrg.setStartdate(year + "0901");
			retOrg.setEnddate((Integer.parseInt(year) + 1) + "0630");
			retOrg.setT1startdate(year + "0901");
			retOrg.setT2enddate((Integer.parseInt(year) + 1) + "0630");
		}
		return retOrg;
	}

	private Orggrade getOrgGradeOpen(String orgId) {
		Orggrade org = new Orggrade();
		org.setOrgid(orgId);
		org.setIsopen(Constant.Orggrade.ISOPEN$Y);
		Orggrade retOrg = orgDeployMapper.getOrgGradeInfo(org);
		return retOrg;
	}

	@Override
	public void saveOrgGrade(SaveOrgGradeAction action) throws BizException {
		this.getProcessId(action);
		orgDeployValidator.validator(action);
		Orggrade retOrg = this.getOrgGradeOpen(action.getOrgid());
		String gradeid = "";
		if (retOrg == null) {
			gradeid = sequenceManager.getGradeid();
			retOrg = new Orggrade();
			retOrg.setOrgid(action.getOrgid());
			retOrg.setIsopen(Constant.Orggrade.ISOPEN$Y);
			retOrg.setGradeid(gradeid);
			retOrg.setTerm1id(gradeid + "A");
			retOrg.setTerm2id(gradeid + "B");
			retOrg.setT1isopen(Constant.Orggrade.ISOPEN$Y);
			retOrg.setT2isopen(Constant.Orggrade.ISOPEN$N);
		}
		retOrg.setGradename(action.getGradename());
		retOrg.setStartdate(action.getStartdate());
		retOrg.setEnddate(action.getEnddate());
		retOrg.setT1startdate(action.getStartdate());
		retOrg.setT2enddate(action.getEnddate());
		retOrg.setCreateno(action.getOrgid());
		orgDeployMapper.mergeOrgGradeInfo(retOrg);
	}

	@Override
	public void saveterm1(SavetermAction action) throws BizException {
		this.saveterm(action, "T1");
	}

	@Override
	public void saveterm2(SavetermAction action) throws BizException {
		this.saveterm(action, "T2");
	}

	private void saveterm(SavetermAction action, String type) throws BizException {
		String processId = this.getProcessId(action);
		orgDeployValidator.validator(action);
		Orggrade retOrg = this.getOrgGradeOpen(action.getOrgid());
		if (retOrg == null) {
			throw new BizException(processId, ErrorInfo.ORG_MUST_SAVE_GRADE);
		}
		if ("T1".equals(type)) {
			if (Constant.Orggrade.ISOPEN$C.equals(retOrg.getT1isopen())) {
				throw new BizException(processId, ErrorInfo.ORG_TERM_IS_CLOSE);
			}
			retOrg.setT1startdate(action.getStartdate());
			retOrg.setT1enddate(action.getEnddate());
		} else {
			if (Constant.Orggrade.ISOPEN$C.equals(retOrg.getT2isopen())) {
				throw new BizException(processId, ErrorInfo.ORG_TERM_IS_CLOSE);
			}
			retOrg.setT2startdate(action.getStartdate());
			retOrg.setT2enddate(action.getEnddate());
		}
		retOrg.setUpdateno(action.getOrgid());
		orgDeployMapper.mergeOrgGradeInfo(retOrg);

	}

	@Override
	public void startTerm(String orgId) throws BizException {
		String processId = this.getProcessId(orgId);
		if (RegexUtil.isNull(orgId)) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
		}
		Orggrade retOrg = this.getOrgGradeOpen(orgId);
		if (retOrg == null || retOrg.equals("")) {
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		if (Constant.Orggrade.ISOPEN$N.equals(retOrg.getT1isopen())) {
			retOrg.setT1isopen(Constant.Orggrade.ISOPEN$Y);
		} else if (Constant.Orggrade.ISOPEN$C.equals(retOrg.getT1isopen()) && Constant.Orggrade.ISOPEN$N.equals(retOrg.getT2isopen())) {
			retOrg.setT2isopen(Constant.Orggrade.ISOPEN$Y);
		} else {
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		retOrg.setUpdateno(orgId);
		orgDeployMapper.mergeOrgGradeInfo(retOrg);
	}

	@Override
	public void closeTerm(String orgId) throws BizException {
		String processId = this.getProcessId(orgId);
		if (RegexUtil.isNull(orgId)) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
		}
		Orggrade retOrg = this.getOrgGradeOpen(orgId);
		if (retOrg == null || retOrg.equals("")) {
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		if (Constant.Orggrade.ISOPEN$Y.equals(retOrg.getT1isopen())) {
			retOrg.setT1isopen(Constant.Orggrade.ISOPEN$C);
			retOrg.setT2isopen(Constant.Orggrade.ISOPEN$Y);
		} else if (Constant.Orggrade.ISOPEN$C.equals(retOrg.getT1isopen()) && Constant.Orggrade.ISOPEN$Y.equals(retOrg.getT2isopen())) {
			retOrg.setT2isopen(Constant.Orggrade.ISOPEN$C);
			retOrg.setIsopen(Constant.Orggrade.ISOPEN$C);
			/*
			 * 生成新的学年
			 */
			String gradeid = sequenceManager.getGradeid();
			Orggrade org = new Orggrade();
			org.setOrgid(orgId);
			org.setIsopen(Constant.Orggrade.ISOPEN$Y);
			org.setGradeid(gradeid);
			org.setTerm1id(gradeid + "A");
			org.setTerm2id(gradeid + "B");
			org.setT1isopen(Constant.Orggrade.ISOPEN$Y);
			org.setT2isopen(Constant.Orggrade.ISOPEN$N);
			if (RegexUtil.isDigits(retOrg.getGradename())) {
				org.setGradename((Integer.parseInt(retOrg.getGradename()) + 1) + "");
			}
			String systime = workDayManager.getSysTime();
			String year = systime.substring(0, 4);
			org.setStartdate(year + "0901");
			org.setEnddate((Integer.parseInt(year) + 1) + "0630");
			org.setT1startdate(year + "0901");
			org.setT2enddate((Integer.parseInt(year) + 1) + "0630");
			org.setCreateno(orgId);
			orgDeployMapper.mergeOrgGradeInfo(org);
		} else {
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		retOrg.setUpdateno(orgId);
		orgDeployMapper.mergeOrgGradeInfo(retOrg);
	}

	@Override
	public List<Orgchargeinfo> getOrgchargeinfo(String orgId) throws BizException {
		String processId = this.getProcessId(orgId);
		if (RegexUtil.isNull(orgId)) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
		}
		Orgchargeinfo org = new Orgchargeinfo();
		org.setOrgid(orgId);
		org.setIsdelete(Constant.Orggrade.ISDELETE$Y);
		List<Orgchargeinfo> list = orgDeployMapper.getOrgchargeinfo(org);
		return list;
	}

	@Override
	public void createOrgchargeinfo(CreateOrgchargeinfoAction action) throws BizException {
		String processId = this.getProcessId(action);
		orgDeployValidator.validator(action);
		Orgchargeinfo orgchargeinfo = OrgDeployHelper.converntOrgchargeinfo(action);
		if ("M".equals(action.getCycle())) {
			orgchargeinfo.setCycletype("M");
		} else {
			orgchargeinfo.setCycletype("S");
		}
		orgchargeinfo.setIsdelete("N");
		int n = orgDeployMapper.createOrgchargeinfo(orgchargeinfo);
		log.debug(processId + " 创建费用成功 " + n);
	}

	@Override
	public void updateOrgchargeinfo(UpdateOrgchargeinfoAction action) throws BizException {
		String processId = this.getProcessId(action);
		orgDeployValidator.validator(action);
		if (RegexUtil.isNull(action.getChargeid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CHARGE_ID);
		}
		Orgchargeinfo orgchargeinfo = OrgDeployHelper.converntOrgchargeinfo(action);
		if ("M".equals(action.getCycle())) {
			orgchargeinfo.setCycletype("M");
		} else {
			orgchargeinfo.setCycletype("S");
		}
		int n = orgDeployMapper.updateOrgchargeinfo(orgchargeinfo);
		log.debug(processId + " 更新费用成功 " + n);
	}

}
