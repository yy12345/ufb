package com.ufufund.ufb.biz.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.dao.FundBalanceMapper;
import com.ufufund.ufb.dao.TradeQutyChgMapper;
import com.ufufund.ufb.dao.TradeRequestMapper;
import com.ufufund.ufb.model.db.FundBalance;
import com.ufufund.ufb.model.db.FundNav;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeQutyChg;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.TradeAccoVo;
import com.ufufund.ufb.remote.HftQueryService;

@Service
public class QueryManagerImpl implements QueryManager{
//	private static Logger LOG = LoggerFactory.getLogger(QueryManagerImpl.class);
	
	@Autowired
	private HftQueryService hftQueryService;

	@Autowired
	private TradeRequestMapper tradeRequestMapper;
	
	@Autowired
	private TradeQutyChgMapper tradeQutyChgMapper;
	
	@Autowired
	private FundBalanceMapper fundBalanceMapper ;

	@Override
	public TradeAccoVo queryAssets(String tradeAcco){
		TradeAccoVo result = new TradeAccoVo();
		
		// 已确认份额
		FundBalance fundBalance = new FundBalance();
		fundBalance.setTradeacco(tradeAcco);
		fundBalance.setFundcode(BasicFundinfo.YFB.getFundCode());
		fundBalance = fundBalanceMapper.getFundBalance(fundBalance);
		TradeQutyChg tradeQutyChg = new TradeQutyChg();
		// 变动份额（因T+1日终确认，可能包含2个工作日的变动）
		tradeQutyChg.setTradeacco(tradeAcco);
		tradeQutyChg.setFundcode(BasicFundinfo.YFB.getFundCode());
		TradeQutyChg qutyChg = tradeQutyChgMapper.getTradeQutyChg(tradeQutyChg); 
	
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
		if(qutyChg != null){
			total = total.add(qutyChg.getTotal());
			available = available.add(qutyChg.getAvailable());
			frozen = frozen.add(qutyChg.getFrozen());
		}
		
		/** 
		 * 注：fundbalance份额与tradequtychg份额对应字段分别累加后，
		 * 才满足：total = available + frozen 
		 **/
		
		result.setTotal(total);  
		result.setAvailable(available);
		result.setRealavailable(available);  // 暂时设置与available一致
		result.setFrozen(frozen);
		
		//
		result.setFunddayincome(funddayincome);
		result.setTotalincome(totalincome);
		return result;
	}

	@Override
	public Assets queryAssets(List<TradeAccoinfoOfMore> tradeAccoList) {
		
		Assets result = new Assets();
		
		List<TradeAccoVo> accoList = new ArrayList<TradeAccoVo>();
		for(TradeAccoinfoOfMore acco : tradeAccoList){
			TradeAccoVo tradeAccoVo = queryAssets(acco.getTradeacco());
			
			//BeanUtils.copyProperties(acco, tradeAccoVo);
			//accoList.add(tradeAccoVo);
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
	
	@Override
	public TradeRequest queryTradeRequest(String serialno) {
		return tradeRequestMapper.getBySerialno(serialno);
	}
	
	@Override
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
	
	@Override
	public List<FundNav> qryFundNavList(FundNav fundnav){
		return tradeRequestMapper.qryFundNavList(fundnav);
	}
}
