package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;

/**
 * tradeAcco的manager定义
 * @author ayis
 * 2015年4月29日
 */
public interface TradeAccoManager {
	
	/**
	 * 获取带关联银行卡信息的基金交易账号列表
	 * 备注：只获取银行卡状态正常的记录
	 * @param custno
	 * @return
	 */
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno); 

	/**
	 * 获取带关联银行卡信息的基金交易账号列表
	 * @param custno
	 * @return
	 */
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno, String state); 
	
	/**
	 * 获取带关联银行卡信息的基金交易账号
	 * @param custno
	 * @return
	 */
	public TradeAccoinfoOfMore getTradeAcco(String custno, String fundcorpno, String bankserialid); 
	
}
