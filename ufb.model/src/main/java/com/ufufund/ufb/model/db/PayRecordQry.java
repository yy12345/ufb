package com.ufufund.ufb.model.db;

import lombok.Data;

/**
 * 缴费明细：查询参数对象
 * @author ayis
 * 2015年11月12日
 */
@Data
public class PayRecordQry {

	private String custno;
	private String orgid;
	private String sid;
	private String startTime;
	private String endTime;
	private String type;
	private int total;
	private int page;
	private int pageSize;
	private int start;
	private int end;
}
