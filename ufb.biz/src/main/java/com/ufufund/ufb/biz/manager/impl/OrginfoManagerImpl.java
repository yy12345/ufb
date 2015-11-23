package com.ufufund.ufb.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.OrginfoManager;
import com.ufufund.ufb.dao.OrginfoMapper;
import com.ufufund.ufb.dao.PicinfoMapper;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.model.db.Picinfo;

@Service
public class OrginfoManagerImpl implements OrginfoManager{

	@Autowired
	private OrginfoMapper orginfoMapper;
	@Autowired
	private PicinfoMapper picinfoMapper;
	
	
	@Override
	public int addOrginfo(Orginfo orginfo) {
		return orginfoMapper.addOrginfo(orginfo);
	}
	
	@Override
	public int addPicinfo(Picinfo picinfo) {
		return picinfoMapper.addPicinfo(picinfo);
	}

}
