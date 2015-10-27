package com.ufufund.ufb.web.util;

import org.apache.commons.lang.StringUtils;

import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.web.filter.ServletHolder;

/**
 * 图形验证码工具类
 * @author ayis 
 * 2015-03-14
 */
public class VerifyCodeUtils {

	/**
	 * 验证图形验证码-后台使用
	 * 备注：验证成功后，会清除已使用验证码
	 * @param verifycode
	 */
	public static void validate(String verifycode) {
		
		check(verifycode);
		// 清除已使用的验证码
		ServletHolder.getSession().removeAttribute("VERIFYCODE");
	}
	
	/**
	 * 验证图形验证码-前端校验使用
	 * 备注：仅供前端异步交互调用，使用后不清楚验证码
	 * @param verifycode
	 */
	public static void check(String verifycode) {
		
		String value = (String) ServletHolder.getSession().getAttribute("VERIFYCODE");
		if (StringUtils.isBlank(verifycode)) {
			throw new UserException("输入验证码为空！");
		} else if (StringUtils.isBlank(value)) {
			throw new UserException("验证码已失效！");
		} else if (!verifycode.equalsIgnoreCase(value)) {
			throw new UserException("验证码不正确！");
		}
	}
}
