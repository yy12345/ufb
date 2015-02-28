package com.ufufund.ufb.dao;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.Custinfo;

public interface CustinfoMapper {
	
	public int addCustinfo(Custinfo custinfo);
	
	public int updateAreaByCustNo(@Param("custNo")String custNo, @Param("areaId")String areaId);
	
	public Custinfo getByCustNo(String custNo);
}
