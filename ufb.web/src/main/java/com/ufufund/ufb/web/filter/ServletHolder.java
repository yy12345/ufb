package com.ufufund.ufb.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * request、response线程持有类
 * @author ayis
 * 2015-03-14
 */
public class ServletHolder {

	private static ThreadLocal<HttpServletRequest> request = new ThreadLocal();
	private static ThreadLocal<HttpServletResponse> response = new ThreadLocal();
	
	
	public static HttpServletRequest getRequest() {
		return ServletHolder.request.get();
	}
	public static void setRequest(HttpServletRequest request) {
		ServletHolder.request.set(request);
	}
	public static HttpServletResponse getResponse() {
		return ServletHolder.response.get();
	}
	public static void setResponse(HttpServletResponse response) {
		ServletHolder.response.set(response);
	}
	
	public static HttpSession getSession(){
		return ServletHolder.request.get().getSession();
	}
	
}
