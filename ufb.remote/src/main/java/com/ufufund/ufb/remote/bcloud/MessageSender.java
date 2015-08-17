package com.ufufund.ufb.remote.bcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bcloud.msg.http.HttpSender;
import com.ufufund.ufb.common.exception.SysException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageSender {

	@Value("${ufb.bcloud.uri}")
	private String uri;
	@Value("${ufb.bcloud.account}")
	private String account;
	@Value("${ufb.bcloud.password}")
	private String password;
	
	/**
	 * 短信发送接口
	 * @param mobile 手机号
	 * @param content 短信内容
	 * @return 短信上送状态；上送失败，则直接抛出异常
	 */
	public String sendMessage(String mobile, String content){
		
		String status = null;
		String result = null;
		try {
			result = HttpSender.batchSend(uri, account, password,
					mobile, content, false, null, null);
			
			if(result != null && result.length() > 0){
				status = result.split(",")[1];
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SysException("短信发送异常！");
		}
		log.info("msgsend:mobile="+mobile+",status="+status+",content="+content);
		if(!"0".equals(status)){
			throw new SysException("短信发送失败！");
		}
		return status;
	}
	
}
