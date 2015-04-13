package com.ufufund.ufb.biz.common;

import java.util.Date;

import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.model.vo.Today;

public class UfbCommonUtil {

	/**
	 * 获取当前时间 <来源：应用服务器时间>
	 * @return
	 */
	public static Today getToday(){
		String time = DateUtil.format(new Date(), DateUtil.FULL_PATTERN_1);
		Today today = new Today();
		today.setDate(time.substring(0, 8));
		today.setTime(time.substring(8, 14));
		return today;
	}
}
