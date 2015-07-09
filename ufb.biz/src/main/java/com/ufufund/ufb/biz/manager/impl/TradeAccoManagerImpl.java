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
		return tradeAccoinfoMapper.getTradeAccoList(custno, "01", "Y");
	}

	@Override
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno, String fundcorpno, String state) {
		return tradeAccoinfoMapper.getTradeAccoList(custno, fundcorpno, state);
	}
	
	public TradeAccoinfoOfMore getTradeAcco(String custno, String fundcorpno, String bankserialid){
		return tradeAccoinfoMapper.getTradeAcco(custno, fundcorpno, bankserialid, "Y");
	}

}
