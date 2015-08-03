package com.ufufund.ufb.remote.hftfund;

import org.springframework.stereotype.Service;

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
