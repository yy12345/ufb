package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.FamilyCodes;

public interface FamilyCodesMapper {

	FamilyCodes get(FamilyCodes familyCodes);
	
	int useFamilyCodes(String code);
}
