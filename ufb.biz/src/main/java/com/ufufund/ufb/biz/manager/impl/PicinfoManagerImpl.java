package com.ufufund.ufb.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.PicinfoManager;
import com.ufufund.ufb.dao.PicinfoMapper;
import com.ufufund.ufb.model.db.Picinfo;

@Service
public class PicinfoManagerImpl implements PicinfoManager{

	@Autowired
	private PicinfoMapper picinfoMapper;
	
	@Override
	public int addPicinfo(Picinfo picinfo) {
		return picinfoMapper.addPicinfo(picinfo);
	}

}
