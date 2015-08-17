package com.ufufund.ufb.biz.manager;

public interface MobileMsgManager {

	/**
	 * 短信发送（一般场景使用）
	 * @param custno
	 * @param content
	 * @return 短信记录流水id
	 */
	public String sendMessage(String custno, String content);
	
	/**
	 * 短信发送（通过手机号发送，仅注册等场景使用）
	 * @param mobile
	 * @param content
	 * @return 短信记录流水id
	 */
	public String sendMessageByMobile(String mobile, String content);
}
