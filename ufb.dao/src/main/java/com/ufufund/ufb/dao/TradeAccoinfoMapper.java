package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.Tradeaccoinfo;

public interface TradeAccoinfoMapper {

	public Tradeaccoinfo getTradeaccoinfo(Tradeaccoinfo tradeaccoinfo);
	
	public void insterTradeaccoinfo(Tradeaccoinfo tradeaccoinfo);
	
	public String isTradeaccoinfoBind(OpenAccountAction openAccountAction);
	
	public List<TradeAccoinfoOfMore> getBankCardWithTradeAccoList();

	public List<TradeAccoinfoOfMore> getTradeAccoList(@Param("custno")String custno, @Param("state")String state);
	
	public TradeAccoinfoOfMore getTradeAcco(
			@Param("custno")String custno, 
			@Param("fundcorpno")String fundcorpno, 
			@Param("bankserialid")String bankserialid, 
			@Param("state")String state);
}
