package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.CancelRequest;

public interface CancelRequestMapper {
	
	public CancelRequest getByOldSerialno(@Param("custno")String custno, @Param("oldserialno")String oldserialno);

	public int add(CancelRequest tradeRequest);
	
	public int update(CancelRequest tradeRequest);
	
//	public List<CancelRequest> qryTradeList(
//			@Param("custno")String custno, 
//			@Param("apkinds") List<String> apkinds,
//			@Param("states") List<String> states,
//			@Param("startappdt")String startappdt, 
//			@Param("endappdt")String endappdt,
//			@Param("start")int start, 
//			@Param("end")int end);
}
