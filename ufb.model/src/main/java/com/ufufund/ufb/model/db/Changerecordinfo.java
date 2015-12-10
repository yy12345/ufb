package com.ufufund.ufb.model.db;

import com.ufufund.ufb.model.action.PrintableModel;

import lombok.Data;

@Data
public class Changerecordinfo extends PrintableModel {
	private String  refserialno;//申请流水号
	private String  tablename;// 表名
	private String  custno;// 客户编号
	private String  apkind;//  业务代码
	private String  recordbefore;//  变更信息
	private String  recordafter;//  变更信息
}
