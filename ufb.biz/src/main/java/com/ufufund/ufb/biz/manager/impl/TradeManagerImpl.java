package com.ufufund.ufb.biz.manager.impl;

import java.math.BigDecimal;

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
import com.ufufund.ufb.dao.CancelRequestMapper;
import com.ufufund.ufb.dao.TradeQutyChgMapper;
import com.ufufund.ufb.dao.TradeRequestMapper;
import com.ufufund.ufb.model.db.CancelRequest;
import com.ufufund.ufb.model.db.TradeQutyChg;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.hft.BuyApplyRequest;
import com.ufufund.ufb.model.hft.BuyApplyResponse;
import com.ufufund.ufb.model.hft.CancelResponse;
//import com.ufufund.ufb.model.hft.CancelRequest;
//import com.ufufund.ufb.model.hft.CancelResponse;
import com.ufufund.ufb.model.hft.RealRedeemRequest;
import com.ufufund.ufb.model.hft.RealRedeemResponse;
import com.ufufund.ufb.model.hft.RedeemRequest;
import com.ufufund.ufb.model.hft.RedeemResponse;
import com.ufufund.ufb.model.hft.SubApplyRequest;
import com.ufufund.ufb.model.hft.SubApplyResponse;
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
	private CancelRequestMapper cancelRequestMapper;

	@Autowired
	private TradeQutyChgMapper tradeQutyChgMapper;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	@Autowired
	private TradeManagerValidator validator;
	
	@Autowired
	private TradeManagerHelper helper;
	
//	@Autowired
//	private TradeNotesMapper tradeNotesMapper;
	
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
		
//		/*
//		 * 写交易流水 
//		 * 没有更新交易流水 gaoxin
//		 */
//		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
//		fdacfinalresult.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
//		fdacfinalresult.setApkind(Apkind.BUYAPPLY.getValue());
//		fdacfinalresult.setWorkdate(today.getWorkday());
//		fdacfinalresult.setApdt(today.getDate());
//		fdacfinalresult.setAptm(today.getTime());
//		fdacfinalresult.setCustno(vo.getCustno());
//		fdacfinalresult.setFromtradeacco(vo.getTradeacco());
//		fdacfinalresult.setFrombankserialid(vo.getBankid());
//		fdacfinalresult.setTofundcode(vo.getFundcode());
//		fdacfinalresult.setTofundcorpno(vo.getFundcorpno());
//		fdacfinalresult.setAppamt(vo.getAppamt());
//		fdacfinalresult.setAppvol(vo.getAppvol());
//		fdacfinalresult.setStatus("I");
//		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
		
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
			
			// 货基直接记份额，暂时算成功
			TradeQutyChg tradeQutyChg = helper.toTradeQutyChg4BuyApply(vo);
			n = tradeQutyChgMapper.add(tradeQutyChg);
			if(n < 1){
				LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
						+", <Failed>申购回写份额：serialno="+tradeRequest.getSerialno());
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
			// 货基直接记份额，暂时算成功
			TradeQutyChg tradeQutyChg = helper.toTradeQutyChg4Redeem(vo);
			n = tradeQutyChgMapper.add(tradeQutyChg);
			if(n < 1){
				LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
						+", <Failed>普通赎回回写份额：serialno="+tradeRequest.getSerialno());
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
		
		
//		/*
//		 * 写交易流水   gaoxin 
//		 * 没有更新交易流水
//		 */
//		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
//		fdacfinalresult.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
//		fdacfinalresult.setApkind(Apkind.REALREDEEM.getValue());
//		fdacfinalresult.setWorkdate(today.getWorkday());
//		fdacfinalresult.setApdt(today.getDate());
//		fdacfinalresult.setAptm(today.getTime());
//		fdacfinalresult.setCustno(vo.getCustno());
//		fdacfinalresult.setTotradeacco(vo.getTradeacco());
//		fdacfinalresult.setTobankserialid(vo.getBankid());
//		fdacfinalresult.setFromfundcode(vo.getFundcode());
//		fdacfinalresult.setFromfundcorpno(vo.getFundcorpno());
//		fdacfinalresult.setAppamt(vo.getAppamt());
//		fdacfinalresult.setAppvol(vo.getAppvol());
//		fdacfinalresult.setStatus("I");
//		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
		
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
			// 货基直接记份额，暂时算成功
			TradeQutyChg tradeQutyChg = helper.toTradeQutyChg4RealRedeem(vo);
			n = tradeQutyChgMapper.add(tradeQutyChg);
			if(n < 1){
				LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
						+", <Failed>快速赎回回写份额：serialno="+tradeRequest.getSerialno());
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
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>原交易申请流水号不存在：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		//不能撤销已受理的单		
		if(!"I".equals(oldtradeRequest.getState())){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>不能撤销已受理的单：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		//撤单业务不能取消
		if(Apkind.CANCEL.getValue().equals(oldtradeRequest.getApkind())){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>撤单业务不能取消：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		//认购业务不能取消
		if(Apkind.SUBAPPLY.getValue().equals(oldtradeRequest.getApkind())){
			LOG.error("procc8nhvbessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>认购业务不能取消：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		// 相同工作日单据财允许撤销
		if(!vo.getWorkday().equals(oldtradeRequest.getWorkday())){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>相同工作日单据才允许撤销：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		// 不能重复撤单
		CancelRequest oldCancelRequest = cancelRequestMapper.getByOldSerialno(vo.getCustno(), vo.getOldserialno());
		if(null != oldCancelRequest){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>不能重复撤单：serialno="+vo.getOldserialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		// 获得原来那笔赎回单据
		TradeQutyChg oldTradeQutyChg = tradeQutyChgMapper.getByOldSerialno(vo.getOldserialno());
//		tradeQutyChg.setSerialno(vo.getSerialno());
//		tradeQutyChg.setCustno(vo.getCustno());
//		tradeQutyChg.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
//		tradeQutyChg.setTradeacco(vo.getTradeacco());
//		tradeQutyChg.setApkind(Apkind.REDEEM.getValue());
//		tradeQutyChg.setAppdate(vo.getAppdate());
//		tradeQutyChg.setWorkdate(vo.getWorkday());
//		tradeQutyChg.setFundcode(vo.getFundcode());
		oldTradeQutyChg.setTotal(BigDecimal.ZERO);
		oldTradeQutyChg.setAvailable(BigDecimal.ZERO.subtract(oldTradeQutyChg.getAvailable()));
		oldTradeQutyChg.setFrozen(BigDecimal.ZERO.subtract(oldTradeQutyChg.getFrozen()));
//		tradeQutyChg.setOldserialno(vo.getSerialno());
		tradeQutyChgMapper.add(oldTradeQutyChg);
		
		//更新状态为已撤单
		//IBF_WRITE_TRADESTATUS 
		//PI_STATUS     => 'C',
		//更新状态为已撤单 更新母单APPLYST状态为C  TRANSST 为G 
		
		/** 生成本地交易流水 **/
		CancelRequest cancelRequest = helper.toCancelRequest4Cancel(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
				+", 撤单流水："+cancelRequest);
		int n = cancelRequestMapper.add(cancelRequest);
		if(n < 1){
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>撤单流水：serialno="+cancelRequest.getSerialno());
			throw new SysException(SysErrorCode.SYS_LOCAL_FAILED);
		}
		
		/** 调用基金公司接口 **/
		com.ufufund.ufb.model.hft.CancelRequest request = helper.toCancelRequest(vo);
		LOG.info("proccessId="+ThreadLocalUtil.getProccessId() +", 撤单下单："+request);
		CancelResponse response = hftTradeService.cancel(request);
		
		/** 处理交易执行结果  **/
		if(response != null 
				&& Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			// 执行成功，回写本地数据
			cancelRequest = helper.toResponse4Cancel(response);
			LOG.info("proccessId="+ThreadLocalUtil.getProccessId()
					+", 撤单回写："+cancelRequest);
			n = cancelRequestMapper.update(cancelRequest);
			if(n < 1){
				LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
						+", <Failed>撤单回写：serialno="+cancelRequest.getSerialno());
				throw new UserException(UserErrorCode.USER_LOCAL_FAILED);
			}
		}else {
			// 执行失败，处理返回异常码
			LOG.error("proccessId="+ThreadLocalUtil.getProccessId()
					+", <Failed>撤单下单：serialno="+request.getApplicationNo());
			HftResponseUtil.dealResponseCode(response);
		}
		return response.getApplicationNo();
	}

}
