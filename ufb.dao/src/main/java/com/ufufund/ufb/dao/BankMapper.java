package com.ufufund.ufb.dao;

import com.ufufund.common.BaseDao;
import com.ufufund.ufb.model.model.Bankcardinfo;
import com.ufufund.ufb.model.model.Custinfo;
import com.ufufund.ufb.model.model.Tradeaccoinfo;

public interface BankMapper extends BaseDao {
	/*
	 * 不使用  ，使用BankManager insterBankcardinfo方法
	 */
	public void insterBankcardinfo(Bankcardinfo bankcardinfo);
	
	
	/*
	 * 不使用  ，使用BankManager insterTradeaccoinfo方法
	 */
	public void insterTradeaccoinfo(Tradeaccoinfo tradeaccoinfo);
}
