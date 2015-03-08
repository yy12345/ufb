package com.ufufund.ufb.dao;

import org.apache.ibatis.annotations.Param;

import com.ufufund.common.BaseDao;
import com.ufufund.ufb.action.CustinfoAction;
import com.ufufund.ufb.model.model.Custinfo;

//public interface CustinfoMapper {
//	public int addCustinfo(Custinfo custinfo);
//	
//	public int updateAreaByCustNo(@Param("custNo")String custNo, @Param("areaId")String areaId);
//	
//	public Custinfo getByCustNo(String custNo);

public interface CustinfoMapper extends BaseDao {
	
	
	/**
	 * 查询手机号是否注册
	 * @param CustinfoAction custinfoAction
	 * @return 
	 * 
	 * rownum  = 1;
	 */
	public Custinfo getCustinfo(CustinfoAction custinfo);
	
	/*
	 * 不使用  ，使用CustManager insterCustinfo方法
	 */
	public void insterCustinfo(Custinfo custinfo);
	
	
	public void updateCustinfo(Custinfo custinfo);
	
}
