package com.ufufund.ufb.dao;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.CancelRequest;

public interface CancelRequestMapper {
	
	public CancelRequest getByOldSerialno(@Param("custno")String custno, @Param("oldserialno")String oldserialno);

	public int add(CancelRequest tradeRequest);
	
	public int update(CancelRequest tradeRequest);
}
