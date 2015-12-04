package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class OrgPriceItem {
	private String id        ;
	private String orgid     ;
	private String termid    ;
	private String type      ;
	private String name      ;
	private String amount    ;
	private String chage_type;
	private String state     ;
	private String createtime;
	private String updatetime;
}
