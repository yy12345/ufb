package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;

public interface TradeNotesMapper{
	
	public void insterChangerecordinfo(Changerecordinfo changerecordinfo);
	
	public void insterFdacfinalresult(Fdacfinalresult fdacfinalresult);
	
//	public String getFdacfinalresultSeq();
	
//	public DateInfo getDateInfo();
	
//	public String getAccoreqSerialSeq();
}
