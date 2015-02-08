package com.ufufund.manager;



import com.ufufund.action.BankPageAuth;
import com.ufufund.action.BankPageVerify;
import com.ufufund.action.BankSwiftAuth;
import com.ufufund.action.BankSwiftVerify;

public interface  BankManager {
	
	/**
	 * 银行页面鉴权
	 * @param BankPageAuth
	 * @return 
	 */
	public void bankPageAuth(BankPageAuth bankPageAuth) throws Exception;
	
	/**
	 *  银行页面验证
	 * @param BankPageVerify
	 * @return 
	 */
	public void bankPageVerify(BankPageVerify bankPageVerify) throws Exception;

	/**
	 *  银行快捷鉴权
	 * @param BankSwiftAuth
	 * @return 
	 */
	public void bankSwiftAuth(BankSwiftAuth bankSwiftAuth) throws Exception;
	
	/**
	 *  银行快捷验证
	 * @param BankSwiftVerify
	 * @return 
	 */
	public void bankSwiftVerify(BankSwiftVerify bankSwiftVerify) throws Exception;
}
