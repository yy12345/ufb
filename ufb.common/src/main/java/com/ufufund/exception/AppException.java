package com.ufufund.exception;

/**
 * 应用系统异常，应用自身处理
 */
@SuppressWarnings("serial")
public class AppException extends RuntimeException{
	
	private String errmsg;
	
	private String errcode;
	
	public AppException(String errmsg){
		this.errcode = "9999";
		this.errmsg = errmsg;
	}
	
	public AppException(String errcode,String errmsg){
		this.errcode = errcode;
		this.errmsg = errmsg;
	}
	
	/**
	 * @param cause
	 */
	public AppException(Throwable cause){
		super(cause);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public AppException(String message,Throwable cause){
		super(message, cause);
	}
	
	@Override
	public String getMessage(){
		return this.errcode +"-"+this.errmsg;
	}
	
	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	
	@Override
	public String toString(){
		return this.errcode +"-"+this.errmsg;
	}

}
