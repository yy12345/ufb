package com.ufufund.exception;






@SuppressWarnings("serial")
public class BizException extends AppException{

	public BizException(String errmsg) {
		super(errmsg);
	}
	
	public BizException(String errcode,String errmsg) {
		super(errcode,errmsg);
	}
	public BizException(String[] s) {
		super(s[0],s[1]);
	}
//	public BizException(ParameterDO parameterDO) {
//		super(parameterDO.getPmco(),parameterDO.getPmnm());
//	}

//	public BizException(RespCode respCode){
//		super(respCode.getCode(),respCode.getMessage());
//	}
}
