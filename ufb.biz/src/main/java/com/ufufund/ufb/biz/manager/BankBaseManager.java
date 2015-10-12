package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.BankCardbin;

public interface BankBaseManager {

	/**
	 * 获取银行列表
	 * @param bankno
	 * @return
	 */
	public List<BankBaseInfo> getBankBaseInfoList(String bankno);
	
	/**
	 * 获取银行卡bin对象
	 * @param bin
	 * @return
	 */
	public BankCardbin getBankCardbin(String bin);
}
