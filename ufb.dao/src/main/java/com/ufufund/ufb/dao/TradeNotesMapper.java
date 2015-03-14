package com.ufufund.ufb.dao;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.DateInfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;


public interface TradeNotesMapper extends BaseDao {
	
	public void insterChangerecordinfo(Changerecordinfo changerecordinfo);
	
	public void insterFdacfinalresult(Fdacfinalresult fdacfinalresult);
	
	
	public String getFdacfinalresultSeq();
	
	public DateInfo getDateInfo();
	
}
