package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.Orginfo;

public interface OrginfoMapper {
	
	int addOrginfo(Orginfo orginfo);
	
	Orginfo getOrginfo(Orginfo orginfo);
	
	Orginfo isCertnoRegister(Orginfo orginfo);
	
	int updateOrginfo(Orginfo orginfo);
	
	int updateForLogin(Orginfo orginfo);
	
	int updateState(Orginfo orginfo);
}
