package com.ufufund.ufb.biz.exception;

import com.ufufund.ufb.model.enums.ErrorInfo;


/**
 * 业务类异常，可以展示给用户
 */

public class BizException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BizException(String errmsg) {
		super(errmsg);
	}
	
	public BizException(String processId,ErrorInfo errorInfo) {
		super(processId,errorInfo);
	}
	
	public BizException(String processId,ErrorInfo errorInfo,String otherInfo) {
		super(processId,errorInfo,otherInfo);
	}
	
//	public BizException(String errcode,String errmsg) {
//		super(errcode,errmsg);
//	}
	
}
