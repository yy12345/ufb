package com.ufufund.ufb.remote.simulator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.remote.hft.BalanceQueryAsset;
import com.ufufund.ufb.model.remote.hft.BalanceQueryRequest;
import com.ufufund.ufb.model.remote.hft.BalanceQueryResponse;
import com.ufufund.ufb.model.remote.hft.TransQueryAsset;
import com.ufufund.ufb.model.remote.hft.TransQueryRequest;
import com.ufufund.ufb.model.remote.hft.TransQueryResponse;
import com.ufufund.ufb.remote.HftQueryService;

@Service
public class HftQueryServiceSimulator extends HftQueryService{
	private static final Logger LOG = LoggerFactory.getLogger(HftQueryServiceSimulator.class);
	
	/**
	 * 交易查询
	 * @param request
	 * @return
	 */
	@Override
	public TransQueryResponse transQuery(TransQueryRequest request){
		TransQueryResponse response = new TransQueryResponse();
		response.setVersion(Constant.HftSysConfig.Version);
		response.setMerchantId(Constant.HftSysConfig.MerchantId);
		response.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		response.setBusinType(request.getBusinType());
		response.setApplicationNo(request.getApplicationNo());
		
		response.setTotalRecord(1); 
		List<TransQueryAsset> assets = new ArrayList<TransQueryAsset>();
		TransQueryAsset a1 = new TransQueryAsset();
		a1.setAppSheetSerialNo("20150416001657");
		a1.setTransactionDate("20150416");
		a1.setTransactiontime("134332");
		a1.setConfirmFlag("1");
		a1.setPayFlag("1");
		TransQueryAsset a2 = new TransQueryAsset();
		a2.setAppSheetSerialNo("20150416001657");
		a2.setTransactionDate("20150416");
		a2.setTransactiontime("134332");
		a2.setConfirmFlag("9");
		a2.setPayFlag("2");
		assets.add(a1);
		assets.add(a2);
		response.setAssets(assets);
		
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		LOG.warn("模拟器返回："+response);
		return response;
	}
	
	/**
	 * 份额查询
	 * @param request
	 * @return
	 */
	@Override
	public BalanceQueryResponse balanceQuery(BalanceQueryRequest request){
		BalanceQueryResponse response = new BalanceQueryResponse();
		response.setVersion(Constant.HftSysConfig.Version);
		response.setMerchantId(Constant.HftSysConfig.MerchantId);
		response.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		response.setBusinType(request.getBusinType());
		response.setApplicationNo(request.getApplicationNo());
		
		response.setTotalRecord(1);
		List<BalanceQueryAsset> assets = new ArrayList<BalanceQueryAsset>();
		BalanceQueryAsset asset = new BalanceQueryAsset();
		asset.setFundCode(request.getFundCode());
		asset.setTotalFundVol(new BigDecimal("50000.00"));
		asset.setAvailableVol(new BigDecimal("40000.00"));
		asset.setTotalFrozenVol(new BigDecimal("10000.00"));
		assets.add(asset);
		response.setAssets(assets);
		
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		LOG.warn("模拟器返回："+response);
		return response;
	}
}
