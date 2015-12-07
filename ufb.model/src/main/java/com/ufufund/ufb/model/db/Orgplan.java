package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class Orgplan{
	private String id           ;
	private String orgid        ;
	private String type         ;
	private String charge_type  ;
	private String name         ;
	private String bill_month   ;
	private String orgin_planid ;
	private String plan_date    ;
	private String deadline     ;
	private String payday       ;
	private String state        ;
	private String price_detail ;
	private String amount       ;
	private String discount     ;
	private String mark         ;
	private String updatetime   ;
}
