package com.ufufund.ufb.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RefreshHolderFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String value = req.getParameter(RefreshHolder.INPUT_NAME);
		if(value != null && RefreshHolder.isRefresh(value)){
			req.setAttribute("A", "1234");
			req.getRequestDispatcher("/expire.html").forward(request, response);
			return;
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
