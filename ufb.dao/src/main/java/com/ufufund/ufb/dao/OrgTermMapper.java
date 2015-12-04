package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.OrgTerm;
import com.ufufund.ufb.model.db.OrgYear;

public interface OrgTermMapper {
	
	void addOrgTerm(OrgTerm orgTerm);
	
	List<OrgTerm> getOrgTerm(OrgTerm orgTerm);
	
	int updateOrgTerm(OrgTerm orgTerm);
	
	List<OrgYear> getOrgYearList();
	
	OrgYear getOrgYear(String y1);

}
