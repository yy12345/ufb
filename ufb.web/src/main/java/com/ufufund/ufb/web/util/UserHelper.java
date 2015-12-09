package com.ufufund.ufb.web.util;

import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.web.filter.ServletHolder;

public class UserHelper {

	/**
	 * 获取当前客户信息
	 * @return
	 */
	public static Custinfo getCustinfo(){
		return (Custinfo)ServletHolder.getSession().getAttribute("CUSTINFO");
	}
	
	/**
	 * 保存当前客户信息
	 * @return
	 */
	public static void saveCustinfo(Custinfo custinfo){
		ServletHolder.getSession().setAttribute("CUSTINFO", custinfo);
	}
	
	/**
	 * 获取当前客户编号
	 * @return 返回custno；若没有，则返回null
	 */
	public static String getCustno(){
		Custinfo custinfo = (Custinfo)ServletHolder.getSession().getAttribute("CUSTINFO");
		if(custinfo != null && !StringUtils.isBlank(custinfo.getCustno())){
			return custinfo.getCustno();
		}
		return null;
	}
	
	/**
	 * 获取session属性
	 * @return
	 */
	public static Object getSessionAttr(String name){
		return ServletHolder.getSession().getAttribute(name);
	}
	
	/**
	 * 设置session属性
	 * @return
	 */
	public static void setSessionAttr(String name, Object value){
		ServletHolder.getSession().setAttribute(name, value);
	}	
	
	/**
	 * 移除session属性
	 * @return
	 */
	public static void removeSessionAttr(String name){
		ServletHolder.getSession().removeAttribute(name);
	}
	
}
