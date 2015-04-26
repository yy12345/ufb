package com.ufufund.ufb.common.exception;

/**
 * 用户类异常，抛出异常，为了展示给用户相应提示
 * @author ayis
 * 2015年4月25日
 */
public class UserException extends SysException{
	private static final long serialVersionUID = 1L;

	public UserException(String msg){
		super(msg);
	}
	
	public UserException(String code, String msg){
		super(code, msg);
	}
	
	public UserException(UserErrorCode ec){
		super(ec.getCode(), ec.getMsg());
	}
}
