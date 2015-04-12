package com.ufufund.ufb.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.dao.TradeRequestMapper;
import com.ufufund.ufb.model.db.TradeRequest;

@Service
public class QueryManagerImpl implements QueryManager{

	@Autowired
	private TradeRequestMapper tradeRequestMapper;
	
	@Override
	public TradeRequest queryTradeRequest(String serialno) {
		return tradeRequestMapper.getBySerialno(serialno);
	}

}
