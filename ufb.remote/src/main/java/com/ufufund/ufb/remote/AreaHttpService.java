package com.ufufund.ufb.remote;


import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.utils.AreaUtil;

@Service
public class AreaHttpService {
	
	public String getAreaTime(String areaId){
		return AreaUtil.getSysTime();
	}
}
