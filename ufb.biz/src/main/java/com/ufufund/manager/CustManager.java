package com.ufufund.manager;

import com.ufufund.action.OpenAccount;

public interface CustManager {
	
	
	/**
	 *  开户
	 * @param OpenAccount
	 * @return 
	 */
	public void openAccount(OpenAccount openAccount) throws Exception;
	
}
