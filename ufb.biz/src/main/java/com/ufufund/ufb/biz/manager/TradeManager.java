package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.db.Apply;
import com.ufufund.ufb.model.db.Cancel;
import com.ufufund.ufb.model.db.Redeem;

/**
 * 交易类manager接口定义
 * @author ayis
 * 2015年4月12日
 */
public interface TradeManager {

	/**
	 * 认购接口
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；
	 */
	public String subApply(Apply vo);
	
	/**
	 * 申购接口
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；
	 */
	public String buyApply(Apply vo);
	
	/**
	 * 普通赎回接口
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；
	 */
	public String redeem(Redeem vo);
	
	/**
	 * 快速赎回接口
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；
	 */
	public String realRedeem(Redeem vo);
	
	/**
	 * 撤单
	 * @param vo
	 * @return 成功，返回申请流水号；否则，返回null；
	 */
	public String cancel(Cancel vo);
}
