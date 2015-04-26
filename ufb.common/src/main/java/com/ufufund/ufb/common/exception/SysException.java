package com.ufufund.ufb.common.exception;

/**
 * 系统类异常，不展示给用户
 * @author ayis
 * 2015年4月25日
 */
public class SysException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	public SysException(String msg){
		super(msg);
	}
	
	public SysException(String code, String msg){
		super(msg);
		this.code = code;
	}
	
	public SysException(SysErrorCode ec){
		super(ec.getMsg());
		this.code = ec.getCode();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
