package com.ufufund.ufb.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.util.UserHelper;

public class RefreshHolderFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		if(uri.indexOf("ufb/css") > -1 || 
				uri.indexOf("ufb/css/") > -1 || 
				uri.indexOf("ufb/js/") > -1 || 
				uri.indexOf("ufb/images/") > -1 ){
			filterChain.doFilter(request, response); // 不做处理
		}else{
			if(uri.indexOf("ufb/expire") > -1){
				req.setAttribute("SessionVo", UserHelper.getCustinfoVo()); // 警告页
			}
			String input = req.getParameter(RefreshHolder.INPUT_NAME);
			if(input != null && RefreshHolder.isRefresh(input)){
				req.getRequestDispatcher("/expire.html").forward(request, response); // 重复刷新跳转
				return;
			}
			filterChain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
