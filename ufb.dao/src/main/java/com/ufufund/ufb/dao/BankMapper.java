package com.ufufund.ufb.dao;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Tradeaccoinfo;

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
