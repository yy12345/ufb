package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class Changerecordinfo extends PrintableModel  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String  serialno` varchar(24) not null comment '流水号',
	private String  refserialno;// varchar(24) not null comment '申请流水号',
	private String  tablename;// varchar(64) not null comment '表名',
	private String  custno;// char(10) not null comment '客户编号',
	private String  apkind;// char(3) not null comment '业务代码',
	private String  recordbefore;// varchar(4000) not null comment '变更信息',
	private String  recordafter;// varchar(4000) not null comment '变更信息',
	//private String   `seq` int(11) not null comment '操作记录流水号',
	//private String  updatetimestamp;//timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
	public String getRefserialno() {
		return refserialno;
	}
	public void setRefserialno(String refserialno) {
		this.refserialno = refserialno;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getApkind() {
		return apkind;
	}
	public void setApkind(String apkind) {
		this.apkind = apkind;
	}
	public String getRecordbefore() {
		return recordbefore;
	}
	public void setRecordbefore(String recordbefore) {
		this.recordbefore = recordbefore;
	}
	public String getRecordafter() {
		return recordafter;
	}
	public void setRecordafter(String recordafter) {
		this.recordafter = recordafter;
	}
	
	
	
}
