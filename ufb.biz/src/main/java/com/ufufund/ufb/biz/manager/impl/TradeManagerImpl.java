package com.ufufund.ufb.biz.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.TradeManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.ThreadLocalUtil;
import com.ufufund.ufb.dao.TradeRequestMapper;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.remote.hft.BuyApplyRequest;
import com.ufufund.ufb.model.remote.hft.BuyApplyResponse;
import com.ufufund.ufb.model.remote.hft.CancelRequest;
import com.ufufund.ufb.model.remote.hft.CancelResponse;
import com.ufufund.ufb.model.remote.hft.RealRedeemRequest;
import com.ufufund.ufb.model.remote.hft.RealRedeemResponse;
import com.ufufund.ufb.model.remote.hft.RedeemRequest;
import com.ufufund.ufb.model.remote.hft.RedeemResponse;
import com.ufufund.ufb.model.remote.hft.SubApplyRequest;
import com.ufufund.ufb.model.remote.hft.SubApplyResponse;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.CancelVo;
import com.ufufund.ufb.model.vo.RedeemVo;
import com.ufufund.ufb.model.vo.Today;
import com.ufufund.ufb.remote.HftTradeService;
import com.ufufund.ufb.remote.test.HftTradeServiceTest;

@Service
public class TradeManagerImpl implements TradeManager{
	private static Logger LOG = LoggerFactory.getLogger(HftTradeServiceTest.class);

	@Autowired
	private HftTradeService hftTradeService;
	
	@Autowired
	private TradeRequestMapper tradeRequestMapper;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	@Override
	public String subApply(ApplyVo vo) {
		
		String serialno = "";
		Today today = workDayManager.getSysDayInfo();
		
		/** 生成本地交易流水 **/
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(serialno);
		tradeRequest.setCustno(vo.getCustno());
		tradeRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeRequest.setTradeacco(vo.getTradeacco());
		tradeRequest.setAppdate(today.getDate());
		tradeRequest.setApptime(today.getTime());
		tradeRequest.setWorkday(today.getWorkday());
		tradeRequest.setApkind(Apkind.SUBAPPLY.getValue());
		tradeRequest.setFundcode(vo.getFundcode());
		tradeRequest.setAppamt(vo.getAppamt());
		tradeRequest.setAppvol(vo.getAppvol());
		tradeRequest.setShareclass("0");
		tradeRequest.setDividenttype("0");
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno("");
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 生成认购流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed> 生成认购流水：serialno="+tradeRequest.getSerialno());
			return null;
		}
		
		/** 调用基金公司接口 **/
		SubApplyRequest request = new SubApplyRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.SubApply);
		request.setApplicationNo(serialno);
		request.setTransactionAccountID(vo.getTradeacco());
		request.setFundCode(vo.getFundcode());
		request.setApplicationAmount(vo.getAppamt());
		request.setShareClass(vo.getShareclass());
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 海富通认购下单："+request);
		SubApplyResponse response = hftTradeService.subApply(request);
		
		String result = null;
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			result = response.getApplicationNo();
		}
		if(result == null){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed> 海富通认购下单：serialno="+request.getApplicationNo());
		}
		return result;
	}

	@Override
	public String buyApply(ApplyVo vo) {
		String serialno = "";
		Today today = workDayManager.getSysDayInfo();
		
		/** 生成本地交易流水 **/
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(serialno);
		tradeRequest.setCustno(vo.getCustno());
		tradeRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeRequest.setTradeacco(vo.getTradeacco());
		tradeRequest.setAppdate(today.getDate());
		tradeRequest.setApptime(today.getTime());
		tradeRequest.setWorkday(today.getWorkday());
		tradeRequest.setApkind(Apkind.BUYAPPLY.getValue());
		tradeRequest.setFundcode(vo.getFundcode());
		tradeRequest.setAppamt(vo.getAppamt());
		tradeRequest.setAppvol(vo.getAppvol());
		tradeRequest.setShareclass("0");
		tradeRequest.setDividenttype("0");
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno("");
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 生成申购流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed> 生成申购流水：serialno="+tradeRequest.getSerialno());
			return null;
		}
		
		/** 调用基金公司接口 **/
		BuyApplyRequest request = new BuyApplyRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BuyApply);
		request.setApplicationNo(serialno);
		request.setTransactionAccountID(vo.getTradeacco());
		request.setFundCode(vo.getFundcode());
		request.setApplicationAmount(vo.getAppamt());
		request.setShareClass(vo.getShareclass());
		request.setAutoFrozen("0");
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 海富通申购下单："+request);
		BuyApplyResponse response = hftTradeService.buyApply(request);
		
		String result = null;
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			result = response.getApplicationNo();
		}
		if(result == null){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed> 海富通申购下单：serialno="+request.getApplicationNo());
		}
		return result;
	}

	@Override
	public String redeem(RedeemVo vo) {
		String serialno = "";
		Today today = workDayManager.getSysDayInfo();
		
		/** 生成本地交易流水 **/
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(serialno);
		tradeRequest.setCustno(vo.getCustno());
		tradeRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeRequest.setTradeacco(vo.getTradeacco());
		tradeRequest.setAppdate(today.getDate());
		tradeRequest.setApptime(today.getTime());
		tradeRequest.setWorkday(today.getWorkday());
		tradeRequest.setApkind(Apkind.REDEEM.getValue());
		tradeRequest.setFundcode(vo.getFundcode());
		tradeRequest.setAppamt(vo.getAppamt());
		tradeRequest.setAppvol(vo.getAppvol());
		tradeRequest.setShareclass("0");
		tradeRequest.setDividenttype("0");
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno("");
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 生成普通赎回流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed> 生成普通赎回流水：serialno="+tradeRequest.getSerialno());
			return null;
		}
		
		/** 调用基金公司接口 **/
		RedeemRequest request = new RedeemRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.Redeem);
		request.setApplicationNo(serialno);
		request.setTransactionAccountID(vo.getTradeacco());
		request.setFundCode(vo.getFundcode());
		request.setApplicationVol(vo.getAppvol());
		request.setShareClass("1");
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 海富通普通赎回下单："+request);
		RedeemResponse response = hftTradeService.redeem(request);
		
		String result = null;
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			result = response.getApplicationNo();
		}
		if(result == null){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed> 海富通普通赎回下单：serialno="+request.getApplicationNo());
		}
		return result;
	}

	@Override
	public String realRedeem(RedeemVo vo) {
		String serialno = "";
		Today today = workDayManager.getSysDayInfo();
		
		/** 生成本地交易流水 **/
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(serialno);
		tradeRequest.setCustno(vo.getCustno());
		tradeRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeRequest.setTradeacco(vo.getTradeacco());
		tradeRequest.setAppdate(today.getDate());
		tradeRequest.setApptime(today.getTime());
		tradeRequest.setWorkday(today.getWorkday());
		tradeRequest.setApkind(Apkind.REALREDEEM.getValue());
		tradeRequest.setFundcode(vo.getFundcode());
		tradeRequest.setAppamt(vo.getAppamt());
		tradeRequest.setAppvol(vo.getAppvol());
		tradeRequest.setShareclass("0");
		tradeRequest.setDividenttype("0");
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno("");
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 生成快速赎回流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed> 生成快速赎回流水：serialno="+tradeRequest.getSerialno());
			return null;
		}
		
		/** 调用基金公司接口 **/
		RealRedeemRequest request = new RealRedeemRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.RealRedeem);
		request.setApplicationNo(serialno);
		request.setTransactionAccountID(vo.getTradeacco());
		request.setFundCode(vo.getFundcode());
		request.setApplicationVol(vo.getAppvol());
		request.setShareClass("1");
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 海富通快速赎回下单："+request);
		RealRedeemResponse response = hftTradeService.realRedeem(request);
		
		String result = null;
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			result = response.getApplicationNo();
		}
		if(result == null){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed> 海富通快速赎回下单：serialno="+request.getApplicationNo());
		}
		return result;
	}

	@Override
	public String cancel(CancelVo vo) {
		
		CancelRequest request = new CancelRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.Cancel);
		request.setApplicationNo("20150410CC0010");
		request.setTransactionAccountID("0001");
		request.setOriginalAppSheetNo("20150410CC0001");
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 海富通撤单申请："+request);
		CancelResponse response = hftTradeService.cancel(request);
		
		String result = null;
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			result = response.getApplicationNo();
		}
		if(result == null){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed> 海富通撤单申请：serialno="+request.getApplicationNo());
		}
		return result;
	}

}
