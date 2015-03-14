package com.ufufund.ufb.biz.exception;


/**
 * 业务类异常，可以展示给用户
 */
@SuppressWarnings("serial")
public class BizException extends AppException{

	public BizException(String errmsg) {
		super(errmsg);
	}
	
	public BizException(String processId,String errcode) {
		super(processId,errcode);
	}
	
	public BizException(String processId,String errcode,String otherInfo) {
		super(processId,errcode,otherInfo);
	}
	
//	public BizException(String errcode,String errmsg) {
//		super(errcode,errmsg);
//	}
	
}
