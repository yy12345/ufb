package com.ufufund.ufb.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.util.UserHelper;

/**
 * 登录拦截器
 * @author ayis
 * 2015年4月27日
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	
	/**
	 * 预处理用户是否已登录
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
		CustinfoVo custinfoVo = UserHelper.getCustinfoVo();
		if(custinfoVo == null || StringUtils.isBlank(custinfoVo.getCustno())){
			response.sendRedirect("/ufb/account/unlogin.htm");
			return false;
		}
		
		return true;
	}
}
