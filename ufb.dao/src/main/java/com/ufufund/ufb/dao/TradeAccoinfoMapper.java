package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.BankCardWithTradeAcco;

public interface TradeAccoinfoMapper {

	public List<BankCardWithTradeAcco> getTradeAccoList(String custno);
}
