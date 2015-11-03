package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.BankCardbin;

public interface BankBaseMapper{
	
	public List<BankBaseInfo> getBankBaseInfo(BankBaseInfo bankBaseInfo);
	
	public BankCardbin getBankCardbin(String bin);
	
	public String getLevelByBankno(String bankno);
}
