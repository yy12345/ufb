package com.ufufund.ufb.common.exception;

/**
 * 用户类异常定义
 * @author ayis
 * 2015年4月25日
 */
public enum UserErrorCode {

	USER_LOCAL_FAILED("0010", "您的申请已成功提交，系统处理中！");
	
	private String code;
	private String msg;
	
	private UserErrorCode(String code, String msg){
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
