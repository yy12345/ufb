package com.ufufund.ufb.web.util;

import org.apache.commons.lang.StringUtils;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.web.filter.ServletHolder;

/**
 * 图形验证码工具类
 * @author ayis
 * 2015-03-14
 */
public class VerifyCodeUtils {

	/**
	 * 图形验证码校验
	 * @param veriCode
	 * @return 校验失败，直接提示业务类异常；否则，成功
	 */
	public static boolean validate(String veriCode){
		
		String value = (String)ServletHolder.getSession().getAttribute("VERIFYCODE");
		
		if(StringUtils.isBlank(veriCode) || StringUtils.isBlank(value)){
			throw new BizException("验证码为空或已失效！");
		}else if(!veriCode.equals(value)){
			throw new BizException("验证码不匹配！");
		}
		
		ServletHolder.getSession().removeAttribute("VERIFYCODE");
		return true;
	}
}
