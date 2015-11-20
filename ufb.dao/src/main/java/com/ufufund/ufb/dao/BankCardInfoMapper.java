package com.ufufund.ufb.dao;

import java.util.List;
import com.ufufund.ufb.model.db.Bankcardinfo;

public interface BankCardInfoMapper{
	
	public void insterBankcardinfo(Bankcardinfo bankcardinfo);
	
	public List<Bankcardinfo> getBankcardinfo(Bankcardinfo bankcardinfo);
	
	public void removeCard(String custno);
}
