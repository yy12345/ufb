package com.ufufund.ufb.web.util;

import org.apache.commons.lang.StringUtils;

import com.ufufund.ufb.common.exception.BizException;
import com.ufufund.ufb.web.filter.ServletHolder;

/**
 * 图形验证码工具类
 * @author ayis
 * 2015-03-14
 */
public class VerifyCodeUtils {

	public static boolean validate(String veriCode){
		
		String value = (String)ServletHolder.getSession().getAttribute("VerifyCode");
		if(StringUtils.isBlank(veriCode) || StringUtils.isBlank(value)){
			throw new BizException("验证码为空！");
		}else if(!veriCode.equals(value)){
			throw new BizException("验证码不匹配！");
		}
		return true;
	}
}
