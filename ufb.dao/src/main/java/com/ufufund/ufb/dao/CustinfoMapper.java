package com.ufufund.ufb.dao;

import org.apache.ibatis.annotations.Param;

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
	
	public Custinfo isTradePwdSet(Custinfo custinfo);
	
	public Custinfo isIdNoBindByTradeAcco(@Param("fundcorpno")String fundcorpno, @Param("invtp")String invtp, @Param("level")String level, @Param("idno")String idno);
	
	public String getCustinfoMapping(@Param("orgNo")String orgNo, @Param("oprNo")String oprNo);
	/*
	 * 不使用  ，使用CustManager insterCustinfo方法
	 */
	public void insertCustinfo(Custinfo custinfo);
	
	
	public void updateCustinfo(Custinfo custinfo);
	
	public String  getCustinfoSequence();
	
}
