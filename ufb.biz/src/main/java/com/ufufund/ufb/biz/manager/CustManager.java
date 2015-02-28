package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.service.CustInterface;

public interface CustManager extends CustInterface{
	
	public int updateLocationByCustNo(String custNo, String location);

}
