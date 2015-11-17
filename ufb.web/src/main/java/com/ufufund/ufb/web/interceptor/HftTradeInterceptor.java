package com.ufufund.ufb.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.web.util.UserHelper;

public class HftTradeInterceptor extends HandlerInterceptorAdapter{
	
	private String redirectUrl;
	
	@Autowired
	private TradeAccoManager tradeAccoManager;

	/**
	 * 进入ufb模块前拦截处理：判断是否有交易账户等
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
		String custno = UserHelper.getCustno();
		// 判断用户是否有正常的交易账号
		if(tradeAccoManager.getTradeAccoList(custno).size() == 0){
			response.sendRedirect(redirectUrl);
			return false;
		}
		return true;
	}
	
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
