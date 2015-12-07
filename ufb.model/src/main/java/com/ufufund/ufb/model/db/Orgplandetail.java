package com.ufufund.ufb.model.db;

import lombok.Data;
@Data
public class Orgplandetail {
	
	private String id           ;
	private String orgid        ;
	private String parentid     ;
	private String sid          ;
	private String plan_id      ;
	private String amount       ;
	private String discount     ;
	private String state        ;
	private String confirm_date ;
	private String pay_date     ;
	private String repay_date   ;
	private String updatetime   ;
}
