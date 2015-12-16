package com.ufufund.ufb.biz.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.dao.FundBalanceMapper;
import com.ufufund.ufb.dao.TradeQutyChgMapper;
import com.ufufund.ufb.dao.TradeRequestMapper;
import com.ufufund.ufb.model.db.Assets;
import com.ufufund.ufb.model.db.FundBalance;
import com.ufufund.ufb.model.db.FundNav;
import com.ufufund.ufb.model.db.TradeAcco;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeQutyChg;
import com.ufufund.ufb.model.db.TradeRequest;

/**
 * 用户资产查询和交易查询类 
 * @author ayis
 * 2015年5月30日
 */
@Service
public class QueryManager{

	@Autowired
	private TradeRequestMapper tradeRequestMapper;
	
	@Autowired
	private TradeQutyChgMapper tradeQutyChgMapper;
	
	@Autowired
	private FundBalanceMapper fundBalanceMapper ;

	/**
	 * 查询交易账号资产明细
	 * @param tradeAccos 交易账号
	 * @return
	 */
	public TradeAcco queryAssets(String tradeAcco, String fundCode){
		TradeAcco result = new TradeAcco();
		
		// 已确认份额
		FundBalance fundBalance = new FundBalance();
		fundBalance.setTradeacco(tradeAcco);
		fundBalance.setFundcode(fundCode);
		fundBalance = fundBalanceMapper.getFundBalance(fundBalance);
		TradeQutyChg tradeQutyChg = new TradeQutyChg();
		// 变动份额（因T+1日终确认，可能包含2个工作日的变动）
		tradeQutyChg.setTradeacco(tradeAcco);
		tradeQutyChg.setFundcode(fundCode);
		tradeQutyChg = tradeQutyChgMapper.getTradeQutyChg(tradeQutyChg); 
	
		// 资产累计
		BigDecimal total = new BigDecimal(0.00); // 总资产
		BigDecimal available = new BigDecimal(0.00); // 可用份额
		BigDecimal frozen = new BigDecimal(0.00); //冻结份额
		BigDecimal funddayincome = new BigDecimal(0.00); // 
		BigDecimal totalincome = new BigDecimal(0.00); //
		// 已确认份额
		if(fundBalance != null){
			total = fundBalance.getTotalfundvol();
			available = fundBalance.getAvailablevol();
			frozen = fundBalance.getTotalfrozenvol(); 
			funddayincome = fundBalance.getFunddayincome();
			totalincome = fundBalance.getTotalincome();
		}
		// 当日变动
		if(tradeQutyChg != null){
			total = total.add(tradeQutyChg.getTotal());
			available = available.add(tradeQutyChg.getAvailable());
			frozen = frozen.add(tradeQutyChg.getFrozen());
		}
		
		/** 
		 * 注：fundbalance份额与tradequtychg份额对应字段分别累加后，
		 * 才满足：total = available + frozen 
		 **/
		if(null==total){
			total = new BigDecimal(0.00);
		}
		if(null==available){
			available = new BigDecimal(0.00);
		}
		if(null==frozen){
			frozen = new BigDecimal(0.00);
		}
		if(null==funddayincome){
			funddayincome = new BigDecimal(0.00);
		}
		if(null==totalincome){
			totalincome = new BigDecimal(0.00);
		}
		result.setTotal(total);  
		result.setAvailable(available);
		result.setRealavailable(available);  // 暂时设置与available一致
		result.setFrozen(frozen);
		result.setFunddayincome(funddayincome);
		result.setTotalincome(totalincome);
		return result;
	}

	/**
	 * 查询用户总资产
	 * @param tradeAccos 交易账号列表
	 * @param fundCode 
	 * @return
	 */
	public Assets queryAssets(List<TradeAccoinfoOfMore> tradeAccoList, String fundCode) {
		
		Assets result = new Assets();
		
		List<TradeAcco> accoList = new ArrayList<TradeAcco>();
		for(TradeAccoinfoOfMore acco : tradeAccoList){
			TradeAcco tradeAccoVo = queryAssets(acco.getTradeacco(), fundCode);
			
			acco.setAvailable(tradeAccoVo.getAvailable());
			acco.setRealavailable(tradeAccoVo.getRealavailable());
			acco.setTotal(tradeAccoVo.getTotal());
			acco.setFrozen(tradeAccoVo.getFrozen());
			BeanUtils.copyProperties(acco, tradeAccoVo);
			accoList.add(tradeAccoVo);
			//
			result.setTotal(result.getTotal().add(tradeAccoVo.getTotal()));
			result.setAvailable(result.getAvailable().add(tradeAccoVo.getAvailable()));
			result.setRealavailable(result.getRealavailable().add(tradeAccoVo.getRealavailable()));
			result.setFrozen(result.getFrozen().add(tradeAccoVo.getFrozen()));
			result.setFunddayincome(result.getFunddayincome().add(tradeAccoVo.getFunddayincome()));
			result.setTotalincome(result.getTotalincome().add(tradeAccoVo.getTotalincome()));
		}
		result.setAccoList(accoList);
		
		return result;
	}
	
	/**
	 * 查询交易流水
	 * @param custno
	 * @param serialno 流水号
	 * @return
	 */
	public TradeRequest queryTradeRequest(String custno, String serialno) {
		return tradeRequestMapper.getBySerialno(custno, serialno);
	}
	
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
			int end){
		return tradeRequestMapper.qryTradeList(
				custno, 
				apkinds,
				states,
				startappdt, 
				endappdt,
				start, 
				end);
	}
	
	/**
	 * 查询NAV
	 * @param fundnav
	 * @param n 获取条数
	 * @return
	 */
	public List<FundNav> qryFundNavList(FundNav fundnav){
		return tradeRequestMapper.qryFundNavList(fundnav);
	}
}
