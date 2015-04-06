package com.ufufund.ufb.biz.exception;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ufufund.ufb.biz.manager.DictManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.db.Dictionary;
import com.ufufund.ufb.model.enums.ErrorInfo;

/**
 * 应用系统异常，应用自身处理
 */

public class AppException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger("AppException");

	private String processId="";
	private String errmsg;
	private ErrorInfo errorInfo;
	private String otherInfo="";
	
	public AppException(String errmsg){
		this.errmsg = errmsg;
	}
	
	public AppException(String processId,ErrorInfo errorInfo){
		this.processId =processId;
		this.errorInfo = errorInfo;
		this.initErrmsg();	
	}
	
	public AppException(String processId,ErrorInfo errorInfo,String otherInfo){
		this.processId =processId;
		this.errorInfo = errorInfo;
		this.otherInfo = otherInfo;
		this.initErrmsg();	
	}
	

	public String getErrmsg() {
		return errmsg;
	}
	public String getErrcode() {
		return errorInfo.value();
	}
	
	/**
	 * 重写Exception类的getMessage方法，
	 * 使能在日志中打印出异常内容
	 * @author ayis
	 */
	@Override
	public String getMessage(){
		return errmsg;
	}

	private void initErrmsg() {
		HashMap<String,Dictionary>  map = DictManager.getDictionaryByType(Constant.DICTIONARY$ERROR);
		Dictionary dictionary = map.get(errorInfo.value());
		if(dictionary!=null){
			errmsg = otherInfo + dictionary.getPmnm();
		}else{
			errmsg = errorInfo.value();
		}
		log.debug(processId + " errcode :" + errorInfo.value()+" errmsg :" + errmsg);
	}

	
	
//
//	public void setErrcode(String errcode) {
//		this.errcode = errcode;
//	}
//	
//	@Override
//	public String toString(){
//		return this.errcode +"-"+this.errmsg;
//	}

}
