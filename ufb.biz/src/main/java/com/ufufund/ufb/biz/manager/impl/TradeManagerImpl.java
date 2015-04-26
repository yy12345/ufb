package com.ufufund.ufb.biz.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.TradeManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.TradeManagerHelper;
import com.ufufund.ufb.biz.manager.impl.validator.TradeManagerValidator;
import com.ufufund.ufb.biz.util.HftResponseUtil;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.SysErrorCode;
import com.ufufund.ufb.common.exception.SysException;
import com.ufufund.ufb.common.exception.UserErrorCode;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.common.utils.ThreadLocalUtil;
import com.ufufund.ufb.dao.TradeRequestMapper;
import com.ufufund.ufb.model.db.TradeRequest;
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

@Service
public class TradeManagerImpl implements TradeManager{
	private static Logger LOG = LoggerFactory.getLogger(TradeManagerImpl.class);

	@Autowired
	private HftTradeService hftTradeService;
	
	@Autowired
	private TradeRequestMapper tradeRequestMapper;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	@Autowired
	private TradeManagerValidator validator;
	
	@Autowired
	private TradeManagerHelper helper;
	
	@Override
	public String subApply(ApplyVo vo) {
		//  参数及业务规则验证
		validator.validateSubApply(vo);
		
		String serialno = SequenceUtil.getSerial();
		Today today = workDayManager.getSysDayInfo();
		vo.setSerialno(serialno);
		vo.setAppdate(today.getDate());
		vo.setApptime(today.getTime());
		vo.setWorkday(today.getWorkday());
		
		/** 生成本地交易流水 **/
		TradeRequest tradeRequest = helper.toTradeRequest4SubApply(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 认购流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>认购流水：serialno="+tradeRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		SubApplyRequest request = helper.toSubApplyRequest(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId() +", 认购下单："+request);
		SubApplyResponse response = hftTradeService.subApply(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			tradeRequest = helper.toResponse4SubApply(response);
			LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
					+", 认购回写："+tradeRequest);
			n = tradeRequestMapper.update(tradeRequest);
			if(n < 1){
				LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
						+", <Failed>认购回写：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
		}else {
			// 执行失败，处理返回异常码
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>认购下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		
		return response.getApplicationNo();
	}
	
	@Override
	public String buyApply(ApplyVo vo) {
		//  参数及业务规则验证
		validator.validateBuyApply(vo);
		
		String serialno = SequenceUtil.getSerial();
		Today today = workDayManager.getSysDayInfo();
		vo.setSerialno(serialno);
		vo.setAppdate(today.getDate());
		vo.setApptime(today.getTime());
		vo.setWorkday(today.getWorkday());
		
		/** 生成本地交易流水 **/
		TradeRequest tradeRequest = helper.toTradeRequest4BuyApply(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 申购流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>申购流水：serialno="+tradeRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		BuyApplyRequest request = helper.toBuyApplyRequest(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId() +", 申购下单："+request);
		BuyApplyResponse response = hftTradeService.buyApply(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			tradeRequest = helper.toResponse4BuyApply(response);
			LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
					+", 申购回写："+tradeRequest);
			n = tradeRequestMapper.update(tradeRequest);
			if(n < 1){
				LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
						+", <Failed>申购回写：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
		}else{
			// 执行失败，处理返回异常码
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>申购下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		
		return response.getApplicationNo();
	}

	@Override
	public String redeem(RedeemVo vo) {
		//  参数及业务规则验证
		validator.validateRedeem(vo);
		
		String serialno = SequenceUtil.getSerial();
		Today today = workDayManager.getSysDayInfo();
		vo.setSerialno(serialno);
		vo.setAppdate(today.getDate());
		vo.setApptime(today.getTime());
		vo.setWorkday(today.getWorkday());
		
		/** 生成本地交易流水 **/
		TradeRequest tradeRequest = helper.toTradeRequest4Redeem(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 普通赎回流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>生成普通赎回流水：serialno="+tradeRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		RedeemRequest request = helper.toRedeemRequest(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId() +", 普通赎回下单："+request);
		RedeemResponse response = hftTradeService.redeem(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			tradeRequest = helper.toResponse4Redeem(response);
			LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
					+", 普通赎回回写："+tradeRequest);
			n = tradeRequestMapper.update(tradeRequest);
			if(n < 1){
				LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
						+", <Failed>普通赎回回写：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
		}else {
			// 执行失败，处理返回异常码
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>普通赎回下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		
		return response.getApplicationNo();
	}

	@Override
	public String realRedeem(RedeemVo vo) {
		//  参数及业务规则验证
		validator.validateRealRedeem(vo);
		
		String serialno = SequenceUtil.getSerial();
		Today today = workDayManager.getSysDayInfo();
		vo.setSerialno(serialno);
		vo.setAppdate(today.getDate());
		vo.setApptime(today.getTime());
		vo.setWorkday(today.getWorkday());
		
		/** 生成本地交易流水 **/
		TradeRequest tradeRequest = helper.toTradeRequest4RealRedeem(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 快速赎回流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>快速赎回流水：serialno="+tradeRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		RealRedeemRequest request = helper.toRealRedeemRequest(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId() +", 快速赎回下单："+request);
		RealRedeemResponse response = hftTradeService.realRedeem(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			tradeRequest = helper.toResponse4RealRedeem(response);
			LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
					+", 快速赎回回写："+tradeRequest);
			n = tradeRequestMapper.update(tradeRequest);
			if(n < 1){
				LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
						+", <Failed>快速赎回回写：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
		}else {
			// 执行失败，处理返回异常码
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>快速赎回下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		
		return response.getApplicationNo();
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
		
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId() +", 撤单申请："+request);
		CancelResponse response = hftTradeService.cancel(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			// code...
		}else {
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>撤单申请：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		
		return response.getApplicationNo();
	}

}
