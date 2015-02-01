package com.ufufund.trade.ufb.biz.manager;

import com.ufufund.trade.ufb.service.CustInterface;

public interface CustManager extends CustInterface{
	
	public int updateLocationByCustNo(String custNo, String location);

}
