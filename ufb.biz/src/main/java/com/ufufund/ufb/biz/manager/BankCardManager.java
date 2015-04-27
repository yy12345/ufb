package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.Bankcardinfo;

public interface BankCardManager {

	/**
	 * 获取用户绑定的银行卡列表
	 * @param custno
	 * @return
	 */
	public List<Bankcardinfo> getBankcardinfoList(String custno);
}
