package com.ufufund.ufb.biz.util;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.SysErrorCode;
import com.ufufund.ufb.common.exception.SysException;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.model.hftfund.AbstractResponse;

/**
 * 海富通交易返回异常码统一处理类
 * @author ayis
 * 2015年4月25日
 */
public class HftResponseUtil {

	/**
	 * 处理交易返回异常码<br/>
	 * 根据错误码类型抛出Sys或者User类异常
	 * @param response
	 */
	public static void dealResponseCode(AbstractResponse response){
		if(response == null){
			throw new SysException(SysErrorCode.SYS_TRADE_FAILED);
		}else if(!Constant.RES_CODE_SUCCESS.equals(response.getReturnCode())){
			throw new UserException(response.getReturnCode(), response.getReturnMsg());
		}
	}
}
