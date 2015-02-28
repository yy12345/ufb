package com.ufufund.ufb.remote;


import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.AreaUtils;

@Service
public class AreaHttpService {
	
	public String getAreaTime(String areaId){
		return AreaUtils.getSysTime();
	}
}
