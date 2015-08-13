package com.ufufund.ufb.biz.manager.org.impl.validator;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.model.action.org.SaveOrgGradeAction;
import com.ufufund.ufb.model.action.org.CreateOrgchargeinfoAction;
import com.ufufund.ufb.model.action.org.SavetermAction;
import com.ufufund.ufb.model.action.org.UpdateOrgchargeinfoAction;
import com.ufufund.ufb.model.enums.ErrorInfo;

@Service
public class OrgDeployValidator {

	public void validator(SaveOrgGradeAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getOrgid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
		}
		if (RegexUtil.isNull(action.getGradename())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.GRADE_NAME);
		}
		if (RegexUtil.isNull(action.getStartdate())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.START_DATE);
		}
		if (RegexUtil.isNull(action.getEnddate())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.END_DATE);
		}
	}

	public void validator(SavetermAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getOrgid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
		}
		if (RegexUtil.isNull(action.getStartdate())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.START_DATE);
		}
		if (RegexUtil.isNull(action.getEnddate())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.END_DATE);
		}
		
	}
	
	public void validator(CreateOrgchargeinfoAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getOrgid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
		}
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
		if (RegexUtil.isNull(action.getCreateno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CREATE_NO);
		}
		if (!RegexUtil.isAmt(action.getChargeamount())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Orggrade.CHARGE_AMOUNT);
		}
	}
	
	public void validator(UpdateOrgchargeinfoAction action) throws BizException {
		String processId = action.getProcessId();
		if (RegexUtil.isNull(action.getOrgid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.ORGID);
		}
		if (RegexUtil.isNull(action.getChargeid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CHARGE_ID);
		}
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
		if (RegexUtil.isNull(action.getCreateno())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.CREATE_NO);
		}
		if (!RegexUtil.isAmt(action.getChargeamount())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Orggrade.CHARGE_AMOUNT);
		}
	}
}
