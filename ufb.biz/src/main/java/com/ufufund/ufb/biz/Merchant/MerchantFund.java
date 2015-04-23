package com.ufufund.ufb.biz.Merchant;



public abstract class MerchantFund {
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