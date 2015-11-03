package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.action.cust.OpenAccountAction;

public interface ChinapayManager {

	/**
	 * 银联账户验证
	 * @param openAccountAction
	 */
	void checkAccount(OpenAccountAction openAccountAction);
}
