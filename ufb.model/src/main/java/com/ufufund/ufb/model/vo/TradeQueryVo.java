package com.ufufund.ufb.model.vo;

import lombok.Data;

@Data
public class TradeQueryVo{

	private String custno;
	private String serialno;        
	private String appdateindex;
	private String appcateindex;
	private String apptypeindex;
	private String startappdate;
	private String endappdate;
}
