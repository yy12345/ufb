package com.ufufund.ufb.biz.manager.impl.helper;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.TradeStatus;
import com.ufufund.ufb.model.remote.hft.BuyApplyRequest;
import com.ufufund.ufb.model.remote.hft.BuyApplyResponse;
import com.ufufund.ufb.model.remote.hft.RealRedeemRequest;
import com.ufufund.ufb.model.remote.hft.RealRedeemResponse;
import com.ufufund.ufb.model.remote.hft.RedeemRequest;
import com.ufufund.ufb.model.remote.hft.RedeemResponse;
import com.ufufund.ufb.model.remote.hft.SubApplyRequest;
import com.ufufund.ufb.model.remote.hft.SubApplyResponse;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.RedeemVo;

@Service
public class TradeManagerHelper {

	/**
	 * 生成TradeRequest对象 - for 认购
	 * @param vo
	 * @return
	 */
	public TradeRequest toTradeRequest4SubApply(ApplyVo vo){
		
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
		tradeRequest.setShareclass("0");
		tradeRequest.setDividenttype("0");
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno("");
		return tradeRequest;
	}
	
	/**
	 * 生成SubApplyRequest请求对象
	 * @param vo
	 * @return
	 */
	public SubApplyRequest toSubApplyRequest(ApplyVo vo){
		
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
		tradeRequest.setAppdate(response.getTransactionDate());
		tradeRequest.setApptime(response.getTransactiontime());
		tradeRequest.setState(TradeStatus.I.getValue());
		return tradeRequest;
	}
	
	
	
	/**
	 * 生成TradeRequest对象 - for 申购
	 * @param vo
	 * @return
	 */
	public TradeRequest toTradeRequest4BuyApply(ApplyVo vo){
		
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
		tradeRequest.setShareclass("0");
		tradeRequest.setDividenttype("0");
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno("");
		return tradeRequest;
	}
	
	/**
	 * 生成BuyApplyRequest请求对象
	 * @param vo
	 * @return
	 */
	public BuyApplyRequest toBuyApplyRequest(ApplyVo vo){
		
		BuyApplyRequest request = new BuyApplyRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BuyApply);
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
		tradeRequest.setAppdate(response.getTransactionDate());
		tradeRequest.setApptime(response.getTransactiontime());
		tradeRequest.setState(TradeStatus.I.getValue());
		return tradeRequest;
	}
	
	
	/**
	 * 生成TradeRequest对象 - for 普通赎回
	 * @param vo
	 * @return
	 */
	public TradeRequest toTradeRequest4Redeem(RedeemVo vo){
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
		tradeRequest.setShareclass("0");
		tradeRequest.setDividenttype("0");
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno("");
		return tradeRequest;
	}
	
	/**
	 * 生成RedeemRequest请求对象
	 * @param vo
	 * @return
	 */
	public RedeemRequest toRedeemRequest(RedeemVo vo){
		RedeemRequest request = new RedeemRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.Redeem);
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
		tradeRequest.setAppdate(response.getTransactionDate());
		tradeRequest.setApptime(response.getTransactiontime());
		tradeRequest.setState(TradeStatus.I.getValue());
		return tradeRequest;
	}
	
	
	/**
	 * 生成TradeRequest对象 - for 快速赎回
	 * @param vo
	 * @return
	 */
	public TradeRequest toTradeRequest4RealRedeem(RedeemVo vo){
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
		tradeRequest.setShareclass("0");
		tradeRequest.setDividenttype("0");
		tradeRequest.setFee(vo.getFee());
		tradeRequest.setReferno("");
		return tradeRequest;
	}
	
	/**
	 * 生成RealRedeemRequest请求对象
	 * @param vo
	 * @return
	 */
	public RealRedeemRequest toRealRedeemRequest(RedeemVo vo){
		RealRedeemRequest request = new RealRedeemRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.RealRedeem);
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
		tradeRequest.setAppdate(response.getTransactionDate());
		tradeRequest.setApptime(response.getTransactiontime());
		tradeRequest.setState(TradeStatus.I.getValue());
		return tradeRequest;
	}
	
	
	
}