package com.ufufund.manager;

import com.ufufund.action.StaitcshareQuery;
import com.ufufund.action.TransResultQuery;

public interface QueryManager {

	/**
	 * 份额查询
	 * @param StaitcshareQuery
	 * @return 
	 */
	public void staitcshareQuery(StaitcshareQuery staitcshareQuery) throws Exception;
	
	/**
	 * 交易结果查询
	 * @param TransResultQuery
	 * @return 
	 */
	public void transResultQuery(TransResultQuery transResultQuery) throws Exception;
}
