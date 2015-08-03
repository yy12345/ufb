package com.ufufund.ufb.remote.hftfund.simulator;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.model.hftfund.BuyApplyRequest;
import com.ufufund.ufb.model.hftfund.BuyApplyResponse;
import com.ufufund.ufb.model.hftfund.CancelRequest;
import com.ufufund.ufb.model.hftfund.CancelResponse;
import com.ufufund.ufb.model.hftfund.FrozenRequest;
import com.ufufund.ufb.model.hftfund.FrozenResponse;
import com.ufufund.ufb.model.hftfund.RealRedeemRequest;
import com.ufufund.ufb.model.hftfund.RealRedeemResponse;
import com.ufufund.ufb.model.hftfund.RedeemRequest;
import com.ufufund.ufb.model.hftfund.RedeemResponse;
import com.ufufund.ufb.model.hftfund.SubApplyRequest;
import com.ufufund.ufb.model.hftfund.SubApplyResponse;
import com.ufufund.ufb.model.hftfund.TransferRequest;
import com.ufufund.ufb.model.hftfund.TransferResponse;
import com.ufufund.ufb.model.hftfund.UnFrozenRequest;
import com.ufufund.ufb.model.hftfund.UnFrozenResponse;
import com.ufufund.ufb.remote.hftfund.HftTradeService;
  
/**
 * 海富通交易相关接口
 * @author ayis
 * 2015年3月22日
 */
@Service
public class HftTradeServiceSimulator extends HftTradeService{
	private static final Logger LOG = LoggerFactory.getLogger(HftTradeServiceSimulator.class);
	
	/**
	 * 认购
	 * @param request
	 * @return
	 */
	@Override
	public SubApplyResponse subApply(SubApplyRequest request){
		SubApplyResponse response = new SubApplyResponse();
		response.setVersion(Constant.HftSysConfig.Version);
		response.setMerchantId(Constant.HftSysConfig.MerchantId);
		response.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		response.setBusinType(request.getBusinType());
		response.setApplicationNo(request.getApplicationNo());
		
		Date now = new Date();
		response.setAppSheetSerialNo("APPSHEET"+DateUtil.format(now, DateUtil.FULL_PATTERN_1));
		response.setTransactionDate(DateUtil.format(now, DateUtil.DATE_PATTERN_1));
		response.setTransactiontime(DateUtil.format(now, DateUtil.TIME_PATTERN_1));
		
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		LOG.warn("模拟器返回："+response);
		return response;
	}
	
	/**
	 * 申购
	 * @param request
	 * @return
	 */
	@Override
	public BuyApplyResponse buyApply(BuyApplyRequest request){
		BuyApplyResponse response = new BuyApplyResponse();
		response.setVersion(Constant.HftSysConfig.Version);
		response.setMerchantId(Constant.HftSysConfig.MerchantId);
		response.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		response.setBusinType(request.getBusinType());
		response.setApplicationNo(request.getApplicationNo());
		
		Date now = new Date();
		response.setAppSheetSerialNo("APPSHEET"+DateUtil.format(now, DateUtil.FULL_PATTERN_1));
		response.setTransactionDate(DateUtil.format(now, DateUtil.DATE_PATTERN_1));
		response.setTransactiontime(DateUtil.format(now, DateUtil.TIME_PATTERN_1));
		
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		LOG.warn("模拟器返回："+response);
		return response;
	}
	
	/**
	 * 普通赎回
	 * @param request
	 * @return
	 */
	@Override
	public RedeemResponse redeem(RedeemRequest request){
		RedeemResponse response = new RedeemResponse();
		response.setVersion(Constant.HftSysConfig.Version);
		response.setMerchantId(Constant.HftSysConfig.MerchantId);
		response.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		response.setBusinType(request.getBusinType());
		response.setApplicationNo(request.getApplicationNo());
		
		Date now = new Date();
		response.setAppSheetSerialNo("APPSHEET"+DateUtil.format(now, DateUtil.FULL_PATTERN_1));
		response.setTransactionDate(DateUtil.format(now, DateUtil.DATE_PATTERN_1));
		response.setTransactiontime(DateUtil.format(now, DateUtil.TIME_PATTERN_1));
		response.setTotalFundVol(new BigDecimal("5000.00"));
		response.setAvailableVol(new BigDecimal("3000.00"));
		response.setTotalFrozenVol(new BigDecimal("2000.00"));
		
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		LOG.warn("模拟器返回："+response);
		return response;
	}
	
	/**
	 * 快速赎回
	 * @param request
	 * @return
	 */
	@Override
	public RealRedeemResponse realRedeem(RealRedeemRequest request){
		RealRedeemResponse response = new RealRedeemResponse();
		response.setVersion(Constant.HftSysConfig.Version);
		response.setMerchantId(Constant.HftSysConfig.MerchantId);
		response.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		response.setBusinType(request.getBusinType());
		response.setApplicationNo(request.getApplicationNo());
		
		Date now = new Date();
		response.setAppSheetSerialNo("APPSHEET"+DateUtil.format(now, DateUtil.FULL_PATTERN_1));
		response.setTransactionDate(DateUtil.format(now, DateUtil.DATE_PATTERN_1));
		response.setTransactiontime(DateUtil.format(now, DateUtil.TIME_PATTERN_1));
		response.setTotalFundVol(new BigDecimal("5000.00"));
		response.setAvailableVol(new BigDecimal("3000.00"));
		response.setTotalFrozenVol(new BigDecimal("2000.00"));
		
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		LOG.warn("模拟器返回："+response);
		return response;
	}
	
	/**
	 * 撤单
	 * @param request
	 * @return
	 */
	@Override
	public CancelResponse cancel(CancelRequest request){
		return super.send(request, CancelResponse.class);
	}
	
	/**
	 * 冻结
	 * @param request
	 * @return
	 */
	@Override
	public FrozenResponse frozen(FrozenRequest request){
		return super.send(request, FrozenResponse.class);
	}
	
	/**
	 * 解冻
	 * @param request
	 * @return
	 */
	@Override
	public UnFrozenResponse unfrozen(UnFrozenRequest request){
		return super.send(request, UnFrozenResponse.class);
	}
	
	/**
	 * 快速过户
	 * @param request
	 * @return
	 */
	@Override
	public TransferResponse transfer(TransferRequest request){
		return super.send(request, TransferResponse.class);
	}
	
}
