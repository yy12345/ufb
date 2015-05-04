package com.ufufund.ufb.web.util;

import org.apache.commons.lang.StringUtils;

import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;

public class UserHelper {

	/**
	 * 获取当前客户信息
	 * @return
	 */
	public static CustinfoVo getCustinfoVo(){
		return (CustinfoVo)ServletHolder.getSession().getAttribute("S_CUSTINFO");
	}
	
	/**
	 * 保存当前客户信息
	 * @return
	 */
	public static void saveCustinfoVo(CustinfoVo custinfoVo){
		ServletHolder.getSession().setAttribute("S_CUSTINFO", custinfoVo);
	}
	
	/**
	 * 保存当前绑卡状态
	 * @return
	 */
	public static void setAddBankCardStatus(String status){
		ServletHolder.getSession().setAttribute("S_ADDBANKCARDSTATUS", status);
	}
	
	/**
	 * 获取当前绑卡状态
	 * @return
	 */
	public static String getAddBankCardStatus(){
		return (String)ServletHolder.getSession().getAttribute("S_ADDBANKCARDSTATUS");
	}
	
	/**
	 * 登出当前客户信息
	 * @return
	 */
	public static void removeCustinfoVo(){
		ServletHolder.getSession().removeAttribute("S_CUSTINFO");
	}
	
	/**
	 * 获取当前客户编号
	 * @return 返回custno；若没有，则返回null
	 */
	public static String getCustno(){
		CustinfoVo s_custinfo = (CustinfoVo)ServletHolder.getSession().getAttribute("S_CUSTINFO");
		
		if(s_custinfo != null && !StringUtils.isBlank(s_custinfo.getCustno())){
			return s_custinfo.getCustno();
		}
		return null;
	}
	
	/**
	 * 保存当前绑卡状态
	 * @return
	 */
	public static void setSessionAttr(String name, String value){
		ServletHolder.getSession().setAttribute(name, value);
	}
	
	/**
	 * 获取当前绑卡状态
	 * @return
	 */
	public static String getSessionAttr(String name){
		return (String)ServletHolder.getSession().getAttribute(name);
	}
	
}
