package com.ufufund.ufb.biz.Merchant;

import com.ufufund.ufb.biz.manager.DictManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.db.Dictionary;


public abstract class MerchantFund {
	
	protected static boolean isTest = false;
    // DictManager.getDict("DICTIONARY$HTFERROR", bankAuthResponse.getReturnCode());
	
	
	private static boolean getTest(){
		Dictionary dictionary = DictManager.getDict(Constant.DICTIONARY$SYS, Constant.TEST);
		if(dictionary!=null&&"NO".equals(dictionary.getPmnm())){
			return false;
		}
		return true;
	}
	/*
	 * 3.1.1	银行快捷鉴权
	 */
	public abstract OpenAccount bankAuth(Object obj);
	
	/*
	 * 3.1.2	银行快捷验证
	 */
	public abstract OpenAccount bankVeri(Object obj);
	
	/*
	 * 3.1.3	开户
	 */
	public abstract OpenAccount openAccount(Object obj);
}