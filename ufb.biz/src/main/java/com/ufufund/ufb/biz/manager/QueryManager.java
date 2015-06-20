package com.ufufund.ufb.biz.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.model.db.FundNav;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.TradeAccoVo;

/**
 * 用户资产查询和交易查询类 
 * @author ayis
 * 2015年5月30日
 */
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
	public Assets queryAssets(List<TradeAccoinfoOfMore> tradeAccoList);
	
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
	public List<TradeRequest> qryTradeList(
			String custno, 
			List<String> apkinds,
			List<String> states,
			String startappdt, 
			String endappdt,
			int start, 
			int end);
	/**
	 * 查询NAV
	 * @param fundnav
	 * @param n 获取条数
	 * @return
	 */
	public List<FundNav> qryFundNavList(FundNav fundnav);
}
