package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.CancelVo;
import com.ufufund.ufb.model.vo.RedeemVo;


public interface TradeManager {

	/**
	 * 申购、认购接口
	 * @param vo
	 */
	public void apply(ApplyVo vo);
	
	/**
	 * 普通赎回、快速赎回接口
	 * @param vo
	 */
	public void redeem(RedeemVo vo);
	
	/**
	 * 撤单
	 * @param vo
	 */
	public void cancel(CancelVo vo);
}
