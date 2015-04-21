package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.CancelVo;
import com.ufufund.ufb.model.vo.RedeemVo;

/**
 * 交易类manager接口定义
 * @author ayis
 * 2015年4月12日
 */
public interface TradeManager {

	/**
	 * 认购接口
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；业务类异常，直接通过BizException响应给用户
	 */
	public String subApply(ApplyVo vo);
	
	/**
	 * 申购接口
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；业务类异常，直接通过BizException响应给用户
	 */
	public String buyApply(ApplyVo vo);
	
	/**
	 * 普通赎回接口
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；业务类异常，直接通过BizException响应给用户
	 */
	public String redeem(RedeemVo vo);
	
	/**
	 * 快速赎回接口
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；业务类异常，直接通过BizException响应给用户
	 */
	public String realRedeem(RedeemVo vo);
	
	/**
	 * 撤单
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；业务类异常，直接通过BizException响应给用户
	 */
	public String cancel(CancelVo vo);
}
