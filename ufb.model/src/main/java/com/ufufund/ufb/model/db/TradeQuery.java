package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class TradeQuery{

	private String custno;
	private String serialno;        
	private String appdateindex;
	private String appcateindex;
	private String apptypeindex;
	private String startappdate;
	private String endappdate;
}
