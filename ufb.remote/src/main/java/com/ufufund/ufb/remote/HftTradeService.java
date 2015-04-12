package com.ufufund.ufb.remote;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.model.remote.hft.BuyApplyRequest;
import com.ufufund.ufb.model.remote.hft.BuyApplyResponse;
import com.ufufund.ufb.model.remote.hft.BuyNotifyRequest;
import com.ufufund.ufb.model.remote.hft.BuyNotifyResponse;
import com.ufufund.ufb.model.remote.hft.CancelRequest;
import com.ufufund.ufb.model.remote.hft.CancelResponse;
import com.ufufund.ufb.model.remote.hft.FrozenRequest;
import com.ufufund.ufb.model.remote.hft.FrozenResponse;
import com.ufufund.ufb.model.remote.hft.RealRedeemRequest;
import com.ufufund.ufb.model.remote.hft.RealRedeemResponse;
import com.ufufund.ufb.model.remote.hft.RedeemRequest;
import com.ufufund.ufb.model.remote.hft.RedeemResponse;
import com.ufufund.ufb.model.remote.hft.SubApplyRequest;
import com.ufufund.ufb.model.remote.hft.SubApplyResponse;
import com.ufufund.ufb.model.remote.hft.TransferRequest;
import com.ufufund.ufb.model.remote.hft.TransferResponse;
import com.ufufund.ufb.model.remote.hft.UnFrozenRequest;
import com.ufufund.ufb.model.remote.hft.UnFrozenResponse;

/**
 * 海富通交易相关接口
 * @author ayis
 * 2015年3月22日
 */
@Service
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
	 * 认申购扣款时，支付通知
	 * @param request
	 * @return
	 */
	public BuyNotifyResponse buyNotify(BuyNotifyRequest request){
		return super.send(request, BuyNotifyResponse.class);
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
