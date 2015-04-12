package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.db.TradeRequest;

public interface QueryManager {

	/**
	 * 查询交易流水表
	 * @param serialno 流水号
	 * @return
	 */
	public TradeRequest queryTradeRequest(String serialno);
}
