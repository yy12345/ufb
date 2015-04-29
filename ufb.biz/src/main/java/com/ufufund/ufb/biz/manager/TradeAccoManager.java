package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.BankCardWithTradeAcco;

/**
 * tradeAcco的manager定义
 * @author ayis
 * 2015年4月29日
 */
public interface TradeAccoManager {

	/**
	 * 获取带关联银行卡信息的基金交易账号列表
	 * @param custno
	 * @return
	 */
	public List<BankCardWithTradeAcco> getTradeAccoList(String custno); 
	
}
