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
	
}
