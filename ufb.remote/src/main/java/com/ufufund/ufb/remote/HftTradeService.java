package com.ufufund.ufb.remote;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.model.hft.BuyApplyRequest;
import com.ufufund.ufb.model.hft.BuyApplyResponse;
import com.ufufund.ufb.model.hft.NotifyPaidRequest;
import com.ufufund.ufb.model.hft.NotifyPaidResponse;
import com.ufufund.ufb.model.hft.CancelRequest;
import com.ufufund.ufb.model.hft.CancelResponse;
import com.ufufund.ufb.model.hft.FrozenRequest;
import com.ufufund.ufb.model.hft.FrozenResponse;
import com.ufufund.ufb.model.hft.RealRedeemRequest;
import com.ufufund.ufb.model.hft.RealRedeemResponse;
import com.ufufund.ufb.model.hft.RedeemRequest;
import com.ufufund.ufb.model.hft.RedeemResponse;
import com.ufufund.ufb.model.hft.SubApplyRequest;
import com.ufufund.ufb.model.hft.SubApplyResponse;
import com.ufufund.ufb.model.hft.TransferRequest;
import com.ufufund.ufb.model.hft.TransferResponse;
import com.ufufund.ufb.model.hft.UnFrozenRequest;
import com.ufufund.ufb.model.hft.UnFrozenResponse;

/**
 * 海富通交易相关接口
 * @author ayis
 * 2015年3月22日
 */
//@Service
public class HftTradeService extends HftBaseService{

	/**
	 * 认购
	 * @param request
	 * @return
	 */
	public SubApplyResponse subApply(SubApplyRequest request){
		return super.send(request, SubApplyResponse.class);
	}
	
	/**
	 * 申购
	 * @param request
	 * @return
	 */
	public BuyApplyResponse buyApply(BuyApplyRequest request){
		return super.send(request, BuyApplyResponse.class);
	}
	
	/**
	 * 普通赎回
	 * @param request
	 * @return
	 */
	public RedeemResponse redeem(RedeemRequest request){
		return super.send(request, RedeemResponse.class);
	}
	
	/**
	 * 快速赎回
	 * @param request
	 * @return
	 */
	public RealRedeemResponse realRedeem(RealRedeemRequest request){
		return super.send(request, RealRedeemResponse.class);
	}
	
	/**
	 * 撤单
	 * @param request
	 * @return
	 */
	public CancelResponse cancel(CancelRequest request){
		return super.send(request, CancelResponse.class);
	}
	
	/**
	 * 冻结
	 * @param request
	 * @return
	 */
	public FrozenResponse frozen(FrozenRequest request){
		return super.send(request, FrozenResponse.class);
	}
	
	/**
	 * 解冻
	 * @param request
	 * @return
	 */
	public UnFrozenResponse unfrozen(UnFrozenRequest request){
		return super.send(request, UnFrozenResponse.class);
	}
	
	/**
	 * 快速过户
	 * @param request
	 * @return
	 */
	public TransferResponse transfer(TransferRequest request){
		return super.send(request, TransferResponse.class);
	}
}
