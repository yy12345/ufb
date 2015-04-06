package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Tradeaccoinfo;

public interface BankMapper extends BaseDao {

	public void insterBankcardinfo(Bankcardinfo bankcardinfo);
	
	public void insterTradeaccoinfo(Tradeaccoinfo tradeaccoinfo);
	
	public List<Bankcardinfo> getBankcardinfo(String custno);
	
	public String  getBankcardinfoSequence();
}
