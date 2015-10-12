package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class BankBaseInfo{

	private String bankno;
	private String banknm;
	private String level;    // 通道级别：1-支持海富通和银联；2-仅支持海富通银联
	private String disorder; // 展示顺序
	private String status;   // 状态：Y 正常；N 禁用；P 暂停
	private String mark;     // 备注，or暂停描述
	private String createtime;
	private String updatetime;
	
}
