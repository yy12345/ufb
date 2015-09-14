package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.PicInfo;

public interface BankMapper extends BaseDao {

	public void insterBankcardinfo(Bankcardinfo bankcardinfo);

	public List<Bankcardinfo> getBankcardinfo(Bankcardinfo bankcardinfo);

	public void setBankCardMainFlag(@Param("custno")String custno, @Param("bankacco")String bankacco, @Param("mainflag")String mainflag);
	
	public void unbindBankCard(@Param("custno")String custno, @Param("serialid")String serialid, @Param("state")String state);
	
	//public String getBankcardinfoSequence();
	
	public void updatePicInfo(PicInfo picinfo);
	public void insertPicInfo(PicInfo picinfo);
	public PicInfo getPicInfo(PicInfo picinfo);
	
}
