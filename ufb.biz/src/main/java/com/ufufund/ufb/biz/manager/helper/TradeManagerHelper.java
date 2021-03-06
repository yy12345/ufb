package com.ufufund.ufb.biz.manager.helper;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.db.Apply;
import com.ufufund.ufb.model.db.CancelRequest;
import com.ufufund.ufb.model.db.Cancel;
import com.ufufund.ufb.model.db.Redeem;
import com.ufufund.ufb.model.db.TradeQutyChg;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.TradeStatus;
import com.ufufund.ufb.model.hftfund.BuyApplyRequest;
import com.ufufund.ufb.model.hftfund.BuyApplyResponse;
import com.ufufund.ufb.model.hftfund.CancelResponse;
import com.ufufund.ufb.model.hftfund.RealRedeemRequest;
import com.ufufund.ufb.model.hftfund.RealRedeemResponse;
import com.ufufund.ufb.model.hftfund.RedeemRequest;
import com.ufufund.ufb.model.hftfund.RedeemResponse;
import com.ufufund.ufb.model.hftfund.SubApplyRequest;
import com.ufufund.ufb.model.hftfund.SubApplyResponse;

@Service
public class TradeManagerHelper {
	
	/**
	 * 生成TradeRequest对象 - for 认购
	 * @param vo
	 * @return
	 */
	public TradeRequest toTradeRequest4SubApply(Apply vo){
		
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(vo.getSerialno());
		tradeRequest.setCustno(vo.getCustno());
		tradeRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeRequest.setTradeacco(vo.getTradeacco());
		tradeRequest.setAppdate(vo.getAppdate());
		tradeRequest.setApptime(vo.getApptime());
		tradeRequest.setWorkday(vo.getWorkday());
		tradeRequest.setApkind(Apkind.SUBAPPLY.getValue());
		tradeRequest.setFundcode(vo.getFundcode());
		tradeRequest.setAppamt(vo.getAppamt());
		tradeRequest.setAppvol(vo.getAppvol());
		tradeRequest.setShareclass(vo.getShareclass());
		tradeRequest.setDividmethod(vo.getDividmethod());
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno(vo.getReferno());
		return tradeRequest;
	}
	
	/**
	 * 生成SubApplyRequest请求对象
	 * @param vo
	 * @return
	 */
	public SubApplyRequest toSubApplyRequest(Apply vo){
		
		SubApplyRequest request = new SubApplyRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.SubApply);
		request.setApplicationNo(vo.getSerialno());
		request.setTransactionAccountID(vo.getTradeacco());
		request.setFundCode(vo.getFundcode());
		request.setApplicationAmount(vo.getAppamt());
		request.setShareClass(vo.getShareclass());
		return request;
	}
	
	/**
	 * 生成回写响应对象 - for 认购
	 * @param response
	 * @return
	 */
	public TradeRequest toResponse4SubApply(SubApplyResponse response){
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(response.getApplicationNo());
		tradeRequest.setSheetserialno(response.getAppSheetSerialNo());
//		tradeRequest.setAppdate(response.getTransactionDate());
//		tradeRequest.setApptime(response.getTransactiontime());
		tradeRequest.setState(TradeStatus.I.getValue());
		return tradeRequest;
	}
	
	
	/**
	 * 生成TradeRequest对象 - for 申购
	 * @param vo
	 * @return
	 */
	public TradeRequest toTradeRequest4BuyApply(Apply vo){
		
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(vo.getSerialno());
		tradeRequest.setCustno(vo.getCustno());
		tradeRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeRequest.setTradeacco(vo.getTradeacco());
		tradeRequest.setAppdate(vo.getAppdate());
		tradeRequest.setApptime(vo.getApptime());
		tradeRequest.setWorkday(vo.getWorkday());
		tradeRequest.setApkind(Apkind.BUYAPPLY.getValue());
		tradeRequest.setFundcode(vo.getFundcode());
		tradeRequest.setAppamt(vo.getAppamt());
		tradeRequest.setAppvol(vo.getAppvol());
		tradeRequest.setShareclass(vo.getShareclass());
		tradeRequest.setDividmethod(vo.getDividmethod());
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno(vo.getReferno());
		return tradeRequest;
	}
	
	/**
	 * 生成TradeQutyChg对象 - for 申购
	 * @param vo
	 * @return
	 */
	public TradeQutyChg toTradeQutyChg4BuyApply(Apply vo){
		TradeQutyChg tradeQutyChg = new TradeQutyChg();
		tradeQutyChg.setSerialno(vo.getSerialno());
		tradeQutyChg.setCustno(vo.getCustno());
		tradeQutyChg.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeQutyChg.setTradeacco(vo.getTradeacco());
		tradeQutyChg.setApkind(Apkind.BUYAPPLY.getValue());
		tradeQutyChg.setAppdate(vo.getAppdate());
		tradeQutyChg.setWorkdate(vo.getWorkday());
		tradeQutyChg.setFundcode(vo.getFundcode());
		tradeQutyChg.setTotal(vo.getAppamt());
		tradeQutyChg.setAvailable(BigDecimal.ZERO);
		tradeQutyChg.setFrozen(vo.getAppamt());
		tradeQutyChg.setOldserialno(vo.getSerialno());
		return tradeQutyChg;
	}
	
	/**
	 * 生成BuyApplyRequest请求对象
	 * @param vo
	 * @return
	 */
	public BuyApplyRequest toBuyApplyRequest(Apply vo){
		
		BuyApplyRequest request = new BuyApplyRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		if("1".equals(vo.getLevel())){
			request.setBusinType(Constant.HftBusiType.BuyApply);
		}else{
			request.setBusinType(Constant.HftBusiType.BuyApplyOrg);
		}
		request.setApplicationNo(vo.getSerialno());
		request.setTransactionAccountID(vo.getTradeacco());
		request.setFundCode(vo.getFundcode());
		request.setApplicationAmount(vo.getAppamt());
		request.setShareClass(vo.getShareclass());
		request.setAutoFrozen("0");
		return request;
	}
	
	/**
	 * 生成回写响应对象 - for 申购
	 * @param response
	 * @return
	 */
	public TradeRequest toResponse4BuyApply(BuyApplyResponse response){
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(response.getApplicationNo());
		tradeRequest.setSheetserialno(response.getAppSheetSerialNo());
//		tradeRequest.setAppdate(response.getTransactionDate());
//		tradeRequest.setApptime(response.getTransactiontime());
		tradeRequest.setState(TradeStatus.I.getValue());
		return tradeRequest;
	}
	
	/**
	 * 生成TradeRequest对象 - for 普通赎回
	 * @param vo
	 * @return
	 */
	public TradeRequest toTradeRequest4Redeem(Redeem vo){
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(vo.getSerialno());
		tradeRequest.setCustno(vo.getCustno());
		tradeRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeRequest.setTradeacco(vo.getTradeacco());
		tradeRequest.setAppdate(vo.getAppdate());
		tradeRequest.setApptime(vo.getApptime());
		tradeRequest.setWorkday(vo.getWorkday());
		tradeRequest.setApkind(Apkind.REDEEM.getValue());
		tradeRequest.setFundcode(vo.getFundcode());
		tradeRequest.setAppamt(vo.getAppamt());
		tradeRequest.setAppvol(vo.getAppvol());
		tradeRequest.setShareclass(vo.getShareclass());
		tradeRequest.setDividmethod(vo.getDividmethod());
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno(vo.getReferno());
		return tradeRequest;
	}
	
	/**
	 * 生成TradeQutyChg对象 - for 普通赎回
	 * @param vo
	 * @return
	 */
	public TradeQutyChg toTradeQutyChg4Redeem(Redeem vo){
		TradeQutyChg tradeQutyChg = new TradeQutyChg();
		tradeQutyChg.setSerialno(vo.getSerialno());
		tradeQutyChg.setCustno(vo.getCustno());
		tradeQutyChg.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeQutyChg.setTradeacco(vo.getTradeacco());
		tradeQutyChg.setApkind(Apkind.REDEEM.getValue());
		tradeQutyChg.setAppdate(vo.getAppdate());
		tradeQutyChg.setWorkdate(vo.getWorkday());
		tradeQutyChg.setFundcode(vo.getFundcode());
		tradeQutyChg.setTotal(BigDecimal.ZERO);
		tradeQutyChg.setAvailable(BigDecimal.ZERO.subtract(vo.getAppvol()));
		tradeQutyChg.setFrozen(vo.getAppvol());
		tradeQutyChg.setOldserialno(vo.getSerialno());
		return tradeQutyChg;
	}
	
	/**
	 * 生成RedeemRequest请求对象
	 * @param vo
	 * @return
	 */
	public RedeemRequest toRedeemRequest(Redeem vo){
		RedeemRequest request = new RedeemRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);

		if("1".equals(vo.getLevel())){
			request.setBusinType(Constant.HftBusiType.RedeemOrg);
		}else{
			request.setBusinType(Constant.HftBusiType.Redeem);
		}
		
		request.setApplicationNo(vo.getSerialno());
		request.setTransactionAccountID(vo.getTradeacco());
		request.setFundCode(vo.getFundcode());
		request.setApplicationVol(vo.getAppvol());
		request.setShareClass(vo.getShareclass());
		return request;
	}
	
	/**
	 * 生成回写响应对象 - for 普通赎回
	 * @param response
	 * @return
	 */
	public TradeRequest toResponse4Redeem(RedeemResponse response){
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(response.getApplicationNo());
		tradeRequest.setSheetserialno(response.getAppSheetSerialNo());
//		tradeRequest.setAppdate(response.getTransactionDate());
//		tradeRequest.setApptime(response.getTransactiontime());
		tradeRequest.setState(TradeStatus.I.getValue());
		return tradeRequest;
	}
	
	
	/**
	 * 生成TradeRequest对象 - for 快速赎回
	 * @param vo
	 * @return
	 */
	public TradeRequest toTradeRequest4RealRedeem(Redeem vo){
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(vo.getSerialno());
		tradeRequest.setCustno(vo.getCustno());
		tradeRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeRequest.setTradeacco(vo.getTradeacco());
		tradeRequest.setAppdate(vo.getAppdate());
		tradeRequest.setApptime(vo.getApptime());
		tradeRequest.setWorkday(vo.getWorkday());
		tradeRequest.setApkind(Apkind.REALREDEEM.getValue());
		tradeRequest.setFundcode(vo.getFundcode());
		tradeRequest.setAppamt(vo.getAppamt());
		tradeRequest.setAppvol(vo.getAppvol());
		tradeRequest.setShareclass(vo.getShareclass());
		tradeRequest.setDividmethod(vo.getDividmethod());
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno(vo.getReferno());
		return tradeRequest;
	}
	
	/**
	 * 生成TradeQutyChg对象 - for 快速赎回
	 * @param vo
	 * @return
	 */
	public TradeQutyChg toTradeQutyChg4RealRedeem(Redeem vo){
		TradeQutyChg tradeQutyChg = new TradeQutyChg();
		tradeQutyChg.setSerialno(vo.getSerialno());
		tradeQutyChg.setCustno(vo.getCustno());
		tradeQutyChg.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeQutyChg.setTradeacco(vo.getTradeacco());
		tradeQutyChg.setApkind(Apkind.REALREDEEM.getValue());
		tradeQutyChg.setAppdate(vo.getAppdate());
		tradeQutyChg.setWorkdate(vo.getWorkday());
		tradeQutyChg.setFundcode(vo.getFundcode());
		tradeQutyChg.setTotal(BigDecimal.ZERO.subtract(vo.getAppvol()));
		tradeQutyChg.setAvailable(BigDecimal.ZERO.subtract(vo.getAppvol()));
		tradeQutyChg.setFrozen(BigDecimal.ZERO);
		tradeQutyChg.setOldserialno(vo.getSerialno());
		return tradeQutyChg;
	}
	
	/**
	 * 生成RealRedeemRequest请求对象
	 * @param vo
	 * @return
	 */
	public RealRedeemRequest toRealRedeemRequest(Redeem vo){
		RealRedeemRequest request = new RealRedeemRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		
		if("1".equals(vo.getLevel())){
			request.setBusinType(Constant.HftBusiType.RealRedeemOrg);
		}else{
			request.setBusinType(Constant.HftBusiType.RealRedeem);
		}
		
		request.setApplicationNo(vo.getSerialno());
		request.setTransactionAccountID(vo.getTradeacco());
		request.setFundCode(vo.getFundcode());
		request.setApplicationVol(vo.getAppvol());
		request.setShareClass(vo.getShareclass());
		return request;
	}
	
	/**
	 * 生成回写响应对象 - for 快速赎回
	 * @param response
	 * @return
	 */
	public TradeRequest toResponse4RealRedeem(RealRedeemResponse response){
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(response.getApplicationNo());
		tradeRequest.setSheetserialno(response.getAppSheetSerialNo());
//		tradeRequest.setAppdate(response.getTransactionDate());
//		tradeRequest.setApptime(response.getTransactiontime());
		tradeRequest.setState(TradeStatus.I.getValue());
		return tradeRequest;
	}
	
	
	/**
	 * 生成CancelRequest对象 - for 撤单
	 * @param vo
	 * @return
	 */
	public CancelRequest toCancelRequest4Cancel(Cancel vo){
		CancelRequest cancelRequest = new CancelRequest();
		
//		cancelRequest.setVersion(Constant.HftSysConfig.Version);
//		cancelRequest.setMerchantId(Constant.HftSysConfig.MerchantId);
//		cancelRequest.setDistributorCode(Constant.HftSysConfig.DistributorCode);
//		cancelRequest.setBusinType(Constant.HftBusiType.Cancel);
//		cancelRequest.setApplicationNo(vo.getSerialno());
//		cancelRequest.setTransactionAccountID(vo.getTradeacco());
//		cancelRequest.setOriginalAppSheetNo(vo.getOldserialno());
		
		cancelRequest.setSerialno(vo.getSerialno());
		cancelRequest.setOldserialno(vo.getOldserialno());
		cancelRequest.setCustno(vo.getCustno());
		cancelRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		cancelRequest.setTradeacco(vo.getTradeacco());
		cancelRequest.setApkind(Apkind.CANCEL.getValue());
		cancelRequest.setFundcode(vo.getFundcode());
		cancelRequest.setOfundcode(null);
		cancelRequest.setSubamt(null);
		cancelRequest.setSubquty(null);
		cancelRequest.setPayst(null);
		cancelRequest.setPaytype(null);
		cancelRequest.setCanceldt(vo.getCanceldt());
		cancelRequest.setCanceltm(vo.getCanceltm());
		cancelRequest.setCancelst("N");
		return cancelRequest;
	}
	
	/**
	 * 生成CancelRequest对象 - for 撤单
	 * @param vo
	 * @return
	 */
	public com.ufufund.ufb.model.hftfund.CancelRequest toCancelRequest(Cancel vo){
		com.ufufund.ufb.model.hftfund.CancelRequest cancelRequest = 
				new com.ufufund.ufb.model.hftfund.CancelRequest();
		
		cancelRequest.setVersion(Constant.HftSysConfig.Version);
		cancelRequest.setMerchantId(Constant.HftSysConfig.MerchantId);
		cancelRequest.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		cancelRequest.setBusinType(Constant.HftBusiType.Cancel);
		cancelRequest.setApplicationNo(vo.getSerialno());
		cancelRequest.setTransactionAccountID(vo.getTradeacco());
		cancelRequest.setOriginalAppSheetNo(vo.getOldserialno());
		
		return cancelRequest;
	}
	
	/**
	 * 生成TradeRequest对象 - for 撤单
	 * @param vo
	 * @return
	 */
	public TradeRequest toTradeRequest4Cancel(Cancel vo){
		TradeRequest tradeRequest = new TradeRequest();
		tradeRequest.setSerialno(vo.getSerialno());
		tradeRequest.setCustno(vo.getCustno());
		tradeRequest.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeRequest.setTradeacco(vo.getTradeacco());
		tradeRequest.setAppdate(vo.getAppdate());
		tradeRequest.setApptime(vo.getApptime());
		tradeRequest.setWorkday(vo.getWorkday());
		tradeRequest.setApkind(Apkind.CANCEL.getValue());
		tradeRequest.setFundcode(vo.getFundcode());
		tradeRequest.setAppamt(vo.getAppamt());
		tradeRequest.setAppvol(vo.getAppvol());
		tradeRequest.setShareclass(vo.getShareclass());
		tradeRequest.setDividmethod(vo.getDividmethod());
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno(vo.getReferno());
		return tradeRequest;
	}
	
	/**
	 * 生成TradeQutyChg对象 - for 撤单
	 * @param vo
	 * @return
	 */
	public TradeQutyChg toTradeQutyChg4Cancel(Cancel vo){
		TradeQutyChg tradeQutyChg = new TradeQutyChg();
		tradeQutyChg.setSerialno(vo.getSerialno());
		tradeQutyChg.setCustno(vo.getCustno());
		tradeQutyChg.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		tradeQutyChg.setTradeacco(vo.getTradeacco());
		tradeQutyChg.setApkind(Apkind.REALREDEEM.getValue());
		tradeQutyChg.setAppdate(vo.getAppdate());
		tradeQutyChg.setWorkdate(vo.getWorkday());
		tradeQutyChg.setFundcode(vo.getFundcode());
		tradeQutyChg.setTotal(BigDecimal.ZERO.subtract(vo.getAppvol()));
		tradeQutyChg.setAvailable(BigDecimal.ZERO.subtract(vo.getAppvol()));
		tradeQutyChg.setFrozen(BigDecimal.ZERO);
		//tradeQutyChg.setOldserialno(vo.getSerialno());
		tradeQutyChg.setOldserialno(vo.getOldserialno());
		return tradeQutyChg;
	}
	
	/**
	 * 生成回写响应对象 - for 撤单
	 * @param response
	 * @return
	 */
	public CancelRequest toResponse4Cancel(CancelResponse response){
		CancelRequest cancelRequest = new CancelRequest();
		cancelRequest.setSerialno(response.getApplicationNo());
		cancelRequest.setSheetserialno(response.getAppSheetSerialNo());
//		tradeRequest.setAppdate(response.getTransactionDate());
//		tradeRequest.setApptime(response.getTransactiontime());
		cancelRequest.setCancelst(TradeStatus.I.getValue());
		return cancelRequest;
	}
}
