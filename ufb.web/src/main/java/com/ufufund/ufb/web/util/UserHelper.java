package com.ufufund.ufb.web.util;

import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;

public class UserHelper {

	/**
	 * 获取当前客户信息
	 * @return
	 */
	public static CustinfoVo getCustinfoVo(){
		return (CustinfoVo)ServletHolder.getSession().getAttribute("CUSTINFO");
	}
	
	/**
	 * 保存当前客户信息
	 * @return
	 */
	public static void saveCustinfoVo(CustinfoVo custinfoVo){
		ServletHolder.getSession().setAttribute("CUSTINFO", custinfoVo);
	}
	
	/**
	 * 获取当前客户编号
	 * @return 返回custno；若没有，则返回null
	 */
	public static String getCustno(){
//		if(true)return "CU201508165661EKDYBIBS22";
		CustinfoVo s_custinfo = (CustinfoVo)ServletHolder.getSession().getAttribute("S_CUSTINFO");
		
		if(s_custinfo != null && !StringUtils.isBlank(s_custinfo.getCustno())){
			return s_custinfo.getCustno();
		}
		return null;
	}
	
	/**
	 * 设置session属性
	 * @return
	 */
	public static void setSessionAttr(String name, String value){
		ServletHolder.getSession().setAttribute(name, value);
	}
	
	/**
	 * 获取session属性
	 * @return
	 */
	public static String getSessionAttr(String name){
		return (String)ServletHolder.getSession().getAttribute(name);
	}
	
}
