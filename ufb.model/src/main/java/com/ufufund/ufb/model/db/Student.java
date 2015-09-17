package com.ufufund.ufb.model.db;

import lombok.Data;

@Data
public class Student {

	private String sid;
	private String cid;
	private String sno;
	private String name;
	private String sex;
	private String birthday;
	private String certno;
	private String state;
	private String f_type;
	private String f_address;
	private String p_custno;
	private String p_date;
	private String pay_default;
	private String contact_default;
	private String p1_relation;
	private String p1_name;
	private String p1_mobile;
	private String p1_mail;
	private String p1_work;
	private String p2_relation;
	private String p2_name;
	private String p2_mobile;
	private String p2_mail;
	private String p2_work;
	private String start_date      ;
	private String end_date        ;
	private String registry_type   ;
	private String registry_address;
	private boolean is_addr_same    ;
	private String nation          ;
	private String insurance       ;
	private String medical         ;
	private String health          ;
	private String afraid          ;
	private String diet_not        ;
	private String allergies       ;
	private String interest        ;
	private String special_care    ;
	private String care_type       ;
	private String meal_type       ;
	private String lastupdate;
}
