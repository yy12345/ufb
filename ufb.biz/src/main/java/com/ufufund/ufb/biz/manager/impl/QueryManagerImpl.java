package com.ufufund.ufb.biz.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.dao.FundBalanceMapper;
import com.ufufund.ufb.dao.TradeQutyChgMapper;
import com.ufufund.ufb.dao.TradeRequestMapper;
import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.db.FundBalance;
import com.ufufund.ufb.model.db.TradeQutyChg;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.TradeAccoVo;
import com.ufufund.ufb.remote.HftQueryService;

@Service
public class QueryManagerImpl implements QueryManager{
	private static Logger LOG = LoggerFactory.getLogger(QueryManagerImpl.class);
	
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
		
		/** 本地查询 **/
		TradeQutyChg tradeQutyChg = new TradeQutyChg();
//		// 当日份额变动充值(所有)
//		tradeQutyChg.setTradeacco(tradeAcco);
//		tradeQutyChg.setFundcode("001001");
//		tradeQutyChg.setApkind(null);
//		tradeQutyChg.setWorkdate(null);
//		BigDecimal asset_all = tradeQutyChgMapper.getTradeQutyChg(tradeQutyChg); 
//		if(null == asset_all){
//			asset_all = new BigDecimal(0); 
//		}
		// 当日份额变动充值(取现)
		tradeQutyChg.setTradeacco(tradeAcco);
		tradeQutyChg.setFundcode("001001");
		tradeQutyChg.setApkind("023");
		tradeQutyChg.setWorkdate(null);
		// 当日份额变动 023 取现
		TradeQutyChg quty = tradeQutyChgMapper.getTradeQutyChg(tradeQutyChg); 
		BigDecimal asset_023 = new BigDecimal(0);
		if(null != quty){
			asset_023 = quty.getFrozen();
		}
		// 当日份额变动充值(快速取现)
		tradeQutyChg.setTradeacco(tradeAcco);
		tradeQutyChg.setFundcode("001001");
		tradeQutyChg.setApkind("024");
		tradeQutyChg.setWorkdate(null);
		// 当日份额变动 024 快速取现
		quty = tradeQutyChgMapper.getTradeQutyChg(tradeQutyChg); 
		BigDecimal asset_024 = new BigDecimal(0);
		if(null != quty){
			asset_024 = quty.getAvailable();
		}
		// 当日份额变动充值(充值)
		tradeQutyChg.setTradeacco(tradeAcco);
		tradeQutyChg.setFundcode("001001");
		tradeQutyChg.setApkind("022");
		tradeQutyChg.setWorkdate(null);
		// 当日份额变动 022 快速取现
		quty = tradeQutyChgMapper.getTradeQutyChg(tradeQutyChg); 
		BigDecimal asset_022 = new BigDecimal(0);
		if(null != quty){
			asset_022 = quty.getAvailable();
		}
		// 昨日份额
		FundBalance fundBalance = new FundBalance();
		fundBalance.setTradeacco(tradeAcco);
		fundBalance.setFundcode("001001");
		fundBalance.setCustno(null);
		fundBalance = fundBalanceMapper.getFundBalance(fundBalance);
		BigDecimal total = new BigDecimal(0.00); // 总份额
		BigDecimal available = new BigDecimal(0.00); // 用户当前可用份额
		BigDecimal realavailable = new BigDecimal(0.00); //快取份额
		BigDecimal frozen = new BigDecimal(0.00); //冻结份额
		if(null != fundBalance){
			total = fundBalance.getTotalfundvol(); // 总份额
			available = fundBalance.getAvailablevol(); // 用户当前可用份额
			frozen = fundBalance.getTotalfrozenvol(); //冻结份额
			if(null == total){
				total = new BigDecimal(0);
			}
			if(null == available){
				available = new BigDecimal(0);
			}
			if(null == frozen){
				frozen = new BigDecimal(0);
			}
		}
		//总资产 = 历史  + 充值 + 快取
		result.setTotal(total.add(asset_022).add(asset_024));  
		//可取资产 = 历史  + 充值 + 取现 + 快取
		result.setAvailable(available.add(asset_022).subtract(asset_023).add(asset_024)); 
		//快速可取资产 = 历史 + 取现 + 快取
		realavailable = available.subtract(asset_023).add(asset_024);
		if(realavailable.compareTo(BigDecimal.ZERO) < 0){
			realavailable = new BigDecimal(0);
		}
		result.setRealavailable(realavailable);
		//冻结资产 = 历史 + 取现
		result.setFrozen(frozen.add(asset_023));
		
//		/** 走接口查询 **/
//		BalanceQueryRequest request = new BalanceQueryRequest();
//		request.setVersion(Constant.HftSysConfig.Version);
//		request.setMerchantId(Constant.HftSysConfig.MerchantId);
//		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
//		request.setBusinType(Constant.HftBusiType.BalanceQuery);
//		request.setApplicationNo(SequenceUtil.getSerial());
//		request.setTransactionAccountID(tradeAcco);
//		request.setFundCode(Constant.FundCode.YFB);
//		request.setPageNo("1");
//		request.setPageSize("10");
//		request.setShareClass("0");
//		
//		BalanceQueryResponse response = hftQueryService.balanceQuery(request);
//		
//		/** 处理交易执行结果  **/
//		if(response == null 
//				|| !Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
//			// 执行失败，处理返回异常码
//			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
//					+", <Failed>查询份额失败：serialno="+request.getApplicationNo()+", tradeAcco="+request.getTransactionAccountID());
//			HftResponseUtil.dealResponseCode(response);
//		}else {
//			// 执行成功 
//			List<BalanceQueryAsset> balanceList = response.getAssets();
//			for(BalanceQueryAsset asset : balanceList){
//				LOG.debug("asset="+asset);
//				result.setTotal(result.getTotal().add(asset.getTotalFundVol()));
//				result.setAvailable(result.getAvailable().add(asset.getAvailableVol()));
//				result.setFrozen(result.getFrozen().add(asset.getTotalFrozenVol()));
//			}
//		}
		
		return result;
	}

	@Override
	public Assets queryAssets(List<BankCardWithTradeAcco> tradeAccoList) {
		
		Assets result = new Assets();
		
		List<TradeAccoVo> accoList = new ArrayList<TradeAccoVo>();
		for(BankCardWithTradeAcco acco : tradeAccoList){
			TradeAccoVo tradeAccoVo = queryAssets(acco.getTradeacco());
			BeanUtils.copyProperties(acco, tradeAccoVo);
			accoList.add(tradeAccoVo);
			result.setTotal(result.getTotal().add(tradeAccoVo.getTotal()));
			result.setAvailable(result.getAvailable().add(tradeAccoVo.getAvailable()));
			result.setFrozen(result.getFrozen().add(tradeAccoVo.getFrozen()));
		}
		result.setAccoList(accoList);
		
		return result;
	}
	
	@Override
	public TradeRequest queryTradeRequest(String serialno) {
		return tradeRequestMapper.getBySerialno(serialno);
	}
	
	@Override
	public List<TradeRequest> qryRecentTradeList(String custno, List<String> apkind, int n){
		return tradeRequestMapper.qryRecentTradeList(custno,apkind, n);
	}
}
