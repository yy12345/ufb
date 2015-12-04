package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.OrgTerm;

public interface OrgTermMapper {
	
	void addOrgTerm(OrgTerm orgTerm);
	
	List<OrgTerm> getOrgTerm(OrgTerm orgTerm);
	
	int updateOrgTerm(OrgTerm orgTerm);

}
