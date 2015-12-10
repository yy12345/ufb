package com.ufufund.ufb.dao;

import java.util.List;
import com.ufufund.ufb.model.db.Bankcardinfo;

public interface BankCardInfoMapper{
	
	/**
	 * 插入银行卡信息
	 * @param bankcardinfo
	 */
	public void insterBankcardinfo(Bankcardinfo bankcardinfo);
	
	/**
	 * 查询银行卡信息
	 * @param bankcardinfo
	 */
	public List<Bankcardinfo> getBankcardinfo(Bankcardinfo bankcardinfo);
	
	/**
	 * 更新银行卡状态
	 * @param custno
	 */
	public void removeCard(String custno);
	
	/**
	 * 删除银行卡信息
	 * @param bankcardinfo
	 */
	public void deleteBankCard(String serialid);
}
