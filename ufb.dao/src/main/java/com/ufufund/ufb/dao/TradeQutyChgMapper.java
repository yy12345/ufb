package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.TradeQutyChg;

public interface TradeQutyChgMapper {

	public TradeQutyChg getBySerialno(String serialno);

	public int add(TradeQutyChg tradeQutyChg);

	// public int update(TradeQutyChg tradeQutyChg);

	// public List<TradeQutyChg> qryRecentTradeList(@Param("custno")String
	// custno, @Param("apkind")String apkind, @Param("n")int n);
}
