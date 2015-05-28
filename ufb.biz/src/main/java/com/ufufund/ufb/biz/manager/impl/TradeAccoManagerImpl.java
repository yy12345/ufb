package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.dao.TradeAccoinfoMapper;
import com.ufufund.ufb.model.db.BankCardWithTradeAcco;

@Service
public class TradeAccoManagerImpl implements TradeAccoManager{

	@Autowired
	private TradeAccoinfoMapper tradeAccoinfoMapper;
	
	@Override
	public List<BankCardWithTradeAcco> getTradeAccoList(String custno) {
		
		List<BankCardWithTradeAcco> userCardList = tradeAccoinfoMapper.getTradeAccoList(custno, null);
//		if(userCardList == null || userCardList.size() <= 0){
//			throw new UserException("您还未开户，请先开通交易账户！");
//		}
		return userCardList;
	}

}
