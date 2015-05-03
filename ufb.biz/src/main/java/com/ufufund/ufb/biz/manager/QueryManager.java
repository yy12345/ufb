package com.ufufund.ufb.biz.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.TradeAccoVo;

@Service
public interface QueryManager {

	/**
	 * 查询交易账号资产明细
	 * @param tradeAccos 交易账号
	 * @return
	 */
	public TradeAccoVo queryAssets(String tradeAcco);
	
	/**
	 * 查询用户总资产
	 * @param tradeAccos 交易账号列表
	 * @return
	 */
	public Assets queryAssets(List<BankCardWithTradeAcco> tradeAccoList);
	
	/**
	 * 查询交易流水
	 * @param serialno 流水号
	 * @return
	 */
	public TradeRequest queryTradeRequest(String serialno);
	
	/**
	 * 查询最近交易明细
	 * @param custno
	 * @param n 获取条数
	 * @return
	 */
	public List<TradeRequest> qryRecentTradeList(String custno, String apkind, int n);
}
