package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.BankBaseInfo;

public interface BankBaseManager {

	/**
	 * 获取银行列表
	 * @param bankno
	 * @return
	 */
	public List<BankBaseInfo> getBankBaseInfoList(String bankno);
}
