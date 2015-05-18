package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Tradeaccoinfo;

public interface BankMapper extends BaseDao {

	public void insterBankcardinfo(Bankcardinfo bankcardinfo);

	public void insterTradeaccoinfo(Tradeaccoinfo tradeaccoinfo);

	public List<Bankcardinfo> getBankcardinfo(Bankcardinfo bankcardinfo);

	public List<BankCardWithTradeAcco> getBankCardWithTradeAccoList(@Param("custno")String custno, @Param("state")String state);

	public void setBankCardMainFlag(@Param("custno")String custno, @Param("bankacco")String bankacco, @Param("mainflag")String mainflag);
	
	public void unbindBankCard(@Param("custno")String custno, @Param("bankacco")String bankacco, @Param("state")String state);
	
	public String getBankcardinfoSequence();
	
	public Tradeaccoinfo getTradeaccoinfo(Tradeaccoinfo tradeaccoinfo);
}
