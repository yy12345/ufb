package com.ufufund.ufb.web.util;

import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.web.filter.ServletHolder;

public class OrgUserHelper {

	/**
	 * 获取当前客户信息
	 * @return
	 */
	public static Orginfo getOrginfo(){
		return (Orginfo)ServletHolder.getSession().getAttribute("ORGINFO");
	}
	
	/**
	 * 保存当前客户信息
	 * @return
	 */
	public static void saveOrginfo(Orginfo Orginfo){
		ServletHolder.getSession().setAttribute("ORGINFO", Orginfo);
	}
	
	/**
	 * 获取当前客户编号
	 * @return 返回custno；若没有，则返回null
	 */
	public static String getOrgid(){
		Orginfo orginfo = (Orginfo)ServletHolder.getSession().getAttribute("ORGINFO");
		if(orginfo != null && !StringUtils.isBlank(orginfo.getOrgid())){
			return orginfo.getOrgid();
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
