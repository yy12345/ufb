package com.ufufund.trade.ufb.dao;

import org.apache.ibatis.annotations.Param;

import com.ufufund.trade.ufb.model.db.Custinfo;

public interface CustinfoMapper {
	
	public int addCustinfo(Custinfo custinfo);
	
	public int updateAreaByCustNo(@Param("custNo")String custNo, @Param("areaId")String areaId);
	
	public Custinfo getByCustNo(String custNo);
}
