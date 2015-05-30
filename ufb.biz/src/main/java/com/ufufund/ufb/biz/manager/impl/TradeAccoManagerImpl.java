package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.dao.TradeAccoinfoMapper;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;

@Service
public class TradeAccoManagerImpl implements TradeAccoManager{

	@Autowired
	private TradeAccoinfoMapper tradeAccoinfoMapper;

	@Override
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno) {
		return tradeAccoinfoMapper.getTradeAccoList(custno, "Y");
	}

	@Override
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno, String state) {
		return tradeAccoinfoMapper.getTradeAccoList(custno, state);
	}

}
