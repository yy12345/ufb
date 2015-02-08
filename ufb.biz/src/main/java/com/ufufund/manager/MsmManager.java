package com.ufufund.manager;

import com.ufufund.action.SendMessage;

public interface MsmManager {
	
	/**
	 * 短信发送
	 * @param SendMessage
	 * @return 
	 */
	public void sendMessage(SendMessage sendMessage) throws Exception;
}
