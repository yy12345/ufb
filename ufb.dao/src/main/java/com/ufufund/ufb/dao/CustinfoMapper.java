package com.ufufund.ufb.dao;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.Custinfo;


public interface CustinfoMapper extends BaseDao {
	
	
	/**
	 * 查询手机号是否注册
	 * @param CustinfoAction custinfoAction
	 * @return 
	 * 
	 * rownum  = 1;
	 */
	public Custinfo getCustinfo(Custinfo custinfo);
	
	/*
	 * 不使用  ，使用CustManager insterCustinfo方法
	 */
	public void insterCustinfo(Custinfo custinfo);
	
	
	public void updateCustinfo(Custinfo custinfo);
	
	public String  getCustinfoSequence();
	
}
