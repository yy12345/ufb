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
	 * @return 短信回执报告id；若失败，则为<code>null</code>
	 */
	public String sendMessage(String mobile, String content){
		
		String msgid = null;
		String status = null;
		String result = null;
		try {
			result = HttpSender.batchSend(uri, account, password,
					mobile, content, true, null, null);
			
			String[] r = result.split("\\n");
			if(r != null && r.length > 0){
				status = r[0].split(",")[1];
				if("0".equals(status) && r.length > 1){
					msgid = r[1];
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SysException("短信发送异常！");
		}
		log.info("msgsend:mobile="+mobile+",status="+status+",msgid="+msgid+",content="+content);
		return msgid;
	}
	
}
