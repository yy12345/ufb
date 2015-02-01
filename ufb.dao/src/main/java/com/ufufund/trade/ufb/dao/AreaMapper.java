package com.ufufund.trade.ufb.dao;

import org.apache.ibatis.annotations.Param;

import com.ufufund.trade.ufb.model.db.Area;

public interface AreaMapper {
	
	public int addArea(Area area);
	
	public int updateLocationById(@Param("id")String id, @Param("location")String location);
	
	public Area getById(String id);
}
