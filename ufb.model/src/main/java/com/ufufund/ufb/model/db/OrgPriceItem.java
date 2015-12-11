package com.ufufund.ufb.model.db;

import java.util.List;

import lombok.Data;

@Data
public class OrgPriceItem {

	private String id         ; //流水id
	private String orgid      ; //机构id
	private String termid     ; //学年id
	private String type       ; //费项类别：01-学费；02-保育费；03-膳食费；04-培训费；05-代收费；06-其它
	private String name       ; //费项名称
	private String amount     ; //金额
	private String chage_type ; //计费周期类型
	private String state      ; //状态：1-正常；9-删除
	
}
