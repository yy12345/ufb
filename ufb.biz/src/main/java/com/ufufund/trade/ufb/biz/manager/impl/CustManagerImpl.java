package com.ufufund.trade.ufb.biz.manager.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.trade.ufb.biz.manager.CustManager;
import com.ufufund.trade.ufb.dao.AreaMapper;
import com.ufufund.trade.ufb.dao.CustinfoMapper;
import com.ufufund.trade.ufb.http.AreaHttpService;
import com.ufufund.trade.ufb.model.db.Area;
import com.ufufund.trade.ufb.model.db.Custinfo;

@Service
public class CustManagerImpl implements CustManager{
	
	private static final Logger log = LoggerFactory.getLogger(CustManagerImpl.class);
	
	@Autowired
	private CustinfoMapper custinfoMapper;
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Autowired
	private AreaHttpService areaHttpService;

	@Override
	public Area getAreaByCustNo(String custNo) {
		
		Custinfo custinfo = custinfoMapper.getByCustNo(custNo);
		Area area = areaMapper.getById(custinfo.getAreaId());
		
		String time = areaHttpService.getAreaTime(area.getId());
		log.info("time="+time+" area is:"+area);
		
		return area;
	}

	@Override
	public int updateLocationByCustNo(String custNo, String location) {
		
		Custinfo custinfo = custinfoMapper.getByCustNo(custNo);
		return areaMapper.updateLocationById(custinfo.getAreaId(), location);
	}

	

}
