package com.ufufund.ufb.dao;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.model.Changerecordinfo;
import com.ufufund.ufb.model.model.Custinfo;
import com.ufufund.ufb.model.model.DateInfo;
import com.ufufund.ufb.model.model.Fdacfinalresult;


public interface TradeNotesMapper extends BaseDao {
	
	public void insterChangerecordinfo(Changerecordinfo changerecordinfo);
	
	public void insterFdacfinalresult(Fdacfinalresult fdacfinalresult);
	
	
	public String getFdacfinalresultSeq();
	
	public DateInfo getDateInfo();
	
}
