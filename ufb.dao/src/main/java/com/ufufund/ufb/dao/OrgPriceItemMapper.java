package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.OrgPriceItem;

public interface OrgPriceItemMapper {
	
	void addOrgPriceItem(OrgPriceItem orgPriceItem);
	
	List<OrgPriceItem> getOrgPriceItem(OrgPriceItem orgPriceItem);
	
	int updatePriceItem(String id);
	
	void deletePriceItem(String id);

}
