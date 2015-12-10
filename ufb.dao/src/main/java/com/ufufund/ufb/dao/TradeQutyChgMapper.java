package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.TradeQutyChg;

public interface TradeQutyChgMapper {

	public TradeQutyChg getBySerialno(String serialno);
	
	public TradeQutyChg getByOldSerialno(String oldserialno);

	public int add(TradeQutyChg tradeQutyChg);
	
	public TradeQutyChg getTradeQutyChg(TradeQutyChg tradeQutyChg);
}
