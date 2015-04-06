package com.ufufund.ufb.biz.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ufufund.ufb.common.utils.ThreadLocalUtil;
import com.ufufund.ufb.model.action.CommonAction;



public abstract class ImplCommon {
	
	protected final static String MOBILE = "手机号";
	protected final static String IDCARDNO = "身份证";
	protected final static String IDENTIFYING = "验证码";
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	
	protected String getProcessId(Object obj){
		String processId = "";
		if(obj instanceof String) {
			processId = ThreadLocalUtil.getProccessId();
		}else if(obj instanceof CommonAction){
			CommonAction commonAction = (CommonAction)obj;
			processId = commonAction.getProcessId();
		}
		String _methodName = new Exception().getStackTrace()[1].getMethodName();
		log.debug(processId + _methodName + " : " + obj.toString());
		//System.out.println("---"+processId + _methodName + " : " + obj.toString());
		return processId;
		
	}
}

	

