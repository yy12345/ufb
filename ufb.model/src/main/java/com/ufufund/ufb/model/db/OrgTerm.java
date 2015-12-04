package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class OrgTerm {
	private String id        ;
	private String orgid     ;
	private String year      ;
	private String type      ;
	private String start     ;
	private String end       ;
	private String updatetime;
}
