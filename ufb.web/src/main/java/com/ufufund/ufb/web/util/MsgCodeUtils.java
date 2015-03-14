package com.ufufund.ufb.web.util;

import org.apache.commons.lang.StringUtils;

import com.ufufund.ufb.common.exception.BizException;
import com.ufufund.ufb.web.filter.ServletHolder;

/**
 * 短信验证码工具类
 * @author ayis
 * 2015-03-14
 */
public class MsgCodeUtils {

	/**
	 * 发送短信验证码
	 * @param template 短信模版
	 */
	public static void sendMsg(String template){
		
		// code later...
	}
	
	/**
	 * 取出session中的短信码进行校验
	 * @param msgCode
	 * @return 校验失败，直接提示业务类异常；否则，成功
	 */
	public static boolean validate(String msgCode){
		
		String value = (String)ServletHolder.getSession().getAttribute("MsgCode");
		if(StringUtils.isBlank(msgCode) || StringUtils.isBlank(value)){
			throw new BizException("短信码为空！");
		}else if(!msgCode.equals(value)){
			throw new BizException("短信码不匹配！");
		}
		return true;
	}
}
