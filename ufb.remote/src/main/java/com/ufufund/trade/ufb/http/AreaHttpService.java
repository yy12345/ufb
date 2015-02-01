package com.ufufund.trade.ufb.http;


import org.springframework.stereotype.Service;

import com.ufufund.trade.ufb.util.AreaUtils;

@Service
public class AreaHttpService {
	
	public String getAreaTime(String areaId){
		return AreaUtils.getSysTime();
	}
}
