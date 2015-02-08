package com.ufufund.manager;

import com.ufufund.action.BuyApply;
import com.ufufund.action.BuyApplyNotify;
import com.ufufund.action.CancelApply;
import com.ufufund.action.RedeemApply;
import com.ufufund.action.SetupDividend;
import com.ufufund.action.SwiftTransfer;
import com.ufufund.action.SynRiskEval;
import com.ufufund.action.UnfrozenShare;

public interface TradeManager {
	
	/**
	 * 申购,认购申请
	 * @param BuyApply
	 * @return 
	 */
	public void buyApply(BuyApply buyApply) throws Exception;
	
	/**
	 * 支付通知
	 * @param BuyApplyNotify
	 * @return 
	 */
	public void buyApplyNotify(BuyApplyNotify buyApplyNotify) throws Exception;
	
	/**
	 * 撤单交易申请
	 * @param CancelApply
	 * @return 
	 */
	public void cancelApply(CancelApply cancelApply) throws Exception;
	
	/**
	 * 赎回申请
	 * @param RedeemApply
	 * @return 
	 */
	public void redeemApply(RedeemApply redeemApply) throws Exception;
	
	/**
	 * 修改分红方式
	 * @param SetupDividend
	 * @return 
	 */
	public void setupDividend(SetupDividend setupDividend) throws Exception;
	
	/**
	 * 风险测试结果同步
	 * @param SynRiskEval
	 * @return 
	 */
	public void synRiskEval(SynRiskEval synRiskEval) throws Exception;
	
	
	/**
	 * 快速过户
	 * @param SwiftTransfer
	 * @return 
	 */
	public void swiftTransfer(SwiftTransfer swiftTransfer) throws Exception;
	
	
	
	/**
	 * 解冻
	 * @param UnfrozenShare
	 * @return 
	 */
	public void unfrozenShare(UnfrozenShare unfrozenShare) throws Exception;
	
}
