package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.BankCardWithTradeAcco;

public interface TradeAccoinfoMapper {

	public List<BankCardWithTradeAcco> getTradeAccoList(@Param("custno")String custno, @Param("fundcorpno")String fundcorpno);
}
