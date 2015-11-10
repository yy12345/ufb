package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.dao.TradeAccoinfoMapper;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.Tradeaccoinfo;

@Service
public class TradeAccoManagerImpl implements TradeAccoManager{

	@Autowired
	private TradeAccoinfoMapper tradeAccoinfoMapper;

	@Override
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno) {
		return tradeAccoinfoMapper.getTradeAccoList(custno, Constant.HftSysConfig.HftFundCorpno);
	}
	
	@Override
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno, String fundcorpno) {
		return tradeAccoinfoMapper.getTradeAccoList(custno, fundcorpno);
	}
	
	public TradeAccoinfoOfMore getTradeAcco(String custno, String fundcorpno, String bankserialid){
		return tradeAccoinfoMapper.getTradeAcco(custno, fundcorpno, bankserialid, "Y");
	}

	@Override
	public Tradeaccoinfo getTradeaccoinfo(String custno) {
		Tradeaccoinfo tradeacco=new Tradeaccoinfo();
		tradeacco.setCustno(custno);
		return tradeAccoinfoMapper.getTradeaccoinfo(tradeacco);
	}

}
