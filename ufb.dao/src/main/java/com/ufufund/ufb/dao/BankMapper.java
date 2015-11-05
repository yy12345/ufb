package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.PicInfo;

public interface BankMapper{

	public void insterBankcardinfo(Bankcardinfo bankcardinfo);

	public List<Bankcardinfo> getBankcardinfo(Bankcardinfo bankcardinfo);

	public void setBankCardMainFlag(@Param("custno")String custno, @Param("bankacco")String bankacco, @Param("mainflag")String mainflag);
	
	public void unbindBankCard(@Param("custno")String custno, @Param("serialid")String serialid, @Param("state")String state);
	public void deleteCard(@Param("custno")String custno, @Param("serialid")String serialid);
	public void deleteTradeacc(@Param("custno")String custno, @Param("serialid")String bankserialid);
	public void removeCard(String custno);
	public Bankcardinfo getBankCardInfo(String custno);
	//public String getBankcardinfoSequence();
	
	public void updatePicInfo(PicInfo picinfo);
	public void insertPicInfo(PicInfo picinfo);
	public PicInfo getPicInfo(PicInfo picinfo);
	
}
