package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.FundNav;
import com.ufufund.ufb.model.db.TradeRequest;

public interface TradeRequestMapper {
	
	public TradeRequest getBySerialno(@Param("custno")String custno, @Param("serialno")String serialno);

	public int add(TradeRequest tradeRequest);
	
	public int update(TradeRequest tradeRequest);
	
	public List<TradeRequest> qryTradeList(
			@Param("custno")String custno, 
			@Param("apkinds") List<String> apkinds,
			@Param("states") List<String> states,
			@Param("startappdt")String startappdt, 
			@Param("endappdt")String endappdt,
			@Param("start")int start, 
			@Param("end")int end);
	
	public List<FundNav> qryFundNavList(FundNav fundnav);
}
