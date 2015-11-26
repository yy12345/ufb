package com.ufufund.ufb.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.web.util.OrgUserHelper;

/**
 * 登录拦截器
 * @author ayis
 * 2015年4月27日
 */
public class OrgLoginInterceptor extends HandlerInterceptorAdapter{
	
	private String loginUrl;
	
	
	/**
	 * 预处理用户是否已登录
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
		Orginfo orginfo = OrgUserHelper.getOrginfo();
		if(orginfo == null || StringUtils.isBlank(orginfo.getOrgid())){
			response.sendRedirect(loginUrl);
			return false;
		}
		return true;
	}
	
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}
