package com.ufufund.ufb.biz.manager.org.impl.validator;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction1;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction3;
import com.ufufund.ufb.model.enums.ErrorInfo;

@Service
public class OrgPlanValidator {

	public void validator(CreateOrgPlanAction1 action) throws BizException {
		String processId = action.getProcessId();

		if (RegexUtil.isNull(action.getOrgid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
		}
//		if (RegexUtil.isNull(action.getPlanid())) {
//			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
//		}
		if (RegexUtil.isNull(action.getGradeid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.GRADE_ID);
		}
		if (RegexUtil.isNull(action.getTermid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.TERM_ID);
		}
		if (RegexUtil.isNull(action.getPlanname())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.PLAN_NAME);
		}

		if (RegexUtil.isNull(action.getPlantype())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.PLAN_TYPE);
		}
		if (RegexUtil.isNull(action.getCycletype())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CYCLE_TYPE);
		}
//		if (RegexUtil.isNull(action.getType())) {
//			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.TYPE);
//		}
		if (RegexUtil.isNull(action.getAckdat())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ACKDAT);
		}
		if (RegexUtil.isNull(action.getPaydate())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.PAY_DATE);
		}
//		if (RegexUtil.isInteger(action.getDat())) {
//			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Orggrade.PAY_DATE);
//		}
		if ("R".equals(action.getPlantype())) {
			if (RegexUtil.isNull(action.getReplanid())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.REPLAN_ID);
			}
		}

		if (RegexUtil.isNull(action.getCreateno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CREATE_NO);
		}
	}
	
	
	public void validator(CreateOrgPlanAction3 action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getChargetype())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CHARGE_TYPE);
		}
		if (RegexUtil.isNull(action.getChargename())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CHARGE_NAME);
		}
		if (RegexUtil.isNull(action.getChargeamount())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CHARGE_AMOUNT);
		}
		if (RegexUtil.isNull(action.getCycle())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CYCLE);
		}
		if (!RegexUtil.isAmt(action.getChargeamount())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Orggrade.CHARGE_AMOUNT);
		}
	}
}
