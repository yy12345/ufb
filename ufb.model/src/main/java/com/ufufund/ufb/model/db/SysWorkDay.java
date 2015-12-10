package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class SysWorkDay{

	/**
	 * 日期
	 */
	private String workdate;
	
	/**
	 * 是否工作日：Y 是； N 否
	 */
	private String workflag;
	
}
