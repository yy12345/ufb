package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.OrgCodes;

public interface OrgCodesMapper {

	int addOrgCodes(List<OrgCodes> orgCodesList);
	
	List<OrgCodes> getMoreOrgCodes(OrgCodes orgCodes);
	
	int updateOrgCodes(OrgCodes orgCodes);
}
