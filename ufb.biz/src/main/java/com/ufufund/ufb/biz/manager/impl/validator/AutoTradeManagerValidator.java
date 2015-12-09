package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.stereotype.Service;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.action.cust.ChangeAutoStateAction;
import com.ufufund.ufb.model.action.cust.ModifyAutotradeAction;
import com.ufufund.ufb.model.enums.AutoTradeType;

@Service
public class AutoTradeManagerValidator {

	public void validator(AddAutotradeAction action){
		if (RegexUtil.isNull(action.getCustno())||RegexUtil.isNull(action.getTradetype().value())||RegexUtil.isNull(action.getType())){
			throw new UserException("系统异常！");
		}
		if (action.getTradetype().equals(AutoTradeType.AUTORECHARGE)) {
			if (RegexUtil.isNull(action.getCycle())||RegexUtil.isNull(action.getDat())||RegexUtil.isNull(action.getFrombankserialid())
					||RegexUtil.isNull(action.getTofundcode())||RegexUtil.isNull(action.getTofundcorpno())||RegexUtil.isNull(action.getTochargetype())||
					!RegexUtil.isAmt(action.getAutoamt())){
				throw new UserException("系统异常！");
			}

		} else if (action.getTradetype().equals(AutoTradeType.AUTOWITHDRAWAL)) {
			if (RegexUtil.isNull(action.getFromfundcode())||RegexUtil.isNull(action.getFromfundcorpno())
					||RegexUtil.isNull(action.getFromchargetype())||!RegexUtil.isAmt(action.getAutovol())) {
				throw new UserException("系统异常！");
			}
		}
	}

	public void validatorModify(ModifyAutotradeAction action){
		if (RegexUtil.isNull(action.getAutoid())) {
			throw new UserException("系统异常！");
		}
	}
	
	public void validator(ChangeAutoStateAction action){
		if (RegexUtil.isNull(action.getAutoid())||RegexUtil.isNull(action.getState())) {
			throw new UserException("系统异常！");
		}
	}

}
