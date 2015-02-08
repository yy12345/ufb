package com.ufufund.daointerface;



import com.ufufund.common.BaseDao;
import com.ufufund.dataobject.CustinfoDO;


public interface CustinfoMapper extends BaseDao {

	
	/**
	 * add CustinfoDO
	 * 
	 * @param CustinfoDO
	 * @return
	 */
	public CustinfoDO queryCustinfoDO(CustinfoDO custinfoDO);
}
