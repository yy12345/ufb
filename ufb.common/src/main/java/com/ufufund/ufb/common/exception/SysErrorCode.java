package com.ufufund.ufb.common.exception;

/**
 * 系统类异常定义 
 * @author ayis
 * 2015年4月25日
 */
public enum SysErrorCode {

	SYS_TRADE_FAILED("9990", "系统执行交易异常！"),
	SYS_LOCAL_FAILED("9991", "系统执行本地数据异常！");
	
	private String code;
	private String msg;
	
	private SysErrorCode(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public String getMsg(){
		return msg;
	}
	
	public String getCode(){
		return code;
	}
}
