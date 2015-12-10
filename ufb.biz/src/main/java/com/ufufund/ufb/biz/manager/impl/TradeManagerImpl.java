package com.ufufund.ufb.biz.manager.impl;

import java.math.BigDecimal;

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
import com.ufufund.ufb.dao.CancelRequestMapper;
import com.ufufund.ufb.dao.TradeQutyChgMapper;
import com.ufufund.ufb.dao.TradeRequestMapper;
import com.ufufund.ufb.model.db.Apply;
import com.ufufund.ufb.model.db.CancelRequest;
import com.ufufund.ufb.model.db.Cancel;
import com.ufufund.ufb.model.db.Redeem;
import com.ufufund.ufb.model.db.Today;
import com.ufufund.ufb.model.db.TradeQutyChg;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.hftfund.BuyApplyRequest;
import com.ufufund.ufb.model.hftfund.BuyApplyResponse;
import com.ufufund.ufb.model.hftfund.CancelResponse;
import com.ufufund.ufb.model.hftfund.RealRedeemRequest;
import com.ufufund.ufb.model.hftfund.RealRedeemResponse;
import com.ufufund.ufb.model.hftfund.RedeemRequest;
import com.ufufund.ufb.model.hftfund.RedeemResponse;
import com.ufufund.ufb.model.hftfund.SubApplyRequest;
import com.ufufund.ufb.model.hftfund.SubApplyResponse;
import com.ufufund.ufb.remote.hftfund.HftTradeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TradeManagerImpl implements TradeManager{

	@Autowired
	private HftTradeService hftTradeService;
	
	@Autowired
	private TradeRequestMapper tradeRequestMapper;
	
	@Autowired
	private CancelRequestMapper cancelRequestMapper;

	@Autowired
	private TradeQutyChgMapper tradeQutyChgMapper;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	@Autowired
	private TradeManagerValidator validator;
	
	@Autowired
	private TradeManagerHelper helper;
	
	@Override
	public String subApply(Apply vo) {
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
		log.info("认购流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			log.error("<Failed>认购流水：serialno="+tradeRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		SubApplyRequest request = helper.toSubApplyRequest(vo);
		log.info("认购下单："+request);
		SubApplyResponse response = hftTradeService.subApply(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			tradeRequest = helper.toResponse4SubApply(response);
			log.info(" 认购回写："+tradeRequest);
			n = tradeRequestMapper.update(tradeRequest);
			if(n < 1){
				log.error("<Failed>认购回写：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
		}else {
			// 执行失败，处理返回异常码
			log.error("<Failed>认购下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		
		return response.getApplicationNo();
	}
	
	@Override
	public String buyApply(Apply vo) {
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
		log.info("申购流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			log.error("<Failed>申购流水：serialno="+tradeRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		BuyApplyRequest request = helper.toBuyApplyRequest(vo);
		log.info("申购下单："+request);
		BuyApplyResponse response = hftTradeService.buyApply(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			tradeRequest = helper.toResponse4BuyApply(response);
			log.info("申购回写："+tradeRequest);
			n = tradeRequestMapper.update(tradeRequest);
			if(n < 1){
				log.error("<Failed>申购回写：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
			
			// 货基直接记份额，暂时算成功
			TradeQutyChg tradeQutyChg = helper.toTradeQutyChg4BuyApply(vo);
			n = tradeQutyChgMapper.add(tradeQutyChg);
			if(n < 1){
				log.error("<Failed>申购回写份额：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
			
		}else{
			// 执行失败，处理返回异常码
			log.error("<Failed>申购下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		
		return response.getApplicationNo();
	}

	@Override
	public String redeem(Redeem vo) {
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
		log.info("普通赎回流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			log.error("<Failed>生成普通赎回流水：serialno="+tradeRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		RedeemRequest request = helper.toRedeemRequest(vo);
		log.info("普通赎回下单："+request);
		RedeemResponse response = hftTradeService.redeem(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			tradeRequest = helper.toResponse4Redeem(response);
			log.info("普通赎回回写："+tradeRequest);
			n = tradeRequestMapper.update(tradeRequest);
			if(n < 1){
				log.error("<Failed>普通赎回回写：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
			// 货基直接记份额，暂时算成功
			TradeQutyChg tradeQutyChg = helper.toTradeQutyChg4Redeem(vo);
			n = tradeQutyChgMapper.add(tradeQutyChg);
			if(n < 1){
				log.error("<Failed>普通赎回回写份额：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
		}else {
			// 执行失败，处理返回异常码
			log.error("<Failed>普通赎回下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		
		return response.getApplicationNo();
	}

	@Override
	public String realRedeem(Redeem vo) {
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
		log.info("快速赎回流水："+tradeRequest);
		int n = tradeRequestMapper.add(tradeRequest);
		if(n < 1){
			log.error("<Failed>快速赎回流水：serialno="+tradeRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		RealRedeemRequest request = helper.toRealRedeemRequest(vo);
		log.info("快速赎回下单："+request);
		RealRedeemResponse response = hftTradeService.realRedeem(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			tradeRequest = helper.toResponse4RealRedeem(response);
			log.info("快速赎回回写："+tradeRequest);
			n = tradeRequestMapper.update(tradeRequest);
			if(n < 1){
				log.error("<Failed>快速赎回回写：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
			// 货基直接记份额，暂时算成功
			TradeQutyChg tradeQutyChg = helper.toTradeQutyChg4RealRedeem(vo);
			n = tradeQutyChgMapper.add(tradeQutyChg);
			if(n < 1){
				log.error("<Failed>快速赎回回写份额：serialno="+tradeRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
		}else {
			// 执行失败，处理返回异常码
			log.error("<Failed>快速赎回下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		
		return response.getApplicationNo();
	}

	@Override
	public String cancel(Cancel vo) {
		
		String serialno = SequenceUtil.getSerial();
		Today today = workDayManager.getSysDayInfo();
		vo.setSerialno(serialno);
		vo.setCanceldt(today.getDate());
		vo.setCanceltm(today.getTime());
		vo.setWorkday(today.getWorkday());
		
		// 参数及业务规则验证
		validator.validateCancel(vo);
		
		TradeRequest oldtradeRequest = tradeRequestMapper.getBySerialno(vo.getCustno(), vo.getOldserialno());
		//原交易申请流水号不存在
		if(null == oldtradeRequest){
			log.error("<Failed>原交易申请流水号不存在：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		//不能撤销已受理的单		
		if(!"I".equals(oldtradeRequest.getState())){
			log.error("<Failed>不能撤销已受理的单：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		//撤单业务不能取消
		if(Apkind.CANCEL.getValue().equals(oldtradeRequest.getApkind())){
			log.error("<Failed>撤单业务不能取消：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		//认购业务不能取消
		if(Apkind.SUBAPPLY.getValue().equals(oldtradeRequest.getApkind())){
			log.error("<Failed>认购业务不能取消：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		// 相同工作日单据财允许撤销
		if(!vo.getWorkday().equals(oldtradeRequest.getWorkday())){
			log.error("<Failed>相同工作日单据才允许撤销：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		// 不能重复撤单
		CancelRequest oldCancelRequest = cancelRequestMapper.getByOldSerialno(vo.getCustno(), vo.getOldserialno());
		if(null != oldCancelRequest){
			log.error("<Failed>不能重复撤单：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		//更新状态为已撤单
		//IBF_WRITE_TRADESTATUS 
		//PI_STATUS     => 'C',
		//更新状态为已撤单 更新母单APPLYST状态为C  TRANSST 为G 
		
		/** 生成本地交易流水 **/
		CancelRequest cancelRequest = helper.toCancelRequest4Cancel(vo);
		log.info("撤单流水："+cancelRequest);
		int n = cancelRequestMapper.add(cancelRequest);
		if(n < 1){
			log.error("<Failed>撤单流水：serialno="+cancelRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		com.ufufund.ufb.model.hftfund.CancelRequest request = helper.toCancelRequest(vo);
		log.info("撤单下单："+request);
		CancelResponse response = hftTradeService.cancel(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			cancelRequest = helper.toResponse4Cancel(response);
			log.info("撤单回写："+cancelRequest);
			n = cancelRequestMapper.update(cancelRequest);
			if(n < 1){
				log.error(" <Failed>撤单回写：serialno="+cancelRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
			
			// 获得原来那笔赎回单据
			TradeQutyChg oldTradeQutyChg = tradeQutyChgMapper.getByOldSerialno(vo.getOldserialno());
			oldTradeQutyChg.setSerialno(cancelRequest.getSerialno());  
			oldTradeQutyChg.setTotal(BigDecimal.ZERO);
			oldTradeQutyChg.setAvailable(BigDecimal.ZERO.subtract(oldTradeQutyChg.getAvailable()));
			oldTradeQutyChg.setFrozen(BigDecimal.ZERO.subtract(oldTradeQutyChg.getFrozen()));
			n = tradeQutyChgMapper.add(oldTradeQutyChg);
			if(n < 1){
				log.error("<Failed>撤单回写份额：serialno="+oldTradeQutyChg.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
			
		}else {
			// 执行失败，处理返回异常码
			log.error("<Failed>撤单下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		return response.getApplicationNo();
	}

}
