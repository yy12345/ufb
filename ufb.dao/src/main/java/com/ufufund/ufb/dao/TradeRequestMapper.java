package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.TradeRequest;

public interface TradeRequestMapper {
	
	public TradeRequest getBySerialno(String serialno);

	public int add(TradeRequest tradeRequest);
	
	public int update(TradeRequest tradeRequest);
	
	public List<TradeRequest> qryRecentTradeList(@Param("custno")String custno, @Param("n")int n);
}
