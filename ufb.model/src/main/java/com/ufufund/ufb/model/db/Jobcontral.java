package com.ufufund.ufb.model.db;


import com.ufufund.ufb.model.action.PrintableModel;

import lombok.Data;

@Data
public class Jobcontral extends PrintableModel{
	
	private String jobname; // 任务名,
	private String jobstatus;// 任务状态
	private String starttime;// 起始时间
	private String endtime;// 结束时间
	private String workdate;//工作日
	
}
