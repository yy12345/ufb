package com.ufufund.ufb.biz.manager.impl;

import java.util.ArrayList;
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
		List<String> tradeaccosts = new ArrayList<String>();
		tradeaccosts.add("Y"); // 
		tradeaccosts.add("N"); // 
		return tradeAccoinfoMapper.getTradeAccoList(custno, null, null, tradeaccosts);
	}
	
	@Override
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno, String fundcorpno, String level, List<String> tradeaccosts) {
		return tradeAccoinfoMapper.getTradeAccoList(custno, fundcorpno, level, tradeaccosts);
	}
	
	public TradeAccoinfoOfMore getTradeAcco(String custno, String fundcorpno, String bankserialid){
		return tradeAccoinfoMapper.getTradeAcco(custno, fundcorpno, bankserialid, "Y");
	}

}
