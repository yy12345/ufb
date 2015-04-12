package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.TradeRequest;

public interface TradeRequestMapper {
	
	public TradeRequest getBySerialno(String serialno);

	public int add(TradeRequest tradeRequest);
	
	public int update(TradeRequest tradeRequest);
}
