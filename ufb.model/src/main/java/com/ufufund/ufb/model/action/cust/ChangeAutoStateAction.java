package com.ufufund.ufb.model.action.cust;

import lombok.Data;

@Data
public class ChangeAutoStateAction{

	private String autoid;// char(24) default '' comment 'ID',
	private String state;// state  P-暂停 ,C 终止 删除 ,N 恢复
	private String custno;// char(24) default '' comment '客户编号',
	private String tradepwd;
}
